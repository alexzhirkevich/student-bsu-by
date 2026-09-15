package github.alexzhirkevich.studentbsuby.util

interface PlatformActions {
    fun exitApp()                       // Android: activity.finish(); iOS: no-op
    fun openStorePage()                 // Play Store page / App Store page
    fun openUrl(url: String)            // browser
    fun shareFile(path: String, mime: String)  // FileProvider+chooser / UIActivityViewController
    suspend fun requestNotificationsPermission(): Boolean
}
