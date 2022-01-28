package github.alexzhirkevich.studentbsuby.di

import android.content.Context
import android.util.Log
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.alexzhirkevich.studentbsuby.BuildConfig
import github.alexzhirkevich.studentbsuby.api.LoginApi
import github.alexzhirkevich.studentbsuby.api.ProfileApi
import github.alexzhirkevich.studentbsuby.api.TimetableApi
import github.alexzhirkevich.studentbsuby.util.LoginCookieManager
import github.alexzhirkevich.studentbsuby.util.PreferencesCookieCache
import io.harkema.retrofitcurlprinter.Logger
import io.harkema.retrofitcurlprinter.RetrofitCurlPrinterInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    private lateinit var loginCookieManager: LoginCookieManager

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        val cache  = PreferencesCookieCache(context)
        val cookieJar = PersistentCookieJar(cache, SharedPrefsCookiePersistor(context))

        loginCookieManager = cache

        val httpClient = OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .followRedirects(false)
            .followSslRedirects(false)
//            .cache(Cache(context.cacheDir,1024*1024*256))
            .retryOnConnectionFailure(true)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor { chain ->
//                val resp = chain.proceed(chain.request())
//                val url = chain.request().url
//
//                val cookies = resp.headers("Set-Cookie").mapNotNull {
//                    Cookie.parse(url,it)
//                }
//                cookieJar.saveFromResponse(url,cookies)
//                resp
//            }
            .addInterceptor {
                val cookies = cookieJar.loadForRequest(it.request().url)
                val sCookies = cookies.joinToString(separator = "; ") { it.name + '=' + it.value }
                val newReq = it.request().newBuilder()
                    .let {
                        if (sCookies.isNotEmpty())
                            it.addHeader("Cookie", sCookies) else it
                    }
                    .build()
                it.proceed(newReq)
            }
            .addNetworkInterceptor( Interceptor {
                val request = it.request().newBuilder()
                    .addHeader("Connection", "close").build();
                it.proceed(request);
            })
            .let {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(HttpLoggingInterceptor(
                        object : HttpLoggingInterceptor.Logger {
                            override fun log(message: String) {
                                Log.e("log", message)
                            }

                        }
                    ).apply { level = HttpLoggingInterceptor.Level.BODY }
                    ).addInterceptor(RetrofitCurlPrinterInterceptor(object : Logger {
                        override fun log(message: String) {
                            Log.e("CURL", message)
                        }
                    }))
                } else it
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://student.bsu.by")
            .client(httpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideCookieCleaner() = loginCookieManager

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit) : LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit) : ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTimetableApi(retrofit: Retrofit) : TimetableApi {
        return retrofit.create(TimetableApi::class.java)
    }
}