package github.alexzhirkevich.studentbsuby.util

import org.jetbrains.compose.resources.StringResource
import platform.UserNotifications.*

class NotificationCreatorImpl(
    private val channelId : String,
    private val channelName : StringResource,
    private val channelDescription : StringResource
) : NotificationCreator {

    override suspend fun sendNotification(
        id : Int,
        sub : String,
        title : String,
        text : String,
    ) {
        kotlin.runCatching {
            val center = UNUserNotificationCenter.currentNotificationCenter()
            center.requestAuthorizationWithOptions(
                UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
            ) { granted, _ ->
                if (granted) {
                    val content = UNMutableNotificationContent().apply {
                        setTitle(title)
                        setSubtitle(sub)
                        setBody(text)
                        setSound(UNNotificationSound.defaultSound)
                    }
                    center.addNotificationRequest(
                        UNNotificationRequest.requestWithIdentifier(
                            id.toString(),
                            content,
                            null
                        ),
                        null
                    )
                }
            }
        }
    }
}
