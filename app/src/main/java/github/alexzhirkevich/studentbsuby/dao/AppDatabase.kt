package github.alexzhirkevich.studentbsuby.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import github.alexzhirkevich.studentbsuby.data.models.HostelAdvert
import github.alexzhirkevich.studentbsuby.data.models.Lesson
import github.alexzhirkevich.studentbsuby.data.models.Subject
import github.alexzhirkevich.studentbsuby.data.models.User

@Database(entities = [
    User::class,
    Subject::class,
    Lesson::class,
    HostelAdvert::class
                     ],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UsersDao

    abstract fun subjectsDao(): SubjectsDao

    abstract fun lessonsDao() : LessonsDao

    abstract fun hostelDao() : HostelDao
}