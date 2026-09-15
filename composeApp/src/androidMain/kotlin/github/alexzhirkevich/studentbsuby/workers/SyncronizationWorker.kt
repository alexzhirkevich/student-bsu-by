package github.alexzhirkevich.studentbsuby.workers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.*
import github.alexzhirkevich.studentbsuby.util.WorkerManager
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

class SyncWorkerManager(
    private val workManager: WorkManager,
) : WorkerManager {

    override suspend fun isEnabled(): Boolean {
        return workManager.getWorkInfosForUniqueWorkFlow(SyncWorker.TAG)
            .first().isNotEmpty()
    }

    override fun run() {
        val request = PeriodicWorkRequestBuilder<SyncWorker>(1, TimeUnit.HOURS)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 15, TimeUnit.MINUTES)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .build()

       workManager
            .enqueueUniquePeriodicWork(
                SyncWorker.TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                request)

    }

    override fun stop() {
        workManager.cancelUniqueWork(SyncWorker.TAG)
    }

}

class SyncWorker(
    context: Context,
    parameters: WorkerParameters,
    private val syncUseCase: SyncUseCase,
) : CoroutineWorker(context, parameters) {

    companion object {
        const val TAG = "SynchronizationWorker"
    }

    override suspend fun doWork(): Result {
        return when (syncUseCase.performSync()) {
            SyncResult.Success -> Result.success()
            SyncResult.Retry -> Result.retry()
        }
    }
}

class SyncWorkerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
//        if (intent.action in listOf(Intent.ACTION_BOOT_COMPLETED, Intent.ACTION_REBOOT)) {
//            SyncWorkerManager(WorkManager.getInstance(context))
//                .run()
//        }
    }
}
