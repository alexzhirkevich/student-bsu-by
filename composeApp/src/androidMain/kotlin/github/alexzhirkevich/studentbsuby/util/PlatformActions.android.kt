package github.alexzhirkevich.studentbsuby.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.core.content.FileProvider
import java.io.File
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager

class PlatformActionsAndroid(
    private val context: Context
) : PlatformActions {

    override fun exitApp() {
        CurrentActivityHolder.activity?.finish()
    }

    override fun openStorePage() {
        val activity = CurrentActivityHolder.activity ?: return
        val packageName = activity.packageName
        runCatching {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            activity.startActivity(intent)
        }.getOrElse {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            activity.startActivity(intent)
        }
    }

    override fun openUrl(url: String) {
        val activity = CurrentActivityHolder.activity ?: context
        runCatching {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            activity.startActivity(intent)
        }
    }

    override fun shareFile(path: String, mime: String) {
        val activity = CurrentActivityHolder.activity ?: return
        runCatching {
            val file = File(path)
            val uri = FileProvider.getUriForFile(activity, "${activity.packageName}.provider", file)
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = mime
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            activity.startActivity(Intent.createChooser(intent, null))
        }
    }

    override suspend fun requestNotificationsPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val activity = CurrentActivityHolder.activity ?: return false
            val permission = "android.permission.POST_NOTIFICATIONS"
            val granted = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
            if (!granted) {
                activity.requestPermissions(arrayOf(permission), 101)
            }
            return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
        }
        return true
    }
}
