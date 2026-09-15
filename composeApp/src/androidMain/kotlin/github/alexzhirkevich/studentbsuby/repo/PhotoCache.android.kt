package github.alexzhirkevich.studentbsuby.repo

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import github.alexzhirkevich.studentbsuby.util.AndroidAppContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

private val cacheDir: File
    get() = AndroidAppContext.context.cacheDir

internal actual fun readCachedPhoto(fileName: String): ByteArray? =
    File(cacheDir, fileName).takeIf(File::exists)?.readBytes()

internal actual fun writeCachedPhoto(fileName: String, bytes: ByteArray) {
    FileOutputStream(File(cacheDir, fileName)).use {
        it.write(bytes)
    }
}

internal actual fun ImageBitmap.encodeToJpegBytes(): ByteArray =
    ByteArrayOutputStream().use {
        asAndroidBitmap().compress(Bitmap.CompressFormat.JPEG, 100, it)
        it.toByteArray()
    }
