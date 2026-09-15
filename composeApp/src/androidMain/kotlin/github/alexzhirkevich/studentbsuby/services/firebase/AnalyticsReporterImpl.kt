package github.alexzhirkevich.studentbsuby.services.firebase

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics

class AnalyticsReporterImpl(
    private val context: Context,
) : AnalyticsReporter {

    override fun setEnabled(enabled: Boolean) {
        kotlin.runCatching {
            FirebaseAnalytics.getInstance(context)
                .setAnalyticsCollectionEnabled(enabled)
        }
    }
}
