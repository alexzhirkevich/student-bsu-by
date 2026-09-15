package github.alexzhirkevich.studentbsuby.dao

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import github.alexzhirkevich.studentbsuby.data.models.*

@Database(
    entities = [
    User::class,
    Subject::class,
    Lesson::class,
    HostelAdvert::class,
    PaidServicesInfo::class,
    Bill::class,
    TuitionFeePayment::class,
    Receipt::class,
    News::class,
    NewsContent::class
               ],
    exportSchema = false,
    version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UsersDao

    abstract fun subjectsDao(): SubjectsDao

    abstract fun lessonsDao(): LessonsDao

    abstract fun hostelDao(): HostelDao

    abstract fun paidServicesDao(): PaidServicesDao

    abstract fun newsDao() : NewsDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT", "KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
