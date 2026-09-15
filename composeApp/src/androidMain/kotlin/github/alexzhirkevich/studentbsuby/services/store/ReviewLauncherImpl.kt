package github.alexzhirkevich.studentbsuby.services.store

import android.content.Context
import com.google.android.play.core.ktx.launchReview
import com.google.android.play.core.ktx.requestReview
import com.google.android.play.core.review.ReviewManagerFactory
import github.alexzhirkevich.studentbsuby.util.CurrentActivityHolder

class ReviewLauncherImpl(
    context: Context,
) : ReviewLauncher {

    private val reviewManager by lazy {
        ReviewManagerFactory.create(context)
    }

    override suspend fun tryShowReviewDialog() {
        val activity = CurrentActivityHolder.activity ?: return
        kotlin.runCatching {
            val info = reviewManager.requestReview()
            reviewManager.launchReview(activity, info)
        }
    }
}
