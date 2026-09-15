package github.alexzhirkevich.studentbsuby.util.logger

import github.alexzhirkevich.studentbsuby.util.PlatformInfo
import github.alexzhirkevich.studentbsuby.util.platformInfo
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

open class DefaultLogger : Logger {

    override fun log(msg: String, tag : String, logLevel: Logger.LogLevel, cause: Throwable?) {
        when (logLevel) {
            Logger.LogLevel.Warning -> platformLogW(tag, msg, cause)
            Logger.LogLevel.Error -> platformLogE(tag, msg, cause)
        }
    }

    override fun share() {

    }
}

class FileLogger constructor(
    private val platformInfo: PlatformInfo = platformInfo()
) : DefaultLogger() {

    private val files = LogFileSystem()

    init {
        logSynchronized {
            tryInit()
        }
    }

    override fun log(msg: String, tag: String, logLevel: Logger.LogLevel, cause : Throwable?) {

        if (platformInfo.isDebug) {
            super.log(msg, tag, logLevel, cause)
        }

        logSynchronized {
            tryInit()
            kotlin.runCatching {
                files.appendText(
                    buildString {
                        append(timestamp())
                        append(" \\\\ ${logLevel.name.take(1)}\n")
                        append("$tag: $msg\n")
                        if (cause != null && logLevel == Logger.LogLevel.Error)
                            append(cause.stackTraceToString())
                        append("\n")
                    }
                )
            }
        }
    }

    private fun tryInit() {
        kotlin.runCatching {
            files.createLogDir()
            if (!files.logFileExists()) {
                files.createLogFile()
                files.appendText(buildString {
                    append(files.deviceMetadata())
                    append("vc: ${platformInfo.versionCode}\n")
                    append("v: ${platformInfo.versionName}\n")
                    append("\n")
                })
            }
        }
    }

    // Reproduces the legacy SimpleDateFormat("[yyyy-mm-dd hh:mm:ss]") pattern
    // (minutes in the month slot, 12-hour clock) so log files stay consistent.
    @OptIn(ExperimentalTime::class)
    private fun timestamp(): String {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        fun pad(value: Int) = value.toString().padStart(2, '0')
        val hour = ((now.hour + 11) % 12) + 1
        return "[${now.year}-${pad(now.minute)}-${pad(now.day)} ${pad(hour)}:${pad(now.minute)}:${pad(now.second)}]"
    }

    override fun share() {
        kotlin.runCatching {
            files.share()
        }
    }
}

internal expect fun platformLogW(tag: String, msg: String, cause: Throwable?)

internal expect fun platformLogE(tag: String, msg: String, cause: Throwable?)

internal expect fun <T> logSynchronized(block: () -> T): T

expect class LogFileSystem() {

    val logFilePath: String

    fun createLogDir()

    fun logFileExists(): Boolean

    fun createLogFile()

    fun appendText(text: String)

    fun deviceMetadata(): String

    fun share()
}
