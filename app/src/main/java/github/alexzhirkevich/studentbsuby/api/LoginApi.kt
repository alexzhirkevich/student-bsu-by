package github.alexzhirkevich.studentbsuby.api

import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Response
import retrofit2.http.*
import java.nio.charset.Charset

fun LoginApi.createLoginData(
    login: String,
    stud: String,
    captcha : String) : FormUrlEncodedBody = mapOf(
        "ctl00\$ContentPlaceHolder0\$txtUserLogin" to login,
        "ctl00\$ContentPlaceHolder0\$txtUserPassword" to stud,
        "ctl00\$ContentPlaceHolder0\$txtCapture" to captcha,
)



interface LoginApi {

    @GET("login.aspx")
    suspend fun initialize() : Response<ResponseBody>

    @Streaming
    @GET("Captcha/CaptchaImage.aspx")
    suspend fun captcha() : Response<ResponseBody>

    @FormUrlEncoded
    @POST("login.aspx")
    suspend fun login(
        @FieldMap(encoded = false) body : FormUrlEncodedBody
    ) : Response<ResponseBody>
}

val ResponseBody.isSessionExpired
    get() = source().apply {
        request(Long.MAX_VALUE)
    }.buffer.clone().readString(Charset.forName("UTF-8")).let {
        it.contains("ctl00_ContentPlaceHolder0_cmdLogIn") ||
                it.contains("ctl00\$ContentPlaceHolder0\$btnLogon")
    }

class LoginApiWrapper(val api : LoginApi) : LoginApi {

    private var __VIEWSTATE = ""
    private var __VIEWSTATEGENERATOR = ""
    private var __EVENTVALIDATION = ""
    private var __BTNLOGON = ""
    private var __EVENTTARGET = ""
    private var __EVENTARGUMENT = ""

    override suspend fun initialize(): Response<ResponseBody> {
        return api.initialize().also {
            val jsoup = it.body()?.byteStream()?.readBytes()?.let {
                Jsoup.parse(String(it))
            }
            __EVENTARGUMENT = jsoup?.getElementById("__EVENTARGUMENT")
                ?.attr("value").orEmpty()
            __EVENTTARGET = jsoup?.getElementById("__EVENTTARGET")
                ?.attr("value").orEmpty()
            __VIEWSTATE = jsoup?.getElementById("__VIEWSTATE")
                ?.attr("value").orEmpty()
            __VIEWSTATEGENERATOR = jsoup?.getElementById("__VIEWSTATEGENERATOR")
                ?.attr("value").orEmpty()
            __EVENTVALIDATION = jsoup?.getElementById("__EVENTVALIDATION")
                ?.attr("value").orEmpty()
            __BTNLOGON = jsoup?.getElementsByAttributeValue("name","ctl00\$ContentPlaceHolder0\$btnLogon")
                ?.attr("value") ?: "Войти"
        }
    }

    override suspend fun captcha(): Response<ResponseBody> {
        return api.captcha()
    }

    override suspend fun login(@FieldMap(encoded = false) body: FormUrlEncodedBody): Response<ResponseBody> {
        val form = body.toMutableMap().apply {
            this["__EVENTTARGET"] = __EVENTTARGET
            this["__EVENTARGUMENT"] = __EVENTARGUMENT
            this["__VIEWSTATE"] = __VIEWSTATE
            this["__VIEWSTATEGENERATOR"] = __VIEWSTATEGENERATOR
            this["__EVENTVALIDATION"] = __EVENTVALIDATION
            this["ctl00\$ContentPlaceHolder0\$btnLogon"] = __BTNLOGON
        }
        return api.login(form)
    }

}