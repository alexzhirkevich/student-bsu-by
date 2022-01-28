package github.alexzhirkevich.studentbsuby.repo

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.api.ProfileApi
import github.alexzhirkevich.studentbsuby.dao.UsersDao
import github.alexzhirkevich.studentbsuby.data.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.jsoup.Jsoup
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val loginRepository: LoginRepository,
    private val profileApi: ProfileApi,
    private val usersDao: UsersDao,
    private val preferences: SharedPreferences,
    @ApplicationContext private val context : Context
) {

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun user(): Flow<User> = flowOf(
        flow {
            getUserFromCache().getOrNull()?.let {
                emit(it)
            }
        },
        flow {
            getUserFromWeb().getOrNull()?.let {
                emit(it)
                saveUserToCache(it)
            }
        }).flattenConcat()

    @FlowPreview
    @ExperimentalCoroutinesApi
    suspend fun photo(): Flow<Bitmap> = flowOf(
        flow {
            getPhotoFromCache().getOrNull()?.let {
                emit(it)
            }
        },
        flow {
            getPhotoFromWeb().getOrNull()?.let {
                emit(it)
                savePhotoToCache(it)
            }
        }
    ).flattenConcat()

    private suspend fun getUserFromCache() = kotlin.runCatching {
        val login = loginRepository.cachedLogin
        if (login.isNotBlank()) {
            usersDao.get(login)?.let {
                return@runCatching it
            }
        }
        null
    }

    private suspend fun getUserFromWeb() = kotlin.runCatching {
        val resp = profileApi.studProgress()
        if (!resp.isSuccessful)
            return@runCatching null

        val bytes = resp.body()?.byteStream()?.readBytes()
            ?: return@runCatching null
        val doc = Jsoup.parse(String(bytes))

        val name = doc.getElementById("ctl00_ctl00_ContentPlaceHolder0_lbFIO1").text()
        val faculty =
            doc.getElementById("ctl00_ctl00_ContentPlaceHolder0_ContentPlaceHolder1_ctlStudProgress1_lbStudFacultet")
                .text()
        val info =
            doc.getElementById("ctl00_ctl00_ContentPlaceHolder0_ContentPlaceHolder1_ctlStudProgress1_lbStudKurs")
                .text()
        val avg =
            doc.getElementById("ctl00_ctl00_ContentPlaceHolder0_ContentPlaceHolder1_ctlStudProgress1_lbStudBall")
                .text()

        val u = User(
            username = loginRepository.cachedLogin,
            name = name,
            faculty = faculty,
            info = info,
            avgGrade = avg
        )
        return@runCatching u
    }

    private suspend fun saveUserToCache(user: User) = kotlin.runCatching {
        usersDao.insert(user)
    }

    private fun getCachedPhotoLocation(username: String): File {
        return File(context.cacheDir, username.filter(Char::isLetterOrDigit))
    }

    private suspend fun getPhotoFromWeb() = kotlin.runCatching {
        val body = profileApi.photo().body()?.byteStream()?.readBytes()
            ?: return@runCatching null
        return@runCatching (BitmapFactory.decodeByteArray(body, 0, body.size))
    }

    private fun savePhotoToCache(photo: Bitmap) = kotlin.runCatching {
        loginRepository.cachedLogin.takeIf(String::isNotBlank)?.let { username ->
            FileOutputStream(getCachedPhotoLocation(username)).use {
                photo.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
        }
    }

    private fun getPhotoFromCache() = kotlin.runCatching {
        val username = loginRepository.cachedLogin
        if (username.isNotEmpty()) {
            val cachedPhoto = getCachedPhotoLocation(username)
            if (cachedPhoto.exists()) {
                return@runCatching (BitmapFactory.decodeFile(cachedPhoto.path))
            }
        }
        null
    }
}