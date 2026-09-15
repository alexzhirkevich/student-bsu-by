package github.alexzhirkevich.studentbsuby.repo

import androidx.compose.ui.graphics.ImageBitmap

/**
 * Photo file cache primitives. The old app stored the photo as a JPEG file in
 * the app cache directory (context.cacheDir) named by the sanitized username.
 * Android keeps the exact same location so cached photos survive the update;
 * iOS uses NSCachesDirectory.
 */
internal expect fun readCachedPhoto(fileName: String): ByteArray?

internal expect fun writeCachedPhoto(fileName: String, bytes: ByteArray)

internal expect fun ImageBitmap.encodeToJpegBytes(): ByteArray
