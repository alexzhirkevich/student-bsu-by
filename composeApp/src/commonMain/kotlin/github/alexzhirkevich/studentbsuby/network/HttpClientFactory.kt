package github.alexzhirkevich.studentbsuby.network

import github.alexzhirkevich.studentbsuby.util.PlatformInfo
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header

expect fun httpClientEngine() : HttpClientEngineFactory<*>

fun createHttpClient(
    cookiesStorage: CookiesStorage,
    platformInfo: PlatformInfo
) : HttpClient = HttpClient(httpClientEngine()) {

    followRedirects = false

    install(HttpCookies) {
        storage = cookiesStorage
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 30_000L
        connectTimeoutMillis = 30_000L
        socketTimeoutMillis = 30_000L
    }

    defaultRequest {
        url("https://student.bsu.by/")
        header("Connection", "close")
        header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
    }

    if (platformInfo.isDebug) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.BODY
        }
    }
}
