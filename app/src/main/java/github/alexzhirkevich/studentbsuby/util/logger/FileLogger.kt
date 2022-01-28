package github.alexzhirkevich.studentbsuby.util.logger

import android.content.Context
import android.os.Build
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.BuildConfig
import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

open class DefaultLogger : Logger {

    override fun log(msg: String, tag : String, logLevel: Logger.LogLevel, cause: Throwable?) {
        when (logLevel) {
            Logger.LogLevel.Warning -> Log.w(tag, msg, cause)
            Logger.LogLevel.Error -> Log.e(tag, msg, cause)
        }
    }
}

class FileLogger constructor(
    context : Context
) : DefaultLogger() {

    private val logDir = File(context.getExternalFilesDir(null), "Debug")
    private val logFile = File(logDir, "Logs.txt")

    private val timestampDateFormat: DateFormat = SimpleDateFormat(
        "[yyyy-mm-dd hh:mm:ss]", Locale.getDefault()
    )

    init {
        synchronized(FileLogger::class) {
            tryInit()
        }
    }

    override fun log(msg: String, tag: String, logLevel: Logger.LogLevel, cause : Throwable?) {

        if (BuildConfig.DEBUG) {
            super.log(msg, tag, logLevel, cause)
        }

        synchronized(FileLogger::class) {
            tryInit()
            kotlin.runCatching {
                logFile.appendText(
                    buildString {
                        append(timestampDateFormat.format(Date()))
                        append(" \\\\ ${logLevel.name.take(1)}\n")
                        append("$tag: $msg")
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
            logDir.mkdirs()
            if (!logFile.exists()) {
                logFile.createNewFile()
                logFile.appendText(buildString {
                    append("Device: ${Build.DEVICE}\n")
                    append("Model: ${Build.MODEL}\n")
                    append("Product: ${Build.PRODUCT}\n")
                    append("API: ${Build.VERSION.SDK_INT}\n")
                    append("\n")
                })
            }
        }
    }
}