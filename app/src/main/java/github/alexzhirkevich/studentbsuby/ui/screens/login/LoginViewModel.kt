package github.alexzhirkevich.studentbsuby.ui.screens.login

import android.content.res.Resources
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.repo.LoginRepository
import github.alexzhirkevich.studentbsuby.repo.SettingsRepository
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.exceptions.LoginException
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import github.alexzhirkevich.studentbsuby.util.setState
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import github.alexzhirkevich.studentbsuby.workers.SyncWorkerManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val syncWorkerManager: SyncWorkerManager,
    private val settingsRepository: SettingsRepository,
    private val resources: Resources,
    private val logger: Logger
) : ViewModel() {

    private val _splashText = mutableStateOf("")
    val splashText : State<String>
        get() = _splashText

    private val _shouldShowSplashScreen = mutableStateOf(true)
    val shouldShowSplashScreen :State<Boolean>
        get() = _shouldShowSplashScreen

    private val _loggedIn = mutableStateOf<DataState<Boolean>>(DataState.Loading)
    val loggedIn :State<DataState<Boolean>>
        get() = _loggedIn

    private val _captchaBitmap = MutableStateFlow<DataState<ImageBitmap>>(DataState.Loading)
    val captchaBitmap : StateFlow<DataState<ImageBitmap>> = _captchaBitmap.asStateFlow()

    private val _captchaText = MutableStateFlow("")
    val captchaText : StateFlow<String> = _captchaText.asStateFlow()

    private val _loginText = mutableStateOf(
        loginRepository.username)
    val loginText : State<String>
        get()= _loginText

    private val _autoLogin = mutableStateOf(loginRepository.autoLogin)
    val autoLogin : State<Boolean>
        get()= _autoLogin

    private val _passwordText = mutableStateOf(
        loginRepository.password)

    val passwordText : State<String>
        get()= _passwordText

    @Volatile private var initialized = false

    init {
        viewModelScope.launch(Dispatchers.IO) {
            init()
        }
    }

    fun updateCaptcha() {
        viewModelScope.launch(Dispatchers.IO) {
            if (initialized) {
                updateCaptchaInternal()
            } else {
                init()
            }
        }
    }

    fun setAutoLogin(autoLogin : Boolean){
        _autoLogin.value = autoLogin
    }

    fun setLoginText(login : String){
        _loginText.value = login.replace("\n","")
    }

    fun setPasswordText(password : String){
        _passwordText.value = password.replace("\n","")
    }

    fun setCaptchaText(text : String){
        _captchaText.value = text.replace("\n","")
    }

    fun login() {
        _loggedIn.value = DataState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val res = loginRepository.login(
                    loginText.value,
                    passwordText.value,
                    captchaText.value
                )
                setState {
                    _loggedIn.value = if (res.loggedIn) {
                        loginRepository.autoLogin = autoLogin.value
                        if (autoLogin.value && settingsRepository.synchronizationEnabled){
                            syncWorkerManager.run()
                        }
                        DataState.Success(true)
                    } else {
                        updateCaptcha()
                        _shouldShowSplashScreen.value = false
                        DataState.Error(
                            R.string.error_login,
                            res.loginResult?.let {
                                LoginException(it)
                            }
                        )
                    }
                }
            }.onFailure {
                _loggedIn.value = DataState.Error(
                    R.string.error_login,it)
                updateCaptcha()
                logger.log(
                    "Failed to log in",
                    tag = javaClass.simpleName,
                    logLevel = Logger.LogLevel.Error,
                    cause = it
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        captchaBitmap.value.valueOrNull()
            ?.asAndroidBitmap()?.recycle()
    }

    private suspend fun updateCaptchaInternal(){
        _captchaBitmap.value.valueOrNull()?.asAndroidBitmap()?.recycle()
        _captchaBitmap.value = DataState.Loading
        loginRepository.updateCaptcha(false)?.let {
            _captchaBitmap.tryEmit(DataState.Success(it.asImageBitmap()))
            withContext(Dispatchers.Default) {
                _captchaText.tryEmit(loginRepository.getCaptchaText(it))
            }
        }
    }

    private suspend fun init() : Result<Boolean> {
        return kotlin.runCatching {
            coroutineScope {
                async {
                    delay(5000)
                    setState {
                        _shouldShowSplashScreen.value = false
                    }
                }

                setState {
                    _loggedIn.value = DataState.Loading
                    if (!loginRepository.canRestoreSession()) {
                        _shouldShowSplashScreen.value = false
                    }
                    _splashText.value = resources.getString(R.string.restoring_session)
                }

                val (success, logged, _) = loginRepository.initialize()

                if (logged) {
                    setState {
                        _loggedIn.value = DataState.Success(true)
                    }
                    return@coroutineScope true
                }

                setState {
                    if (!loginRepository.autoLogin) {
                        _loggedIn.value = if (success)
                            DataState.Success(logged)
                        else DataState.Error(R.string.error_connection, null)
                    }
                    if (!loginRepository.autoLogin && !logged) {
                        _shouldShowSplashScreen.value = false
                    }
                }
                if (success && !logged && loginRepository.autoLogin) {
                    setState {
                        _splashText.value = resources.getString(R.string.loading_captcha)
                    }
                    updateAndRecognizeSameCaptcha(true) {
                        setState {
                            _splashText.value =
                                resources.getString(R.string.recognizing_captcha)
                        }
                    }
                    setState {
                        _splashText.value = resources.getString(R.string.trying_to_singin)
                    }
                    login()
                } else {
                    initialized = false
                    updateCaptchaInternal()
                }
                success
            }
        }.onFailure {
            initialized = false
            logger.log(
                "Failed to initialize login page",
                this@LoginViewModel.javaClass.simpleName,
                Logger.LogLevel.Error,
                it
            )
            setState {
                _shouldShowSplashScreen.value = false
                _loggedIn.value = DataState.Error(R.string.error_connection, null)
            }
        }.onSuccess {
            initialized = true
        }
    }
    
    private suspend fun updateAndRecognizeSameCaptcha(show : Boolean, onCapcthaLoaded : suspend () -> Unit = {}) {

        val captcha = loginRepository.updateCaptcha(true) ?: return
        onCapcthaLoaded()

        val text = loginRepository.getCaptchaText(captcha)
        setState {
            _captchaText.value = text
        }


        if (show) {
            _captchaBitmap.apply {
                value.valueOrNull()?.asAndroidBitmap()?.recycle()
                setState {
                    value = captcha.asImageBitmap().let {
                        DataState.Success(it)
                    }
                }
            }
        }
    }

}