package github.alexzhirkevich.studentbsuby.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.franmontiel.persistentcookiejar.cache.CookieCache
import okhttp3.*

interface LoginCookieManager{
    fun cleanCookies()

    fun canRestoreSession() : Boolean
}

class PreferencesCookieCache(context: Context)
    : CookieCache, LoginCookieManager {

    private val prefs = context.getSharedPreferences(PREF_COOKIES, MODE_PRIVATE)

    override fun iterator(): MutableIterator<Cookie> = object : MutableIterator<Cookie> {

        private val iterator = prefs.cookies.iterator()

        private var last: Cookie? = null

        override fun hasNext(): Boolean =
            iterator.hasNext()


        override fun next(): Cookie = iterator.next().also { last = it }

        override fun remove() {
            last?.let {
                prefs.removeCookie(it)
            }
        }

    }

    override fun addAll(cookies: MutableCollection<Cookie>) {
        prefs.saveCookies(cookies)
    }

    override fun clear() {
        prefs.clearCookies()
    }

    override fun cleanCookies() {
        clear()
    }

    override fun canRestoreSession(): Boolean {
        return prefs.all.values.mapNotNull {
            it as? Set<*>
        }.flatten().any {
            (it as? String)?.contains("AuthCookie") == true
        }
    }
}

private val PREF_COOKIES = "PREF_COOKIES"


private val SharedPreferences.cookies: List<Cookie>
    get() = all.mapNotNull { entry ->
        (entry.value as? Set<*>)?.let { set ->
            set.mapNotNull {
                Cookie.parse(
                    HttpUrl.Builder().scheme("https").host(entry.key).build(), it as String
                )
            }
        }
    }.flatten()


private fun SharedPreferences.saveCookies(cookies: Collection<Cookie>) {

    cookies.groupBy { it.domain }.forEach { entry ->
        val oldCookies = (getStringSet(entry.key, emptySet())?.toMutableSet() ?: mutableSetOf()).map{
            it.substringBefore("=") to it
        }.toMap().toMutableMap()
        entry.value.forEach {
            oldCookies[it.name] = it.toString()
        }

        edit().putStringSet(entry.key, oldCookies.values.toSet()).apply()
    }
}

private fun SharedPreferences.removeCookie(cookie: Cookie){
    val set = (getStringSet(cookie.domain, emptySet()) ?: emptySet()).toMutableList()
    set.indexOf(cookie.toString()).takeIf { it>=0 }?.let { set.remove(set.removeAt(it)) }
    val cookies = set.mapNotNull {
        Cookie.parse(HttpUrl.Builder().host(cookie.domain).build(),it)
    }
    saveCookies(cookies)
}

private fun SharedPreferences.clearCookies(){
    edit().clear().apply()
}