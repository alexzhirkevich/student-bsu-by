package github.alexzhirkevich.studentbsuby.ui.screens.login

import androidx.compose.ui.graphics.ImageBitmap
import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers
import github.alexzhirkevich.studentbsuby.repo.LoginRepository
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.ErrorHandler
import github.alexzhirkevich.studentbsuby.util.SuspendHandlerViewModel
import github.alexzhirkevich.studentbsuby.util.communication.Communication
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication

class LoginViewModel(
    eventHandler: LoginEventHandler,
    dispatchers: Dispatchers,
    errorHandler: ErrorHandler,
    val login : StateCommunication<String>,
    val password : StateCommunication<String>,
    val captcha : StateCommunication<String>,
    val captchaImage : StateCommunication<DataState<ImageBitmap>>,
    val autoLogin : StateCommunication<Boolean>,
    val controlsEnabled: StateCommunication<Boolean>,
    val error : Communication<String>,
    loginRepository: LoginRepository
    ) : SuspendHandlerViewModel<LoginEvent>(
    dispatchers = dispatchers,
    suspendEventHandler = eventHandler,
    errorHandler = errorHandler
) {
    val skipLogin = loginRepository.autoLogin
}
