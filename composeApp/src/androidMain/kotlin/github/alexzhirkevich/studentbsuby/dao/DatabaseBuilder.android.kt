package github.alexzhirkevich.studentbsuby.dao

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun appDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> =
    Room.databaseBuilder<AppDatabase>(
        context = context.applicationContext,
        name = APP_DATABASE_NAME
    )
