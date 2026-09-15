package github.alexzhirkevich.studentbsuby.services.store

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.isFlexibleUpdateAllowed
import com.google.android.play.core.ktx.isImmediateUpdateAllowed
import com.google.android.play.core.ktx.requestAppUpdateInfo
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.restart
import github.alexzhirkevich.studentbsuby.resources.update_downloaded
import github.alexzhirkevich.studentbsuby.services.firebase.await
import github.alexzhirkevich.studentbsuby.util.CurrentActivityHolder
import org.jetbrains.compose.resources.getString

class UpdateLauncherImpl(
    context: Context,
) : UpdateLauncher {

    private val appUpdateManager = AppUpdateManagerFactory.create(context)

    override suspend fun tryUpdate(
        immediate : Boolean,
        onFailedToInAppUpdate : () -> Unit,
    ) {
        val activity = CurrentActivityHolder.activity ?: return
        kotlin.runCatching {
            val info = appUpdateManager.requestAppUpdateInfo()
            if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                val listener = newUpdateListener(
                    activity,
                    getString(Res.string.update_downloaded),
                    getString(Res.string.restart)
                )
                appUpdateManager.registerListener(listener)
                while (!tryUpdateInternal(
                        activity = activity,
                        immediate = immediate || info.updatePriority() == 5,
                        info = info,
                        onFailedToInAppUpdate = onFailedToInAppUpdate
                    )
                )
                    appUpdateManager.unregisterListener(listener)
            }
        }
    }

    private suspend fun tryUpdateInternal(
        activity: Activity,
        immediate: Boolean,
        info: AppUpdateInfo,
        onFailedToInAppUpdate: () -> Unit
    ): Boolean = when {
            info.isImmediateUpdateAllowed && immediate ->
                AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE)
                    .setAllowAssetPackDeletion(true)
                    .build()

            info.isFlexibleUpdateAllowed ->
                AppUpdateOptions.newBuilder(AppUpdateType.FLEXIBLE)
                    .setAllowAssetPackDeletion(true)
                    .build()

            else -> {
                onFailedToInAppUpdate()
                null
            }
        }?.let {
            kotlin.runCatching {
                appUpdateManager.startUpdateFlow(info, activity, it).await()
            }.getOrDefault(ActivityResult.RESULT_IN_APP_UPDATE_FAILED)
        }?.let { it == Activity.RESULT_OK  } ?: true

    private fun newUpdateListener(
        activity: Activity,
        updateDownloaded: String,
        restart: String,
    ) = InstallStateUpdatedListener { state ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                AlertDialog.Builder(activity)
                    .setMessage(updateDownloaded)
                    .setPositiveButton(restart) { _, _ ->
                        appUpdateManager.completeUpdate()
                    }
                    .show()
            }
        }
}
