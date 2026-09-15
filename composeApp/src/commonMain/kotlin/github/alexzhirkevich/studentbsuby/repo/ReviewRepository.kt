package github.alexzhirkevich.studentbsuby.repo

import com.russhwolf.settings.ObservableSettings
import github.alexzhirkevich.studentbsuby.services.store.ReviewLauncher
import github.alexzhirkevich.studentbsuby.util.sharedPreferences
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

private const val REVIEW_DELAY = 3 * 24 * 60 * 60 * 1000

@OptIn(ExperimentalTime::class)
class ReviewRepository(
    preferences: ObservableSettings,
    private val reviewLauncher: ReviewLauncher
) {

    private val firstInitializing by sharedPreferences(
        preferences, Clock.System.now().toEpochMilliseconds()
    )

    suspend fun tryShowReviewDialog() {
        if (Clock.System.now().toEpochMilliseconds() - firstInitializing > REVIEW_DELAY) {
            kotlin.runCatching {
                reviewLauncher.tryShowReviewDialog()
            }
        }
    }
}
