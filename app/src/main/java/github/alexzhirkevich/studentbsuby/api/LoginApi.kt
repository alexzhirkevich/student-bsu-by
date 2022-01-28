package github.alexzhirkevich.studentbsuby.api

import androidx.annotation.IntRange
import okhttp3.ResponseBody
import okhttp3.internal.http.RealResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import kotlin.math.log

fun LoginApi.createLoginData(
    login: String,
    stud: String,
    captcha : String) : FormUrlEncodedBody = mapOf(
        "__EVENTTARGET" to "",
        "__EVENTARGUMENT" to "",
        "__VIEWSTATE" to "%2FwEPDwUKMTUyNzQzMzgzMw9kFgJmD2QWAgIDD2QWBmYPDxYCHgdWaXNpYmxlaGRkAgEPDxYCHgtOYXZpZ2F0ZVVybAURfi9QZXJzb25hbENhYmluZXRkZAIED2QWAgIPDw8WAh8AZ2RkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYCBRhjdGwwMCRMb2dpblZpZXcxJEMkY3RsMDEFGGN0bDAwJExvZ2luVmlldzEkQyRjdGwwM8jyBah6a44sUhrmeMI8AsUPqLcGRvR8vj5cTZlgW5TB",
        "__VIEWSTATEGENERATOR" to "8DE3F183",
        "__EVENTVALIDATION" to "%2FwEdAAY1bt0fHbYdUSGrHwvjylbCFosInJihxEpAm2uIcy09t4KMm1Qfjr3rMxTvG7XqI5AyBPD0VAHpQ0PPs5Wkz%2FZ0pXYHJ69LsJtRXvNKjrkjqKy9D5mzsrekFoYfYfkWr2N4Z5o20hnzZTuFWUauszVpjLCnHK12cd03%2FlpH6q5kLg%3D%3D" ,
        "ctl00%24ContentPlaceHolder0%24txtUserLogin" to login,
        "ctl00%24ContentPlaceHolder0%24txtUserPassword" to stud,
        "ctl00%24ContentPlaceHolder0%24txtCapture" to captcha,
        "ctl00%24ContentPlaceHolder0%24btnLogon" to "%D0%92%D0%BE%D0%B9%D1%82%D0%B8"
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
        @FieldMap(encoded = true) body : FormUrlEncodedBody
    ) : Response<ResponseBody>
}