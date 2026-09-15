package github.alexzhirkevich.studentbsuby.di

import com.russhwolf.settings.ObservableSettings
import github.alexzhirkevich.studentbsuby.repo.UsernameProvider
import github.alexzhirkevich.studentbsuby.repo.UsernameProviderImpl
import github.alexzhirkevich.studentbsuby.util.Calendar
import github.alexzhirkevich.studentbsuby.util.CaptchaRecognizer
import github.alexzhirkevich.studentbsuby.util.ErrorHandler
import github.alexzhirkevich.studentbsuby.util.PlatformInfo
import github.alexzhirkevich.studentbsuby.util.ResourceManager
import github.alexzhirkevich.studentbsuby.util.createCaptchaRecognizer
import github.alexzhirkevich.studentbsuby.util.dispatchers.CoroutineJobManagerImpl
import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers
import github.alexzhirkevich.studentbsuby.util.dispatchers.DispatchersImpl
import github.alexzhirkevich.studentbsuby.util.logger.DefaultLogger
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import github.alexzhirkevich.studentbsuby.util.platformInfo
import github.alexzhirkevich.studentbsuby.util.provideDefaultSettings
import github.alexzhirkevich.studentbsuby.util.provideSecureSettings
import github.alexzhirkevich.studentbsuby.workers.SyncUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Mirrors the original Hilt [github.alexzhirkevich.studentbsuby.di] AppModule.
 * Hilt qualifiers map to Koin qualifiers:
 * `@CredentialsPrefsQualifier` -> named("CredentialsPrefs"),
 * `@CookiesPrefsQualifier` -> named("CookiesPrefs").
 */
val appModule = module {

    factory<Calendar> { Calendar.Base() }

    // MintPermissionsController/MintPermissionsManager are not ported: the runtime
    // notifications permission flow moves to util.PlatformActions (CONTRACTS).
    // wave3: bind PlatformActions once its platform implementations land.

    // ConnectivityManager (unscoped in Hilt -> factory) is bound in the platform
    // modules: InternetConnectivityManager constructors differ per platform.
    // The StateFlowCommunication(false) initial value is preserved there.

    // wave3: factory<BroadcastMapper<ConnectivityUi>> {
    //     BroadcastReceiverMapper("ConnectivityUi", ConnectivityUiSerializer)
    // } — ConnectivityUi/ConnectivityUiSerializer live in ui.screens.drawer (UI wave).
    // The action string must stay the literal "ConnectivityUi".

    single<CaptchaRecognizer> { createCaptchaRecognizer() }

    // Default preferences file ("github.alexzhirkevich.studentbsuby_preferences"),
    // including the one-time scrub of legacy plaintext username/password keys.
    factory<ObservableSettings> { provideDefaultSettings() }

    factory<ObservableSettings>(named("CredentialsPrefs")) {
        provideSecureSettings("github.alexzhirkevich.studentbsuby_credentials")
    }

    // named("CookiesPrefs") is bound in the platform modules: the Android side must
    // run migrateLegacyCookies on the SharedPreferences before wrapping it.

    single<Logger> { DefaultLogger() }

    // FileLogger exists but is intentionally NOT bound (same as the original app).

    factory<ResourceManager> { ResourceManager.Base() }

    factory<Dispatchers> { DispatchersImpl(CoroutineJobManagerImpl()) }

    factory<ErrorHandler> { ErrorHandler.Log(get()) }

    // WorkerManager (replaces the direct androidx.work.WorkManager binding) is bound
    // in the platform modules: SyncWorkerManager on Android, BackgroundSyncScheduler on iOS.

    factory<UsernameProvider> { UsernameProviderImpl(get(named("CredentialsPrefs"))) }

    single<PlatformInfo> { platformInfo() }

    // Replaces the Hilt-assisted SyncWorker constructor injection: the platform
    // workers (SyncWorker / BackgroundSyncScheduler) resolve this use case.
    factory { SyncUseCase(get(), get(), get(), get()) }
}
