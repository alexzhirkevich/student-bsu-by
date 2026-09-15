package github.alexzhirkevich.studentbsuby.services.store

interface ReviewLauncher {

    suspend fun tryShowReviewDialog()
}
