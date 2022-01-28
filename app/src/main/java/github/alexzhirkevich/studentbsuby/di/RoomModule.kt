package github.alexzhirkevich.studentbsuby.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.alexzhirkevich.studentbsuby.dao.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "db_cache")
            .build()


    @Singleton
    @Provides
    fun provideUsersDao(appDatabase: AppDatabase) : UsersDao = appDatabase.userDao()

    @Singleton
    @Provides
    fun provideSubjectsDao(appDatabase: AppDatabase) : SubjectsDao = appDatabase.subjectsDao()

    @Singleton
    @Provides
    fun provideLessonsDao(appDatabase: AppDatabase) : LessonsDao = appDatabase.lessonsDao()

    @Singleton
    @Provides
    fun provideHostelDao(appDatabase: AppDatabase) : HostelDao = appDatabase.hostelDao()
}