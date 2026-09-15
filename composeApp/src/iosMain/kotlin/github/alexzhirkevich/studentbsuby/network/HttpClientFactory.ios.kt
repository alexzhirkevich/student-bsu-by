package github.alexzhirkevich.studentbsuby.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.darwin.DarwinClientEngineConfig

// The Darwin engine never follows redirects natively (its NSURLSession delegate
// cancels them), so redirect handling is fully controlled by followRedirects = false.
// Native cookie handling is disabled entirely: Ktor's HttpCookies plugin owns cookies.
actual fun httpClientEngine() : HttpClientEngineFactory<*> =
    object : HttpClientEngineFactory<DarwinClientEngineConfig> {
        override fun create(block: DarwinClientEngineConfig.() -> Unit): HttpClientEngine =
            Darwin.create {
                block()
                configureSession {
                    setHTTPShouldSetCookies(false)
                    setHTTPCookieStorage(null)
                }
            }
    }
