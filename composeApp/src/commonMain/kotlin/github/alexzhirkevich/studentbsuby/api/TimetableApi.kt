package github.alexzhirkevich.studentbsuby.api

import com.fleeksoft.ksoup.Ksoup
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readRawBytes
import io.ktor.http.Parameters
import io.ktor.http.isSuccess

fun TimetableApi.dayOfWeek(day : Int) : FormUrlEncodedBody = mapOf(
    "ctl00\$ctl00\$ContentPlaceHolder0\$ContentPlaceHolder1\$ctlSchedule1\$ScriptManager1" to "ctl00\$ctl00\$ContentPlaceHolder0\$ContentPlaceHolder1\$ctlSchedule1\$UpdatePanel1|ctl00\$ctl00\$ContentPlaceHolder0\$ContentPlaceHolder1\$ctlSchedule1\$cmdDay${day+1}",
    "__EVENTTARGET" to "ctl00\$ctl00\$ContentPlaceHolder0\$ContentPlaceHolder1\$ctlSchedule1\$cmdDay${day+1}",
)

interface TimetableApi {

    suspend fun init() : HttpResponse

    suspend fun timetable(dayOfWeek: FormUrlEncodedBody) : HttpResponse
}

class TimetableApiImpl(private val client : HttpClient) : TimetableApi {

    override suspend fun init(): HttpResponse =
        client.get("PersonalCabinet/Schedule") {
            header("User-Agent", "Mozilla")
        }

    override suspend fun timetable(dayOfWeek: FormUrlEncodedBody): HttpResponse =
        client.submitForm(
            url = "PersonalCabinet/Schedule",
            formParameters = Parameters.build {
                dayOfWeek.forEach { (k, v) -> append(k, v) }
            }
        ) {
            header("User-Agent", "Mozilla")
        }
}

class TimetableApiWrapper(private val api : TimetableApi) : TimetableApi{

    private var __VIEWSTATE = ""
    private var __VIEWSTATEGENERATOR = ""
    private var __EVENTVALIDATION = ""
    private var __BTNLOGON = ""
    private var __EVENTARGUMENT = ""

    override suspend fun init(): HttpResponse {
        val resp = api.init()

        val jsoup = if (resp.status.isSuccess())
            Ksoup.parse(resp.readRawBytes().decodeToString()) else null

        __EVENTARGUMENT = jsoup?.getElementById("__EVENTARGUMENT")
            ?.attr("value").orEmpty()
        __VIEWSTATE = jsoup?.getElementById("__VIEWSTATE")
            ?.attr("value").orEmpty()
        __VIEWSTATEGENERATOR = jsoup?.getElementById("__VIEWSTATEGENERATOR")
            ?.attr("value").orEmpty()
        __EVENTVALIDATION = jsoup?.getElementById("__EVENTVALIDATION")
            ?.attr("value").orEmpty()
        __BTNLOGON = jsoup?.getElementsByAttributeValue("name","ctl00\$ContentPlaceHolder0\$btnLogon")
            ?.attr("value") ?: "Войти"
        return resp
    }

    override suspend fun timetable(dayOfWeek: FormUrlEncodedBody): HttpResponse {
        val map = dayOfWeek.toMutableMap().apply {
            this["__EVENTARGUMENT"] = __EVENTARGUMENT
            this["__VIEWSTATE"] = __VIEWSTATE
            this["__VIEWSTATEGENERATOR"] = __VIEWSTATEGENERATOR
            this["__EVENTVALIDATION"] = __EVENTVALIDATION
            this["ctl00\$ContentPlaceHolder0\$btnLogon"] = __BTNLOGON
            this["__ASYNCPOST"] = "true"
        }
        return api.timetable(map)
    }

}
