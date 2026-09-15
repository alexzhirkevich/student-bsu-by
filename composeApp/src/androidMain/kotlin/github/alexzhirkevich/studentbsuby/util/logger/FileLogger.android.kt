package github.alexzhirkevich.studentbsuby.util.logger

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.os.Build
import android.util.Log
import androidx.core.content.FileProvider
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.share_logs
import github.alexzhirkevich.studentbsuby.util.AndroidAppContext
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import java.io.File

internal actual fun platformLogW(tag: String, msg: String, cause: Throwable?) {
    Log.w(tag, msg, cause)
}

internal actual fun platformLogE(tag: String, msg: String, cause: Throwable?) {
    Log.e(tag, msg, cause)
}

internal actual fun <T> logSynchronized(block: () -> T): T =
    synchronized(FileLogger::class) {
        block()
    }

actual class LogFileSystem actual constructor() {

    private val logDir = File(AndroidAppContext.context.getExternalFilesDir(null), "Debug")
    private val logFile = File(logDir, "Logs.txt")

    actual val logFilePath: String get() = logFile.absolutePath

    actual fun createLogDir() {
        logDir.mkdirs()
    }

    actual fun logFileExists(): Boolean = logFile.exists()

    actual fun createLogFile() {
        logFile.createNewFile()
    }

    actual fun appendText(text: String) {
        logFile.appendText(text)
    }

    actual fun deviceMetadata(): String = buildString {
        append("Device: ${Build.DEVICE}\n")
        append("Brand: ${Build.BRAND}\n")
        append("Model: ${Build.MODEL}\n")
        append("Manufacturer: ${Build.MANUFACTURER}\n")
        append("Display: ${Build.DISPLAY}\n")
        append("API: ${Build.VERSION.SDK_INT}\n")
    }

    actual fun share() {
        val context = AndroidAppContext.context
        val uri = FileProvider.getUriForFile(context, context.packageName + ".provider", logFile)
        Intent(Intent.ACTION_SEND).apply {
            type = "application/txt"
            flags = FLAG_GRANT_READ_URI_PERMISSION
            putExtra(Intent.EXTRA_STREAM, uri)
        }.let {
            context.startActivity(
                Intent.createChooser(
                    it,
                    runBlocking { getString(Res.string.share_logs) }
                ).apply {
                    flags = FLAG_ACTIVITY_NEW_TASK
                }
            )
        }
    }
}
