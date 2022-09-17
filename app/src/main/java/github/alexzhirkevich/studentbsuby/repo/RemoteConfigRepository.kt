package github.alexzhirkevich.studentbsuby.repo

import android.content.SharedPreferences
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import github.alexzhirkevich.studentbsuby.BuildConfig
import github.alexzhirkevich.studentbsuby.util.sharedPreferences
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONObject
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

private const val G_PLAY = "google_play"
private const val TELEGRAM = "telegram"
private const val MAIL = "mail"
private const val STABLE_VER = "stable_version"
private const val LATEST_VER = "latest_version"
private const val VER_CODE = "code"
private const val VER_NAME = "name"
private const val VER_DESC = "desc"
private const val API = "api"

private const val UPDATE_PROP_DELAY =  3 * 24 * 60 * 60 * 1000L

data class ApplicationVersion(
    val code : Int,
    val name : String,
    val desc : String
)

class RemoteConfigRepository @Inject constructor(
    preferences: SharedPreferences
) {

    private var lastUpdateProp by sharedPreferences(preferences, 0L)

    init {
        val config = FirebaseRemoteConfig.getInstance()

        val settings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(10)
            .build()

        config.setConfigSettingsAsync(settings)
    }

    suspend fun update(){
        FirebaseRemoteConfig.getInstance().fetchAndActivate().await()
    }

    private suspend fun getVersion(name: String) : ApplicationVersion? = kotlin.runCatching {
        val version = JSONObject(FirebaseRemoteConfig.getInstance().getString(name))
        update()
        return ApplicationVersion(
            code = version.getInt(VER_CODE),
            name = version.getString(VER_NAME),
            desc = version.getString(VER_DESC)
        )
    }.getOrNull()

    suspend fun getLatestVersionIfNeeded() : ApplicationVersion? =
        getVersion(LATEST_VER)?.takeIf {
            BuildConfig.VERSION_CODE < it.code &&
                    System.currentTimeMillis() - lastUpdateProp >= UPDATE_PROP_DELAY
        }.also { lastUpdateProp = System.currentTimeMillis() }

    suspend fun getMinimumStableVersionIfNeeded() : ApplicationVersion? =
        getVersion(STABLE_VER)?.takeIf { BuildConfig.VERSION_CODE < it.code }

    fun telegram() : String = kotlin.runCatching{
       return  FirebaseRemoteConfig.getInstance()
           .getString(TELEGRAM)
    }.getOrNull().orEmpty()

    fun mail() : String = kotlin.runCatching {
        FirebaseRemoteConfig.getInstance().getString(MAIL)
    }.getOrNull().orEmpty()

    fun api() = kotlin.runCatching {
        FirebaseRemoteConfig.getInstance().getString(API)
    }.getOrNull().orEmpty()
}

suspend fun <T> Task<T>.await() : T = suspendCancellableCoroutine { cont ->
    addOnCanceledListener {
        cont.cancel()
    }.addOnSuccessListener {
        if (it != null)
            cont.resume(it)
        else cont.cancel()
    }.addOnFailureListener {
        cont.resumeWithException(it)
    }
}