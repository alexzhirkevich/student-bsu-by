package github.alexzhirkevich.studentbsuby.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

/**
 * All shared Koin modules, mirroring the original Hilt module split:
 * AppModule -> [appModule], RetrofitModule -> [networkModule], RoomModule ->
 * [databaseModule], LoginModule -> [loginModule], ProfileModule -> [profileModule],
 * SubjectsModule -> [subjectsModule], TimetableModule -> [timetableModule],
 * NewsModule -> [newsModule], HostelModule -> [hostelModule], PaidServicesModule ->
 * [paidServicesModule], SettingsModule -> [settingsModule], AboutModule ->
 * [aboutModule], MainActivityModule -> [mainModule].
 */
val sharedModules: List<Module> = listOf(
    appModule,
    networkModule,
    databaseModule,
    loginModule,
    profileModule,
    subjectsModule,
    timetableModule,
    newsModule,
    hostelModule,
    paidServicesModule,
    settingsModule,
    aboutModule,
    mainModule,
)

fun initKoin(
    platformModules: List<Module> = emptyList(),
    config: KoinAppDeclaration? = null,
): KoinApplication = startKoin {
    config?.invoke(this)
    modules(platformModules + sharedModules)
}
