package github.alexzhirkevich.studentbsuby.services.store

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import platform.StoreKit.*

class ReviewLauncherImpl : ReviewLauncher {

    override suspend fun tryShowReviewDialog() {
        kotlin.runCatching {
            withContext(Dispatchers.Main) {
                SKStoreReviewController.requestReview()
            }
        }
    }
}
