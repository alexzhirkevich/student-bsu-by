package github.alexzhirkevich.studentbsuby.dao

import androidx.room.RoomDatabase

internal const val APP_DATABASE_NAME = "db_cache"

fun createAppDatabase(builder: RoomDatabase.Builder<AppDatabase>): AppDatabase =
    builder
        .fallbackToDestructiveMigration(dropAllTables = false)
        .build()
