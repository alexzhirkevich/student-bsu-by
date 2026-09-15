package github.alexzhirkevich.studentbsuby.di

import androidx.compose.ui.graphics.ImageBitmap
import github.alexzhirkevich.studentbsuby.repo.LoginRepository
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.ConnectivityUi
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.ConnectivityUiSerializer
import github.alexzhirkevich.studentbsuby.ui.screens.login.LoginEventHandlerImpl
import github.alexzhirkevich.studentbsuby.ui.screens.login.LoginViewModel
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.communication.BroadcastReceiverMapper
import github.alexzhirkevich.studentbsuby.util.communication.SharedFlowCommunication
import github.alexzhirkevich.studentbsuby.util.communication.StateFlowCommunication
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Mirrors the original LoginModule (ViewModelComponent).
 */
val loginModule = module {

    // Was @Singleton on the class itself; the LoginApi it receives is the stateful
    // LoginApiWrapper singleton (VIEWSTATE state).
    single {
        LoginRepository(
            api = get(),
            credentialsPreferences = get(named("CredentialsPrefs")),
            captchaRecognizer = get(),
            loginCookieManager = get(),
        )
    }

    viewModel {
        val login = StateFlowCommunication("")
        val pass = StateFlowCommunication("")
        val captcha = StateFlowCommunication("")
        val captchaImage = StateFlowCommunication<DataState<ImageBitmap>>(DataState.Empty)
        val autoLogin = StateFlowCommunication(false)
        val error = SharedFlowCommunication<String>()
        val controlsEnabled = StateFlowCommunication(true)
        val connectivityMapper = BroadcastReceiverMapper("ConnectivityUi", ConnectivityUiSerializer)

        val eventHandler = LoginEventHandlerImpl(
            dispatchers = get(),
            resourceManager = get(),
            loginRepository = get(),
            syncWorkerManager = get(),
            loginMapper = login,
            passMapper = pass,
            captchaMapper = captcha,
            captchaImageMapper = captchaImage,
            autoLoginMapper = autoLogin,
            errorMapper = error,
            controlsEnabledMapper = controlsEnabled,
            connectivityMapper = connectivityMapper
        )

        LoginViewModel(
            eventHandler = eventHandler,
            dispatchers = get(),
            errorHandler = get(),
            login = login,
            password = pass,
            captcha = captcha,
            captchaImage = captchaImage,
            autoLogin = autoLogin,
            controlsEnabled = controlsEnabled,
            error = error,
            loginRepository = get()
        )
    }
}
