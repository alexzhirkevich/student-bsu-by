@file:OptIn(ExperimentalForeignApi::class)

package github.alexzhirkevich.studentbsuby.workers

import github.alexzhirkevich.studentbsuby.util.WorkerManager
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import platform.BackgroundTasks.*
import platform.Foundation.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BackgroundSyncScheduler(
    private val syncUseCase: SyncUseCase,
) : WorkerManager {

    companion object {
        const val TASK_IDENTIFIER = "github.alexzhirkevich.studentbsuby.sync"
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    /**
     * Must be called before the application finishes launching.
     * TASK_IDENTIFIER must be listed in BGTaskSchedulerPermittedIdentifiers
     * of the app Info.plist.
     */
    fun register() {
        BGTaskScheduler.sharedScheduler.registerForTaskWithIdentifier(
            identifier = TASK_IDENTIFIER,
            usingQueue = null,
        ) { task ->
            handle(task as BGAppRefreshTask)
        }
    }

    override suspend fun isEnabled(): Boolean = suspendCoroutine { cont ->
        BGTaskScheduler.sharedScheduler.getPendingTaskRequestsWithCompletionHandler { requests ->
            cont.resume(
                requests.orEmpty().any {
                    (it as? BGTaskRequest)?.identifier == TASK_IDENTIFIER
                }
            )
        }
    }

    override fun run() {
        kotlin.runCatching {
            val request = BGAppRefreshTaskRequest(identifier = TASK_IDENTIFIER).apply {
                earliestBeginDate = NSDate.dateWithTimeIntervalSinceNow(60.0 * 60.0)
            }
            BGTaskScheduler.sharedScheduler.submitTaskRequest(request, null)
        }
    }

    override fun stop() {
        BGTaskScheduler.sharedScheduler.cancelTaskRequestWithIdentifier(TASK_IDENTIFIER)
    }

    suspend fun performBackgroundSync(): SyncResult = syncUseCase.performSync()

    private fun handle(task: BGAppRefreshTask) {
        run()
        val job = scope.launch {
            val result = performBackgroundSync()
            task.setTaskCompletedWithSuccess(result == SyncResult.Success)
        }
        task.expirationHandler = {
            job.cancel()
            task.setTaskCompletedWithSuccess(false)
        }
    }
}
