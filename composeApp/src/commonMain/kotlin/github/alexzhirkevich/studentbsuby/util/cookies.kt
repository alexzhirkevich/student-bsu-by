package github.alexzhirkevich.studentbsuby.util

import com.russhwolf.settings.ObservableSettings
import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.CookieEncoding
import io.ktor.http.Url
import io.ktor.http.fromCookieToGmtDate
import io.ktor.http.toHttpDate
import io.ktor.util.date.GMTDate
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface LoginCookieManager {

    fun getCookies() : String

    fun cleanCookies()

    fun canRestoreSession() : Boolean
}

/**
 * Persists cookies per domain as sets of Set-Cookie strings
 * ("name=value; expires=...; domain=...; path=...") — the same format the old
 * okhttp cookie jar stored in SharedPreferences.
 */
class PersistentCookiesStorage(
    private val settings: ObservableSettings
) : CookiesStorage, LoginCookieManager {

    override suspend fun get(requestUrl: Url): List<Cookie> {
        val now = GMTDate().timestamp
        return cookies
            .filter { !it.isExpired(now) && it.matches(requestUrl) }
            .map { it.toCookie() }
    }

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        val stored = StoredCookie.from(cookie, requestUrl)
        val oldCookies = domainCookies(stored.domain).map {
            it.substringBefore("=") to it
        }.toMap().toMutableMap()
        oldCookies[stored.name] = stored.serialize()

        settings.putString(stored.domain, Json.encodeToString(oldCookies.values.toList()))
    }

    override fun close() {
    }

    override fun getCookies(): String {
        return cookies.joinToString(separator = "; ") { "${it.name}=${it.value}" }
    }

    override fun cleanCookies() {
        settings.clear()
    }

    override fun canRestoreSession(): Boolean {
        return settings.keys.any {
            rawValue(it)?.contains("AuthCookie") == true
        }
    }

    private val cookies: List<StoredCookie>
        get() = settings.keys.flatMap { domain ->
            domainCookies(domain).mapNotNull { StoredCookie.parse(domain, it) }
        }

    private fun domainCookies(domain: String): List<String> =
        rawValue(domain)?.let {
            runCatching { Json.decodeFromString<List<String>>(it) }.getOrNull()
        }.orEmpty()

    private fun rawValue(key: String): String? =
        runCatching { settings.getStringOrNull(key) }.getOrNull()
}

private class StoredCookie(
    val name: String,
    val value: String,
    val expiresAt: Long?,
    val domain: String,
    val hostOnly: Boolean,
    val path: String,
    val secure: Boolean,
    val httpOnly: Boolean,
) {

    fun isExpired(now: Long): Boolean = expiresAt != null && expiresAt < now

    fun matches(url: Url): Boolean {
        val urlHost = url.host.lowercase()
        val domainMatches = if (hostOnly) urlHost == domain else domainMatch(urlHost, domain)
        if (!domainMatches)
            return false
        if (!pathMatch(url.encodedPath.ifEmpty { "/" }, path))
            return false
        if (secure && url.protocol.name != "https")
            return false
        return true
    }

    fun toCookie(): Cookie = Cookie(
        name = name,
        value = value,
        encoding = CookieEncoding.RAW,
        expires = expiresAt?.let { GMTDate(it) },
        domain = domain,
        path = path,
        secure = secure,
        httpOnly = httpOnly,
    )

    fun serialize(): String = buildString {
        append(name).append('=').append(value)
        expiresAt?.let {
            append("; expires=").append(GMTDate(it).toHttpDate())
        }
        if (!hostOnly) {
            append("; domain=").append(domain)
        }
        append("; path=").append(path)
        if (secure) {
            append("; secure")
        }
        if (httpOnly) {
            append("; httponly")
        }
    }

    companion object {

        fun from(cookie: Cookie, requestUrl: Url): StoredCookie {
            val domainAttr = cookie.domain?.trim()?.trimStart('.')
                ?.lowercase()?.takeIf(String::isNotEmpty)
            return StoredCookie(
                name = cookie.name,
                value = cookie.value,
                expiresAt = cookie.maxAge?.let {
                    if (it > 0) GMTDate().timestamp + it * 1000L else 0L
                } ?: cookie.expires?.timestamp,
                domain = domainAttr ?: requestUrl.host.lowercase(),
                hostOnly = domainAttr == null,
                path = cookie.path?.takeIf { it.startsWith("/") } ?: "/",
                secure = cookie.secure,
                httpOnly = cookie.httpOnly,
            )
        }

        fun parse(domain: String, setCookie: String): StoredCookie? {
            val parts = setCookie.split(';')
            val nameValue = parts.first()
            val eq = nameValue.indexOf('=')
            if (eq == -1)
                return null
            val name = nameValue.substring(0, eq).trim()
            if (name.isEmpty())
                return null
            val value = nameValue.substring(eq + 1).trim()

            var expiresAt: Long? = null
            var hasMaxAge = false
            var domainAttr: String? = null
            var path: String? = null
            var secure = false
            var httpOnly = false

            parts.drop(1).forEach { part ->
                val attr = part.trim()
                val eqi = attr.indexOf('=')
                val key = (if (eqi == -1) attr else attr.substring(0, eqi)).trim()
                val v = if (eqi == -1) "" else attr.substring(eqi + 1).trim()
                when {
                    key.equals("expires", ignoreCase = true) -> if (!hasMaxAge) {
                        expiresAt = runCatching {
                            v.fromCookieToGmtDate().timestamp
                        }.getOrNull() ?: expiresAt
                    }
                    key.equals("max-age", ignoreCase = true) -> {
                        v.toLongOrNull()?.let { seconds ->
                            hasMaxAge = true
                            expiresAt = if (seconds > 0)
                                GMTDate().timestamp + seconds * 1000L else 0L
                        }
                    }
                    key.equals("domain", ignoreCase = true) ->
                        v.trimStart('.').lowercase()
                            .takeIf(String::isNotEmpty)?.let { domainAttr = it }
                    key.equals("path", ignoreCase = true) ->
                        if (v.startsWith("/")) path = v
                    key.equals("secure", ignoreCase = true) -> secure = true
                    key.equals("httponly", ignoreCase = true) -> httpOnly = true
                }
            }

            val explicitDomain = domainAttr
            if (explicitDomain != null && !domainMatch(domain, explicitDomain))
                return null

            return StoredCookie(
                name = name,
                value = value,
                expiresAt = expiresAt,
                domain = explicitDomain ?: domain,
                hostOnly = explicitDomain == null,
                path = path ?: "/",
                secure = secure,
                httpOnly = httpOnly,
            )
        }
    }
}

private fun domainMatch(urlHost: String, domain: String): Boolean {
    if (urlHost == domain)
        return true
    return urlHost.endsWith(domain) &&
            urlHost[urlHost.length - domain.length - 1] == '.'
}

private fun pathMatch(urlPath: String, path: String): Boolean {
    if (urlPath == path)
        return true
    if (urlPath.startsWith(path)) {
        if (path.endsWith("/"))
            return true
        if (urlPath.getOrNull(path.length) == '/')
            return true
    }
    return false
}
