package github.alexzhirkevich.studentbsuby.util

import platform.Foundation.NSBundle
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.Platform

private object IosPlatformInfo : PlatformInfo {

    @OptIn(ExperimentalNativeApi::class)
    override val isDebug: Boolean = Platform.isDebugBinary

    override val versionName: String =
        NSBundle.mainBundle.infoDictionary?.get("CFBundleShortVersionString") as? String ?: ""

    override val versionCode: Int =
        (NSBundle.mainBundle.infoDictionary?.get("CFBundleVersion") as? String)
            ?.toIntOrNull() ?: 0

    override val platformName: String get() = "ios"
}

actual fun platformInfo(): PlatformInfo = IosPlatformInfo
