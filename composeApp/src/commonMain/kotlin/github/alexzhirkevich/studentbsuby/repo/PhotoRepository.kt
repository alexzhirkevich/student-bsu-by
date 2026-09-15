package github.alexzhirkevich.studentbsuby.repo

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import github.alexzhirkevich.studentbsuby.api.ProfileApi


class PhotoRepository(
    private val profileApi: ProfileApi,
    private val usernameProvider: UsernameProvider,
    ) : CacheWebRepository<ImageBitmap>() {

    override suspend fun getFromCache(): ImageBitmap? = kotlin.runCatching {
            usernameProvider.username.takeIf(String::isNotBlank)?.let {
                readCachedPhoto(getCachedPhotoName(it))?.let {
                    it.decodeToImageBitmap()
                }
            }
        }.getOrNull()

    override suspend fun getFromWeb(): ImageBitmap? {
        profileApi.photo().bytes().let {
            return kotlin.runCatching { it.decodeToImageBitmap() }.getOrNull()
        }
    }

    override suspend fun saveToCache(value: ImageBitmap) {
        kotlin.runCatching {
            usernameProvider.username.takeIf(String::isNotBlank)?.let { username ->
                writeCachedPhoto(getCachedPhotoName(username), value.encodeToJpegBytes())
            }
        }
    }

    private fun getCachedPhotoName(username: String): String {
        return username.filter(Char::isLetterOrDigit)
    }
}
