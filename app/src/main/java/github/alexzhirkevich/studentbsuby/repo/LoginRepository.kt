package github.alexzhirkevich.studentbsuby.repo

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import github.alexzhirkevich.studentbsuby.api.LoginApi
import github.alexzhirkevich.studentbsuby.api.createLoginData
import github.alexzhirkevich.studentbsuby.util.CaptchaRecognizer
import github.alexzhirkevich.studentbsuby.util.LoginCookieManager
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

private const val PREF_LOGIN = "PREF_LOGIN"
private const val PREF_PW = "PREF_PW"
private const val PREF_AUTOLOGIN = "PREF_AUTOLOGIN"

@Singleton
class LoginRepository @Inject constructor(
    private val api : LoginApi,
    private val preferences: SharedPreferences,
    private val captchaRecognizer: CaptchaRecognizer,
    private val loginCookieManager: LoginCookieManager
    ) {

    data class LoginResponse(
        val success: Boolean,
        val loggedIn: Boolean,
        val loginResult : String?
    )

    var autoLogin : Boolean
        get() = preferences.getBoolean(PREF_AUTOLOGIN, false)
        set(value) {
            preferences.edit().putBoolean(PREF_AUTOLOGIN, value).apply()
        }

    val cachedLogin: String
        get() = preferences.getString(PREF_LOGIN, "").orEmpty()

    val cachedPassword: String
        get() = preferences.getString(PREF_PW, "").orEmpty()


    suspend fun initialize(): LoginResponse {
        try {
            val resp = api.initialize()


            val jsoup = resp.body()?.byteStream()?.readBytes()?.let {
                Jsoup.parse(String(it))
            }
            val result = jsoup
                ?.getElementById("ctl00_ContentPlaceHolder0_lbLoginResult")
                ?.text()
            val logout = jsoup
                ?.getElementById("ctl00_ContentPlaceHolder0_LoginStatus1")
                ?.text()


            val expired = resp.code() == 400
            val successful = resp.isSuccessful || expired

            if (expired) {
                loginCookieManager.cleanCookies()
                return initialize()
            }
            return LoginResponse(
                successful,
            result?.contains("вошли",true) == true ||
                    logout?.contains("logout",true) == true,
                result)
        } catch (t: Throwable) {
            return LoginResponse(false,false,null)
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

            val logged = res.code() == 302
            val loginResult = if (logged){
                preferences.edit()
                    .putString(PREF_LOGIN, login)
                    .putString(PREF_PW, password)
                    .apply()
                null
            } else{
                res.body()?.byteStream()?.readBytes()?.let {
                    Jsoup.parse(String(it)).let {
                        it.getElementById("ctl00_ContentPlaceHolder0_lbLoginResult")?.text()
                            ?.takeIf(String::isNotBlank)
                            ?: it.getElementsByClass("style1")
                                ?.lastOrNull()
                                ?.text()?.takeIf(String::isNotBlank)
                    }
                }
            }
            return LoginResponse(res.isSuccessful, logged,loginResult)

        } catch (t: Throwable) {
            LoginResponse(false,false,null)
        }
    }


    suspend fun updateCaptcha(keepText: Boolean): Bitmap? {
        return kotlin.runCatching {
            if (!keepText) {
                api.initialize()
            }
            val captcha = api.captcha().body()?.byteStream()?.readBytes()
                ?: return null
            return BitmapFactory.decodeByteArray(captcha, 0, captcha.size)
        }.getOrNull()
    }

    fun canRestoreSession() = loginCookieManager.canRestoreSession()




    private val overflowCount = 5
    private var currentUpdateCount = 0
    private var updatedBitmap: Bitmap? = null

    suspend fun getCaptchaText(bitmap: Bitmap): String {
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
                    updatedBitmap?.recycle()
                    updatedBitmap = updateCaptcha(true)
                        ?: return@runCatching ""
                    return getCaptchaText(updatedBitmap!!)
                } else currentUpdateCount = 0
            } else {
                updatedBitmap?.recycle()
                currentUpdateCount = 0
                return text
            }
            return@runCatching ""
        }.onFailure {
            updatedBitmap?.recycle()
            currentUpdateCount = 0

        }.getOrDefault("")
    }

    fun logout(){
        loginCookieManager.cleanCookies()
        autoLogin = false
    }
}