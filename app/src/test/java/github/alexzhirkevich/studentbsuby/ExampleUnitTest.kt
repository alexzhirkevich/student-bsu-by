package github.alexzhirkevich.studentbsuby

import android.content.Context
import android.util.Log
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.data.models.User
import github.alexzhirkevich.studentbsuby.repo.FacultyTimetableProvider
import github.alexzhirkevich.studentbsuby.repo.FpmTimetableProvider
import github.alexzhirkevich.studentbsuby.util.PreferencesCookieCache
import io.harkema.retrofitcurlprinter.Logger
import io.harkema.retrofitcurlprinter.RetrofitCurlPrinterInterceptor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun fpmTimetableTest() {
        runBlocking {
            val client = provideHttpClient()
            FacultyTimetableProvider.forUser(
                User(faculty = FpmTimetableProvider.NAME, info = "1 курс, 9 группа"), client
            ).get()
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
//            .cookieJar(cookieJar)
            .followRedirects(false)
            .followSslRedirects(false)
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
//            .addNetworkInterceptor(Interceptor {
//                val request = it.request().newBuilder()
//                    .addHeader("Connection", "close").build()
//                it.proceed(request)
//            })
//            .let {
//                if (BuildConfig.DEBUG) {
//                    it.addInterceptor(HttpLoggingInterceptor(
//                        object : HttpLoggingInterceptor.Logger {
//                            override fun log(message: String) {
//                                Log.e("log", message)
//                            }
//
//                        }
//                    ).apply { level = HttpLoggingInterceptor.Level.HEADERS }
//                    ).addInterceptor(RetrofitCurlPrinterInterceptor(object : Logger {
//                        override fun log(message: String) {
//                            Log.e("CURL", message)
//                        }
//                    }))
//                } else it
//            }
            .build()
    }
}