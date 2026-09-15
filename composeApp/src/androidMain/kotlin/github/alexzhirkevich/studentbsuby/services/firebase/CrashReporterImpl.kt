package github.alexzhirkevich.studentbsuby.services.firebase

import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashReporterImpl : CrashReporter {

    override fun setEnabled(enabled: Boolean) {
        kotlin.runCatching {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(enabled)
        }
    }
}
