package github.alexzhirkevich.studentbsuby

import androidx.compose.ui.window.ComposeUIViewController
import github.alexzhirkevich.studentbsuby.di.initKoin
import github.alexzhirkevich.studentbsuby.di.platformModule
import github.alexzhirkevich.studentbsuby.workers.BackgroundSyncScheduler
import org.koin.mp.KoinPlatformTools
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoinIfNeeded()
    return ComposeUIViewController {
        App()
    }
}

/**
 * Starts Koin exactly once (idempotent: skipped when a Koin context is already
 * running). [BackgroundSyncScheduler.register] must run before the application
 * finishes launching — BGTaskScheduler requirement.
 */
private fun initKoinIfNeeded() {
    if (KoinPlatformTools.defaultContext().getOrNull() != null)
        return
    val koin = initKoin(platformModules = listOf(platformModule)).koin
    koin.get<BackgroundSyncScheduler>().register()
}
