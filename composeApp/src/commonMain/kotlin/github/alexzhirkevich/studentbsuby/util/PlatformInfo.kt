package github.alexzhirkevich.studentbsuby.util

interface PlatformInfo {
    val isDebug: Boolean
    val versionName: String
    val versionCode: Int
    val platformName: String // "android" | "ios"
}

expect fun platformInfo(): PlatformInfo
