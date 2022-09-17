package github.alexzhirkevich.studentbsuby

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import github.alexzhirkevich.studentbsuby.util.Dispatchers
import github.alexzhirkevich.studentbsuby.repo.RemoteConfigRepository
import github.alexzhirkevich.studentbsuby.repo.ReviewRepository
import github.alexzhirkevich.studentbsuby.repo.UpdateRepository
import github.alexzhirkevich.studentbsuby.util.BaseSuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.SuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.communication.Mapper

class MainActivityEventHandler(
    private val dispatchers: Dispatchers,
    private val remoteConfigRepository: RemoteConfigRepository,
    private val updateRepository: UpdateRepository,
    private val reviewRepository: ReviewRepository,
    private val showUpdateRequired : Mapper<Boolean>
) : SuspendEventHandler<MainActivityEvent> by SuspendEventHandler.from(
    TestForAppUpdateHandler(
        dispatchers = dispatchers,
        remoteConfigRepository = remoteConfigRepository,
        updateRepository = updateRepository,
        reviewRepository = reviewRepository,
        showUpdateRequired = showUpdateRequired
    ),
    ExitClickedHandler(),
    UpdateClickedHandler()
)


private class ExitClickedHandler
    : BaseSuspendEventHandler<MainActivityEvent.ExitClicked>(
    MainActivityEvent.ExitClicked::class) {
    override suspend fun handle(event: MainActivityEvent.ExitClicked) {
        event.activity.finish()
    }
}
private class UpdateClickedHandler
    : BaseSuspendEventHandler<MainActivityEvent.UpdateClicked>(
    MainActivityEvent.UpdateClicked::class) {
    override suspend fun handle(event: MainActivityEvent.UpdateClicked) {
        openGooglePlay(event.activity)
    }
}

private class TestForAppUpdateHandler(
    private val dispatchers: Dispatchers,
    private val remoteConfigRepository: RemoteConfigRepository,
    private val updateRepository: UpdateRepository,
    private val reviewRepository: ReviewRepository,
    private val showUpdateRequired : Mapper<Boolean>
) : BaseSuspendEventHandler<MainActivityEvent.TestForAppUpdate>(
    MainActivityEvent.TestForAppUpdate::class
){

    override suspend fun launch() {
        if (remoteConfigRepository.getMinimumStableVersionIfNeeded() != null){
            showUpdateRequired.map(true)
        }

    }

    override suspend fun handle(event: MainActivityEvent.TestForAppUpdate) {
        val immediate = remoteConfigRepository.getMinimumStableVersionIfNeeded() != null
        dispatchers.runOnUI {
            reviewRepository.tryShowReviewDialog(event.activity)
            updateRepository.tryUpdate(
                event.activity,
                immediate,
                onFailedToInAppUpdate = { openGooglePlay(event.activity) })
        }
    }
}

private fun openGooglePlay(context: Context){
    try {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=${BuildConfig.APPLICATION_ID}")
            )
        )
    } catch (e: ActivityNotFoundException) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
            )
        )
    }
}