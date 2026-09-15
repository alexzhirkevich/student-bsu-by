package github.alexzhirkevich.studentbsuby.util

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIActivityViewController
import platform.UserNotifications.UNUserNotificationCenter
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationOptionBadge
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PlatformActionsIos : PlatformActions {

    override fun exitApp() {
        // no-op on iOS per guidelines
    }

    override fun openStorePage() {
        runCatching {
            val url = NSURL.URLWithString("itms-apps://itunes.apple.com/app/id")
            if (url != null) {
                UIApplication.sharedApplication.openURL(url, options = emptyMap<Any?, Any>(), completionHandler = null)
            }
        }
    }

    override fun openUrl(url: String) {
        runCatching {
            val nsUrl = NSURL.URLWithString(url)
            if (nsUrl != null) {
                UIApplication.sharedApplication.openURL(nsUrl, options = emptyMap<Any?, Any>(), completionHandler = null)
            }
        }
    }

    override fun shareFile(path: String, mime: String) {
        runCatching {
            val nsUrl = NSURL.fileURLWithPath(path)
            val activityVC = UIActivityViewController(activityItems = listOf(nsUrl), applicationActivities = null)
            UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(activityVC, animated = true, completion = null)
        }
    }

    override suspend fun requestNotificationsPermission(): Boolean = suspendCancellableCoroutine { continuation ->
        val center = UNUserNotificationCenter.currentNotificationCenter()
        val options = UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
        center.requestAuthorizationWithOptions(options) { granted, _ ->
            continuation.resume(granted)
        }
    }
}
