package github.alexzhirkevich.studentbsuby.services.firebase

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class RemoteConfigClientImpl : RemoteConfigClient {

    init {
        kotlin.runCatching {
            val config = FirebaseRemoteConfig.getInstance()

            val settings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(10)
                .build()

            config.setConfigSettingsAsync(settings)
        }
    }

    override suspend fun fetchAndActivate(): Boolean = kotlin.runCatching {
        FirebaseRemoteConfig.getInstance().fetchAndActivate().await()
        true
    }.getOrDefault(false)

    override fun getString(key: String): String = kotlin.runCatching {
        FirebaseRemoteConfig.getInstance().getString(key)
    }.getOrDefault("")
}
