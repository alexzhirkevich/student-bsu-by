package github.alexzhirkevich.studentbsuby.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.preference.PreferenceManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.alexzhirkevich.studentbsuby.BuildConfig
import github.alexzhirkevich.studentbsuby.repo.UsernameProvider
import github.alexzhirkevich.studentbsuby.repo.UsernameProviderImpl
import github.alexzhirkevich.studentbsuby.util.CaptchaRecognizer
import github.alexzhirkevich.studentbsuby.util.CaptchaRecognizerImpl
import github.alexzhirkevich.studentbsuby.util.logger.FileLogger
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class Encrypted

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCaptureRecognizer() : CaptchaRecognizer = CaptchaRecognizerImpl()

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) : SharedPreferences {
       return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Encrypted
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context) : SharedPreferences {
        return kotlin.runCatching {
            EncryptedSharedPreferences.create(
                context,
                BuildConfig.APPLICATION_ID + "_encryped",
                MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .setUserAuthenticationRequired(false)
                    .build(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }.getOrElse { provideSharedPreferences(context) }
    }

    @Provides
    @Singleton
    fun provideLogger(@ApplicationContext context: Context)
        : github.alexzhirkevich.studentbsuby.util.logger.Logger = FileLogger(context)

    @Provides
    fun provideResources(@ApplicationContext context: Context) : Resources = context.resources

    @Provides
    fun provideWorkManager(@ApplicationContext context: Context) : WorkManager =
        WorkManager.getInstance(context)

    @Provides
    fun provideUsernameProvider(preferences: SharedPreferences) : UsernameProvider =
        UsernameProviderImpl(preferences)

}
