package github.alexzhirkevich.studentbsuby.repo

import com.russhwolf.settings.ObservableSettings
import github.alexzhirkevich.studentbsuby.services.firebase.RemoteConfigClient
import github.alexzhirkevich.studentbsuby.util.PlatformInfo
import github.alexzhirkevich.studentbsuby.util.sharedPreferences
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

private const val G_PLAY = "google_play"
private const val TELEGRAM = "telegram"
private const val MAIL = "mail"
private const val STABLE_VER = "stable_version"
private const val LATEST_VER = "latest_version"
private const val VER_CODE = "code"
private const val VER_NAME = "name"
private const val VER_DESC = "desc"

private const val UPDATE_PROP_DELAY =  3 * 24 * 60 * 60 * 1000L

private val json = Json { isLenient = true }

data class ApplicationVersion(
    val code : Int,
    val name : String,
    val desc : String
)

@OptIn(ExperimentalTime::class)
class RemoteConfigRepository(
    preferences: ObservableSettings,
    private val remoteConfigClient: RemoteConfigClient,
    private val platformInfo: PlatformInfo,
) {

    private var lastUpdateProp by sharedPreferences(preferences, 0L)

    suspend fun update(){
        check(remoteConfigClient.fetchAndActivate()) {
            "Failed to fetch and activate remote config"
        }
    }

    private suspend fun getVersion(name: String) : ApplicationVersion? = kotlin.runCatching {
        val version = json.parseToJsonElement(remoteConfigClient.getString(name)).jsonObject
        update()
        return ApplicationVersion(
            code = version.getValue(VER_CODE).jsonPrimitive.int,
            name = version.getValue(VER_NAME).jsonPrimitive.content,
            desc = version.getValue(VER_DESC).jsonPrimitive.content
        )
    }.getOrNull()

    suspend fun getLatestVersionIfNeeded() : ApplicationVersion? =
        getVersion(LATEST_VER)?.takeIf {
            platformInfo.versionCode < it.code &&
                    Clock.System.now().toEpochMilliseconds() - lastUpdateProp >= UPDATE_PROP_DELAY
        }.also { lastUpdateProp = Clock.System.now().toEpochMilliseconds() }

    suspend fun getMinimumStableVersionIfNeeded() : ApplicationVersion? =
        getVersion(STABLE_VER)?.takeIf { platformInfo.versionCode < it.code }

    fun telegram() : String = kotlin.runCatching{
       return  remoteConfigClient.getString(TELEGRAM)
    }.getOrNull().orEmpty()

    fun mail() : String = kotlin.runCatching {
        remoteConfigClient.getString(MAIL)
    }.getOrNull().orEmpty()

}
