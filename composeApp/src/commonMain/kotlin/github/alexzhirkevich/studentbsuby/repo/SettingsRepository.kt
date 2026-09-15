package github.alexzhirkevich.studentbsuby.repo

import com.russhwolf.settings.ObservableSettings
import github.alexzhirkevich.studentbsuby.services.firebase.AnalyticsReporter
import github.alexzhirkevich.studentbsuby.services.firebase.CrashReporter
import github.alexzhirkevich.studentbsuby.util.WorkerManager
import github.alexzhirkevich.studentbsuby.util.sharedPreferences


class SettingsRepository(
    preferences: ObservableSettings,
    private val analyticsReporter: AnalyticsReporter,
    private val crashReporter: CrashReporter,
    private val synchronizationWorkerManager: WorkerManager,
) {
    var synchronizationEnabled by sharedPreferences(preferences,true){
//        with(synchronizationWorkerManager){
//            if (it) run() else stop()
//        }
    }

    var collectStatistics by sharedPreferences(preferences,true){
        analyticsReporter.setEnabled(it)
    }

    var collectCrashlytics by sharedPreferences(preferences,true){
        crashReporter.setEnabled(it)
    }
}
