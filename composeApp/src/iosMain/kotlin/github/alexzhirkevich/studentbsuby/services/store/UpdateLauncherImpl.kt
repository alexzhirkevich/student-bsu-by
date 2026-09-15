package github.alexzhirkevich.studentbsuby.services.store

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import platform.Foundation.*
import platform.UIKit.*

class UpdateLauncherImpl : UpdateLauncher {

    companion object {
        // App Store id of the application. Empty until the app is published,
        // so forced updates fall back to onFailedToInAppUpdate.
        const val APP_STORE_ID = ""
    }

    // There are no in-app updates on iOS. Update availability can't be checked
    // either, so only forced (immediate) updates lead to the App Store page.
    override suspend fun tryUpdate(
        immediate : Boolean,
        onFailedToInAppUpdate : () -> Unit,
    ) {
        if (!immediate)
            return

        kotlin.runCatching {
            val url = if (APP_STORE_ID.isNotEmpty())
                NSURL.URLWithString("itms-apps://itunes.apple.com/app/id$APP_STORE_ID")
            else null

            withContext(Dispatchers.Main) {
                if (url != null) {
                    UIApplication.sharedApplication.openURL(
                        url,
                        options = emptyMap<Any?, Any>(),
                        completionHandler = null
                    )
                } else {
                    onFailedToInAppUpdate()
                }
            }
        }
    }
}
