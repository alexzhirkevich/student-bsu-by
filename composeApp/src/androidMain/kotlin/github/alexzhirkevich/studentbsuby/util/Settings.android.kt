package github.alexzhirkevich.studentbsuby.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

actual fun provideSettings(name: String): ObservableSettings =
    SharedPreferencesSettings(
        AndroidAppContext.context.getSharedPreferences(name, Context.MODE_PRIVATE)
    )

actual fun provideSecureSettings(name: String): ObservableSettings =
    SharedPreferencesSettings(
        kotlin.runCatching {
            createEncryptedPrefs(name, AndroidAppContext.context)
        }.getOrElse {
            defaultSharedPreferences(AndroidAppContext.context)
        }
    )

actual fun provideDefaultSettings(): ObservableSettings =
    SharedPreferencesSettings(defaultSharedPreferences(AndroidAppContext.context))

private fun createEncryptedPrefs(name : String, context: Context) =
    EncryptedSharedPreferences.create(
        context,
        name,
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .setUserAuthenticationRequired(false)
            .build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

private fun defaultSharedPreferences(context: Context) : SharedPreferences =
    context.getSharedPreferences(
        context.packageName + "_preferences",
        Context.MODE_PRIVATE
    ).also {
        kotlin.runCatching {
            if (it.contains("username") || it.contains("password")){
                it.edit()
                    .remove("username")
                    .remove("password")
                    .apply()
            }
        }
    }
