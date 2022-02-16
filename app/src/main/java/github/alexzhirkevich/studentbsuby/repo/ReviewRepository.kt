package github.alexzhirkevich.studentbsuby.repo

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.android.play.core.ktx.launchReview
import com.google.android.play.core.ktx.requestReview
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.Task
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.util.sharedPreferences
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val REVIEW_COOLDOWN = 3 * 24 * 60 * 60 * 1000

class ReviewRepository @Inject constructor(
    preferences: SharedPreferences,
    @ApplicationContext context: Context
) {

    private val firstInitializing by sharedPreferences(preferences, System.currentTimeMillis())

    private val reviewManager by lazy {
        ReviewManagerFactory.create(context)
    }

    suspend fun tryShowReviewDialog(activity: Activity) {
        if (System.currentTimeMillis() - firstInitializing > REVIEW_COOLDOWN) {
            kotlin.runCatching {
                val info = reviewManager.requestReview()
                reviewManager.launchReview(activity, info)
            }
        }
    }
}