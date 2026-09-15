package github.alexzhirkevich.studentbsuby.api

import com.fleeksoft.ksoup.Ksoup
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Parameters
import io.ktor.http.isSuccess

fun LoginApi.createLoginData(
    login: String,
    stud: String,
    captcha: String
): FormUrlEncodedBody = mapOf(
    "ctl00\$ContentPlaceHolder0\$txtUserLogin" to login,
    "ctl00\$ContentPlaceHolder0\$txtUserPassword" to stud,
    "ctl00\$ContentPlaceHolder0\$txtCapture" to captcha,
)

interface LoginApi {
    suspend fun initialize(): HttpResponse
    suspend fun captcha(): HttpResponse
    suspend fun login(body: FormUrlEncodedBody): HttpResponse
}

class LoginApiImpl(private val client: HttpClient) : LoginApi {
    override suspend fun initialize(): HttpResponse = client.get("login.aspx") {
        header("Referer", "https://student.bsu.by/login.aspx")
    }
    override suspend fun captcha(): HttpResponse = client.get("Captcha/CaptchaImage.aspx") {
        header("Referer", "https://student.bsu.by/login.aspx")
    }
    override suspend fun login(body: FormUrlEncodedBody): HttpResponse =
        client.submitForm(
            url = "login.aspx",
            formParameters = Parameters.build {
                body.forEach { (k, v) -> append(k, v) }
            }
        ) {
            header("Referer", "https://student.bsu.by/login.aspx")
        }
}

class LoginApiWrapper(private val api: LoginApi) : LoginApi {

    private var __VIEWSTATE = ""
    private var __VIEWSTATEGENERATOR = ""
    private var __EVENTVALIDATION = ""
    private var __BTNLOGON = ""
    private var __EVENTTARGET = ""
    private var __EVENTARGUMENT = ""

    // Ktor 3 HttpResponse is not reusable after body is consumed.
    // We store the last body string to avoid double-reading.
    var lastBody: String? = null
        private set

    override suspend fun initialize(): HttpResponse {
        val resp = api.initialize()
        lastBody = if (resp.status.isSuccess()) resp.bodyAsText() else null
        val jsoup = lastBody?.let { Ksoup.parse(it) }

        __EVENTARGUMENT = (jsoup?.getElementById("__EVENTARGUMENT")
            ?: jsoup?.selectFirst("input[name=__EVENTARGUMENT]"))
            ?.attr("value").orEmpty()
        __EVENTTARGET = (jsoup?.getElementById("__EVENTTARGET")
            ?: jsoup?.selectFirst("input[name=__EVENTTARGET]"))
            ?.attr("value").orEmpty()
        __VIEWSTATE = (jsoup?.getElementById("__VIEWSTATE")
            ?: jsoup?.selectFirst("input[name=__VIEWSTATE]"))
            ?.attr("value").orEmpty()
        __VIEWSTATEGENERATOR = (jsoup?.getElementById("__VIEWSTATEGENERATOR")
            ?: jsoup?.selectFirst("input[name=__VIEWSTATEGENERATOR]"))
            ?.attr("value").orEmpty()
        __EVENTVALIDATION = (jsoup?.getElementById("__EVENTVALIDATION")
            ?: jsoup?.selectFirst("input[name=__EVENTVALIDATION]"))
            ?.attr("value").orEmpty()
        __BTNLOGON = (jsoup?.getElementsByAttributeValue("name", "ctl00\$ContentPlaceHolder0\$btnLogon")
            ?.firstOrNull() ?: jsoup?.selectFirst("input[name*=btnLogon]"))
            ?.attr("value") ?: "Войти"

        return resp
    }

    override suspend fun captcha(): HttpResponse = api.captcha()

    override suspend fun login(body: FormUrlEncodedBody): HttpResponse {
        val form = body.toMutableMap().apply {
            this["__EVENTTARGET"] = __EVENTTARGET
            this["__EVENTARGUMENT"] = __EVENTARGUMENT
            this["__VIEWSTATE"] = __VIEWSTATE
            this["__VIEWSTATEGENERATOR"] = __VIEWSTATEGENERATOR
            this["__EVENTVALIDATION"] = __EVENTVALIDATION
            this["ctl00\$ContentPlaceHolder0\$btnLogon"] = __BTNLOGON
        }
        val resp = api.login(form)
        lastBody = if (resp.status.isSuccess()) resp.bodyAsText() else null
        return resp
    }
}
