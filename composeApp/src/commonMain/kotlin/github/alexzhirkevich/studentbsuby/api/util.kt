package github.alexzhirkevich.studentbsuby.api

typealias FormUrlEncodedBody = Map<String,String>

fun String.isSessionExpired() : Boolean =
    contains("ctl00_ContentPlaceHolder0_cmdLogIn") ||
            contains("ctl00\$ContentPlaceHolder0\$btnLogon")
