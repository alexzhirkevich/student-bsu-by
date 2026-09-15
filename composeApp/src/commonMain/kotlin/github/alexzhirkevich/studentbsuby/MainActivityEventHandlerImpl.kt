package github.alexzhirkevich.studentbsuby

import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers
import github.alexzhirkevich.studentbsuby.repo.RemoteConfigRepository
import github.alexzhirkevich.studentbsuby.repo.ReviewRepository
import github.alexzhirkevich.studentbsuby.repo.UpdateRepository
import github.alexzhirkevich.studentbsuby.util.BaseSuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.PlatformActions
import github.alexzhirkevich.studentbsuby.util.SuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.communication.Mapper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

interface MainActivityEventHandler : SuspendEventHandler<MainActivityEvent>

class MainActivityEventHandlerImpl(
    private val dispatchers: Dispatchers,
    private val remoteConfigRepository: RemoteConfigRepository,
    private val updateRepository: UpdateRepository,
    private val reviewRepository: ReviewRepository,
    private val showUpdateRequired : Mapper<Boolean>,
    private val platformActions: PlatformActions
) : MainActivityEventHandler, SuspendEventHandler<MainActivityEvent> by SuspendEventHandler.from(
    InitializedHandler(
        dispatchers = dispatchers,
        remoteConfigRepository = remoteConfigRepository,
        updateRepository = updateRepository,
        reviewRepository = reviewRepository,
        showUpdateRequired = showUpdateRequired,
        platformActions = platformActions
    ),
    ExitClickedHandler(platformActions),
    UpdateClickedHandler(platformActions)
)


private class ExitClickedHandler(
    private val platformActions: PlatformActions
) : BaseSuspendEventHandler<MainActivityEvent.ExitClicked>(
    MainActivityEvent.ExitClicked::class) {
    override suspend fun handle(event: MainActivityEvent.ExitClicked) {
        platformActions.exitApp()
    }
}
private class UpdateClickedHandler(
    private val platformActions: PlatformActions
) : BaseSuspendEventHandler<MainActivityEvent.UpdateClicked>(
    MainActivityEvent.UpdateClicked::class) {
    override suspend fun handle(event: MainActivityEvent.UpdateClicked) {
        platformActions.openStorePage()
    }
}

private class InitializedHandler(
    private val dispatchers: Dispatchers,
    private val remoteConfigRepository: RemoteConfigRepository,
    private val updateRepository: UpdateRepository,
    private val reviewRepository: ReviewRepository,
    private val showUpdateRequired : Mapper<Boolean>,
    private val platformActions: PlatformActions
) : BaseSuspendEventHandler<MainActivityEvent.Initialized>(
    MainActivityEvent.Initialized::class
){

    override suspend fun launch() {

        coroutineScope {
            launch {
                kotlin.runCatching {
                    // Android impl asks only when the permission is not granted yet
                    // (13+); below 13 and when granted it returns without asking.
                    platformActions.requestNotificationsPermission()
                }
            }

            kotlin.runCatching {
                remoteConfigRepository.update()
            }
            if (remoteConfigRepository.getMinimumStableVersionIfNeeded() != null) {
                showUpdateRequired.map(true)
            }
        }
    }

    override suspend fun handle(event: MainActivityEvent.Initialized) {
        val immediate = remoteConfigRepository.getMinimumStableVersionIfNeeded() != null
        dispatchers.runOnUI {
            reviewRepository.tryShowReviewDialog()
            updateRepository.tryUpdate(
                immediate,
                onFailedToInAppUpdate = {
                    platformActions.openStorePage()
                })
        }
    }
}
