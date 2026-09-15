package github.alexzhirkevich.studentbsuby.util

import android.content.SharedPreferences
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Rewrites the legacy okhttp cookie jar entries ("<domain>" -> StringSet of
 * Set-Cookie strings) into the JSON format used by [PersistentCookiesStorage].
 * Must run on the cookies preferences before they are wrapped into Settings.
 */
fun migrateLegacyCookies(preferences: SharedPreferences) {
    kotlin.runCatching {
        val legacy = preferences.all.mapNotNull { entry ->
            (entry.value as? Set<*>)?.let { set ->
                entry.key to set.filterIsInstance<String>()
            }
        }
        if (legacy.isEmpty())
            return

        val editor = preferences.edit()
        legacy.forEach { (key, cookies) ->
            editor.putString(key, Json.encodeToString(cookies))
        }
        editor.apply()
    }
}
