package github.alexzhirkevich.studentbsuby.di

import com.russhwolf.settings.ObservableSettings
import github.alexzhirkevich.studentbsuby.dao.appDatabaseBuilder
import github.alexzhirkevich.studentbsuby.dao.createAppDatabase
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.notification_channel_description
import github.alexzhirkevich.studentbsuby.resources.updates
import github.alexzhirkevich.studentbsuby.services.firebase.AnalyticsReporter
import github.alexzhirkevich.studentbsuby.services.firebase.AnalyticsReporterImpl
import github.alexzhirkevich.studentbsuby.services.firebase.CrashReporter
import github.alexzhirkevich.studentbsuby.services.firebase.CrashReporterImpl
import github.alexzhirkevich.studentbsuby.services.firebase.RemoteConfigClient
import github.alexzhirkevich.studentbsuby.services.firebase.RemoteConfigClientImpl
import github.alexzhirkevich.studentbsuby.services.store.ReviewLauncher
import github.alexzhirkevich.studentbsuby.services.store.ReviewLauncherImpl
import github.alexzhirkevich.studentbsuby.services.store.UpdateLauncher
import github.alexzhirkevich.studentbsuby.services.store.UpdateLauncherImpl
import github.alexzhirkevich.studentbsuby.util.ConnectivityManager
import github.alexzhirkevich.studentbsuby.util.InternetConnectivityManager
import github.alexzhirkevich.studentbsuby.util.NotificationCreator
import github.alexzhirkevich.studentbsuby.util.NotificationCreatorImpl
import github.alexzhirkevich.studentbsuby.util.PlatformActions
import github.alexzhirkevich.studentbsuby.util.PlatformActionsIos
import github.alexzhirkevich.studentbsuby.util.WorkerManager
import github.alexzhirkevich.studentbsuby.util.communication.StateFlowCommunication
import github.alexzhirkevich.studentbsuby.util.provideSecureSettings
import github.alexzhirkevich.studentbsuby.workers.BackgroundSyncScheduler
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * iOS bindings of the platform-dependent parts of the graph.
 */
val platformModule = module {

    single { createAppDatabase(appDatabaseBuilder()) }

    single<ObservableSettings>(named("CookiesPrefs")) {
        provideSecureSettings("github.alexzhirkevich.studentbsuby_cookies")
    }

    factory<ConnectivityManager> {
        InternetConnectivityManager(StateFlowCommunication(false))
    }

    // Bound with the concrete type too: MainViewController resolves
    // BackgroundSyncScheduler to register the BGTaskScheduler handler at startup.
    single { BackgroundSyncScheduler(get()) } bind WorkerManager::class

    factory<NotificationCreator> {
        NotificationCreatorImpl(
            channelId = "CHANNEL_SYNCHRONIZATION",
            channelName = Res.string.updates,
            channelDescription = Res.string.notification_channel_description,
        )
    }

    single<AnalyticsReporter> { AnalyticsReporterImpl() }

    single<CrashReporter> { CrashReporterImpl() }

    single<RemoteConfigClient> { RemoteConfigClientImpl() }

    single<ReviewLauncher> { ReviewLauncherImpl() }

    single<UpdateLauncher> { UpdateLauncherImpl() }

    single<PlatformActions> { PlatformActionsIos() }
}
