@file:OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)

package github.alexzhirkevich.studentbsuby.util.logger

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIDevice
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

internal actual fun platformLogW(tag: String, msg: String, cause: Throwable?) {
    println("W/$tag: $msg")
    cause?.let { println(it.stackTraceToString()) }
}

internal actual fun platformLogE(tag: String, msg: String, cause: Throwable?) {
    println("E/$tag: $msg")
    cause?.let { println(it.stackTraceToString()) }
}

private val fileLoggerLock = NSRecursiveLock()

internal actual fun <T> logSynchronized(block: () -> T): T {
    fileLoggerLock.lock()
    try {
        return block()
    } finally {
        fileLoggerLock.unlock()
    }
}

actual class LogFileSystem actual constructor() {

    private val logDir: String = run {
        val documents = NSSearchPathForDirectoriesInDomains(
            NSDocumentDirectory, NSUserDomainMask, true
        ).firstOrNull() as? String ?: ""
        "$documents/Debug"
    }

    actual val logFilePath: String get() = "$logDir/Logs.txt"

    actual fun createLogDir() {
        NSFileManager.defaultManager.createDirectoryAtPath(
            path = logDir,
            withIntermediateDirectories = true,
            attributes = null,
            error = null
        )
    }

    actual fun logFileExists(): Boolean =
        NSFileManager.defaultManager.fileExistsAtPath(logFilePath)

    actual fun createLogFile() {
        NSFileManager.defaultManager.createFileAtPath(
            path = logFilePath,
            contents = null,
            attributes = null
        )
    }

    actual fun appendText(text: String) {
        val data = NSString.create(string = text)
            .dataUsingEncoding(NSUTF8StringEncoding) ?: return
        val handle = NSFileHandle.fileHandleForWritingAtPath(logFilePath) ?: return
        try {
            handle.seekToEndOfFile()
            handle.writeData(data)
        } finally {
            handle.closeFile()
        }
    }

    actual fun deviceMetadata(): String = buildString {
        append("Device: ${UIDevice.currentDevice.name}\n")
        append("Brand: Apple\n")
        append("Model: ${UIDevice.currentDevice.model}\n")
        append("Manufacturer: Apple\n")
        append("Display: ${NSProcessInfo.processInfo.operatingSystemVersionString}\n")
        append("API: ${UIDevice.currentDevice.systemVersion}\n")
    }

    actual fun share() {
        val url = NSURL.fileURLWithPath(logFilePath)
        dispatch_async(dispatch_get_main_queue()) {
            val controller = UIActivityViewController(
                activityItems = listOf(url),
                applicationActivities = null
            )
            UIApplication.sharedApplication.keyWindow?.rootViewController
                ?.presentViewController(controller, animated = true, completion = null)
        }
    }
}
