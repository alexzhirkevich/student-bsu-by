package github.alexzhirkevich.studentbsuby.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.os.Build

/**
 * Application context holder. Must be initialized in [android.app.Application.onCreate]
 * before any platform util is used.
 * */
@SuppressLint("StaticFieldLeak")
object AndroidAppContext {
    lateinit var context: Context
}

private object AndroidPlatformInfo : PlatformInfo {

    override val isDebug: Boolean by lazy {
        AndroidAppContext.context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }

    override val versionName: String by lazy {
        packageInfo().versionName.orEmpty()
    }

    override val versionCode: Int by lazy {
        packageInfo().let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                it.longVersionCode.toInt()
            } else {
                @Suppress("DEPRECATION")
                it.versionCode
            }
        }
    }

    override val platformName: String get() = "android"

    private fun packageInfo(): PackageInfo = AndroidAppContext.context.run {
        packageManager.getPackageInfo(packageName, 0)
    }
}

actual fun platformInfo(): PlatformInfo = AndroidPlatformInfo
