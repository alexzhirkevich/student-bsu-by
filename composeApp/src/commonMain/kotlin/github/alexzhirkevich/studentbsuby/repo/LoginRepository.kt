package github.alexzhirkevich.studentbsuby.repo

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import com.fleeksoft.ksoup.Ksoup
import com.russhwolf.settings.ObservableSettings
import github.alexzhirkevich.studentbsuby.api.LoginApi
import github.alexzhirkevich.studentbsuby.api.LoginApiWrapper
import github.alexzhirkevich.studentbsuby.api.createLoginData
import github.alexzhirkevich.studentbsuby.api.isSessionExpired
import github.alexzhirkevich.studentbsuby.util.CaptchaRecognizer
import github.alexzhirkevich.studentbsuby.util.LoginCookieManager
import github.alexzhirkevich.studentbsuby.util.sharedPreferences
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlin.reflect.KProperty

interface UsernameProvider {
    val username : String
}

open class UsernameProviderImpl(
    val credentialsPrefs: ObservableSettings
) : UsernameProvider {

    override var username: String by sharedPreferences(credentialsPrefs,"")
        protected set

}
operator fun UsernameProvider.getValue(thisObj: Any?, property: KProperty<*>): String {
    return username
}

class LoginRepository(
    private val api : LoginApi,
    credentialsPreferences: ObservableSettings,
    private val captchaRecognizer: CaptchaRecognizer,
    private val loginCookieManager: LoginCookieManager,
    ) : UsernameProviderImpl(credentialsPreferences) {

    data class LoginResponse(
        val success: Boolean,
        val loggedIn: Boolean,
        val loginResult : String?
    )

    var password by sharedPreferences(credentialsPreferences, "")
        private set

    var autoLogin by sharedPreferences(credentialsPreferences,false)

    suspend fun initialize(): LoginResponse {
        try {
            val resp = api.initialize()

            val body = (api as? LoginApiWrapper)?.lastBody
                ?: if (resp.status.isSuccess()) resp.bodyAsText() else null

            val jsoup = body?.let { Ksoup.parse(it) }
            val result = jsoup
                ?.getElementById("ctl00_ContentPlaceHolder0_lbLoginResult")
                ?.text()
            val logout = (jsoup?.getElementById("ctl00_ContentPlaceHolder0_LoginStatus1")
                ?: jsoup?.selectFirst("a[href*=logout]"))
                ?.text()


            val expired = resp.status.value == 400
            val successful = resp.status.value in 200..399 || expired

            if (expired) {
                loginCookieManager.cleanCookies()
                return initialize()
            }
            return LoginResponse(
                success = successful,
                loggedIn = result?.contains("вошли",true) == true ||
                        logout?.contains("logout",true) == true ||
                        logout?.contains("выход", true) == true ||
                        logout?.contains("выйти", true) == true ||
                        logout?.contains("выход", true) == true,
                loginResult = result
            )
        } catch (t: Throwable) {
            return LoginResponse(
                success = false,
                loggedIn = false,
                loginResult = null
            )
        }
    }

    suspend fun login(
        login: String,
        password: String,
        captcha: String
    ): LoginResponse {
        return try {

            val res = api.login(
                api.createLoginData(
                    login,
                    password,
                    captcha
                )
            )

            val logged = res.status.value == 302
            val body = (api as? LoginApiWrapper)?.lastBody
                ?: if (res.status.isSuccess()) res.bodyAsText() else null

            val jsoup = body?.let { Ksoup.parse(it) }

            val loginResult = if (logged){
                this.username = login
                this.password = password
                null
            } else {
                (jsoup?.getElementById("ctl00_ContentPlaceHolder0_lbLoginResult")?.text()
                    ?.takeIf(String::isNotBlank)
                        ?: jsoup?.getElementsByClass("style1")
                            ?.lastOrNull()
                            ?.text()?.takeIf(String::isNotBlank))
            }

            return LoginResponse(
                success = res.status.value in 200..399,
                loggedIn = logged,
                loginResult = loginResult
            )

        } catch (t: Throwable) {
            LoginResponse(
                success = false,
                loggedIn = false,
                loginResult = null
            )
        }
    }

    suspend fun updateCaptcha(keepText: Boolean): ImageBitmap? {
        return kotlin.runCatching {
            if (!keepText) {
                api.initialize()
            }
            val captcha = api.captcha()
                .takeIf { it.status.isSuccess() }
                ?.body<ByteArray>()
                ?: return null
            return captcha.decodeToImageBitmap()
        }.getOrNull()
    }

    private val overflowCount = 5
    private var currentUpdateCount = 0

    tailrec suspend fun getCaptchaText(bitmap: ImageBitmap): String {
        return kotlin.runCatching {
            val text = captchaRecognizer
                .recognize(bitmap)
                .replace("B", "6", true)
                .replace("A", "4")
                .filter(Char::isDigit)
                .take(6)
            if (text.length != 6) {
                if (currentUpdateCount < overflowCount) {
                    currentUpdateCount++
                    val bitmap = updateCaptcha(true)
                        ?: return@runCatching ""
                    return getCaptchaText(bitmap)
                } else currentUpdateCount = 0
            } else {
                currentUpdateCount = 0
                return text
            }
            return@runCatching ""
        }.onFailure {
            currentUpdateCount = 0

        }.getOrDefault("")
    }

    fun logout(){
        loginCookieManager.cleanCookies()
        autoLogin = false
    }
}
