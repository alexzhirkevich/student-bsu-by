@file:OptIn(ExperimentalForeignApi::class)

package github.alexzhirkevich.studentbsuby.repo

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import platform.Foundation.*
import platform.posix.memcpy

private val cachesDir: String
    get() = NSSearchPathForDirectoriesInDomains(
        NSCachesDirectory, NSUserDomainMask, true
    ).firstOrNull() as? String ?: ""

private fun photoPath(fileName: String): String = "$cachesDir/$fileName"

internal actual fun readCachedPhoto(fileName: String): ByteArray? {
    val path = photoPath(fileName)
    if (!NSFileManager.defaultManager.fileExistsAtPath(path))
        return null
    val data = NSData.dataWithContentsOfFile(path) ?: return null
    return data.toByteArray()
}

internal actual fun writeCachedPhoto(fileName: String, bytes: ByteArray) {
    bytes.toNSData().writeToFile(photoPath(fileName), atomically = true)
}

internal actual fun ImageBitmap.encodeToJpegBytes(): ByteArray =
    Image.makeFromBitmap(asSkiaBitmap())
        .encodeToData(EncodedImageFormat.JPEG, 100)!!
        .bytes

private fun NSData.toByteArray(): ByteArray {
    val size = length.toInt()
    val result = ByteArray(size)
    if (size > 0) {
        result.usePinned {
            memcpy(it.addressOf(0), bytes, length)
        }
    }
    return result
}

private fun ByteArray.toNSData(): NSData =
    if (isEmpty()) NSData()
    else usePinned {
        NSData.create(bytes = it.addressOf(0), length = size.toULong())
    }
