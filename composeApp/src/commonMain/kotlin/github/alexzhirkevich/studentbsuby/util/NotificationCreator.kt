package github.alexzhirkevich.studentbsuby.util

interface NotificationCreator {

    suspend fun sendNotification(
        id : Int,
        sub : String,
        title : String,
        text : String,
    )
}
