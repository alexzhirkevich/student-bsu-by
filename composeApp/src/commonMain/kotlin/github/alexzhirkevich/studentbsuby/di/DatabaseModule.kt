package github.alexzhirkevich.studentbsuby.di

import github.alexzhirkevich.studentbsuby.dao.AppDatabase
import org.koin.dsl.module

/**
 * Mirrors the original RoomModule. The [AppDatabase] itself is bound in the platform
 * modules because the Room builder entry points differ (Android needs a Context, iOS
 * sets the bundled SQLite driver); all DAO bindings stay common.
 */
val databaseModule = module {

    single { get<AppDatabase>().userDao() }

    single { get<AppDatabase>().subjectsDao() }

    single { get<AppDatabase>().lessonsDao() }

    single { get<AppDatabase>().hostelDao() }

    single { get<AppDatabase>().paidServicesDao() }

    single { get<AppDatabase>().newsDao() }
}
