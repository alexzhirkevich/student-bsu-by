package github.alexzhirkevich.studentbsuby.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.alexzhirkevich.studentbsuby.util.CaptchaRecognizer
import github.alexzhirkevich.studentbsuby.util.CaptchaRecognizerImpl
import github.alexzhirkevich.studentbsuby.util.logger.FileLogger
import javax.inject.Singleton

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
    @Singleton
    fun provideLogger(@ApplicationContext context: Context): github.alexzhirkevich.studentbsuby.util.logger.Logger = FileLogger(context)

    @Provides
    fun provideResources(@ApplicationContext context: Context) : Resources = context.resources
}