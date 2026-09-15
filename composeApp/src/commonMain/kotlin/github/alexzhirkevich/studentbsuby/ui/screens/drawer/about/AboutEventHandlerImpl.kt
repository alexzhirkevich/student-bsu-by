package github.alexzhirkevich.studentbsuby.ui.screens.drawer.about

import github.alexzhirkevich.studentbsuby.repo.RemoteConfigRepository
import github.alexzhirkevich.studentbsuby.util.BaseSuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.SuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.PlatformActions

interface IAboutEventHandler : SuspendEventHandler<AboutEvent>

class AboutEventHandlerImpl(
    platformActions: PlatformActions,
    configRepository: RemoteConfigRepository
) : IAboutEventHandler, SuspendEventHandler<AboutEvent> by SuspendEventHandler.from(
    TgClickedHandler(platformActions, configRepository),
    EmailClickedHandler(platformActions, configRepository)
)

private class TgClickedHandler(
    private val platformActions: PlatformActions,
    private val configRepository : RemoteConfigRepository
): BaseSuspendEventHandler<AboutEvent.TgClicked>(
    AboutEvent.TgClicked::class
){

    override suspend fun launch() {
        runCatching {
            configRepository.update()
        }
    }

    override suspend fun handle(event: AboutEvent.TgClicked) {
        runCatching {
            platformActions.openUrl(configRepository.telegram())
        }
    }
}

private class EmailClickedHandler(
    private val platformActions: PlatformActions,
    private val configRepository : RemoteConfigRepository
): BaseSuspendEventHandler<AboutEvent.EmailClicked>(
    AboutEvent.EmailClicked::class
){

    override suspend fun launch() {
        runCatching {
            configRepository.update()
        }
    }

    override suspend fun handle(event: AboutEvent.EmailClicked) {
        runCatching {
            platformActions.openUrl("mailto:${configRepository.mail()}")
        }
    }
}
