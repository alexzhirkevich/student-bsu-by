package github.alexzhirkevich.studentbsuby.workers

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.Lesson
import github.alexzhirkevich.studentbsuby.repo.*
import github.alexzhirkevich.studentbsuby.util.NotificationCreator
import github.alexzhirkevich.studentbsuby.util.WorkerManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val HostelUpdateNotification  = 494376142

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Singleton
class SynchronizationWorkerManager @Inject constructor(
    private val workManager: WorkManager,
) : WorkerManager {

    override suspend fun isEnabled(): Boolean {
        return workManager.getWorkInfosForUniqueWork(SynchronizationWorker.TAG)
            .await().isNotEmpty()
    }

    override fun run() {
        val request = PeriodicWorkRequestBuilder<SynchronizationWorker>(1, TimeUnit.HOURS)
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
                SynchronizationWorker.TAG,
                ExistingPeriodicWorkPolicy.KEEP,
                request)

    }

    override fun stop() {
        workManager.cancelUniqueWork(SynchronizationWorker.TAG)
    }

}

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@HiltWorker
class SynchronizationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted parameters: WorkerParameters,
    private val hostelRepository: HostelRepository,
    private val timetableRepository: TimetableRepository,
    private val loginRepository: LoginRepository,
) : CoroutineWorker(context, parameters) {

    companion object {
        const val TAG = "SynchronizationWorker"
    }

    private val notificationCreator = NotificationCreator(
        context, "CHANNEL_SYNCHRONIZATION", context.getString(R.string.updates),
        context.getString(R.string.notification_channel_description)
    )

    override suspend fun doWork(): Result {
        return if (login())
            update()
        else Result.retry()
    }

    private suspend fun login() : Boolean = kotlin.runCatching {
        val res = loginRepository.initialize()
        if (res.loggedIn)
            return true

        repeat(5) {
            loginRepository.updateCaptcha(false)?.let { bmp ->
                loginRepository.getCaptchaText(bmp).also { bmp.recycle() }
            }?.let {
                loginRepository.login(loginRepository.username, loginRepository.password, it)
            }?.let {
                if (it.loggedIn)
                    return true
            }
        }
        return false
    }.getOrNull() == true


    private suspend fun update()  = coroutineScope {
        try {
            listOf(
                async { update(hostelRepository, ::notifyHostelChanged) },
                async { update(timetableRepository, ::notifyTimetableChanged) }
            ).awaitAll()
            Result.success()
        } catch (t: Throwable) {
            Result.retry()
        }
    }

    private suspend fun <T> update(repo : CacheWebRepository<T>, notify : (T,T) -> Unit) = with(repo){
        val cached = getFromCache() ?: return@with
        val new = getFromWeb() ?: return@with

        if (cached != new){
            notify(cached,new)
            saveToCache(new)
        }
    }

    private fun notifyHostelChanged(old: HostelState, new: HostelState) {
        when {
            old is HostelState.NotProvided && new is HostelState.Provided -> {
                notificationCreator.sendNotification(
                    id = HostelUpdateNotification,
                    sub = applicationContext.getString(R.string.hostel),
                    title = applicationContext.getString(R.string.hostel_provided),
                    text = new.address
                )
            }
        }
    }

    private fun notifyTimetableChanged(old : List<List<Lesson>>, new : List<List<Lesson>>) {

        val weekdays = applicationContext.resources.getStringArray(R.array.weekdays)

        val changedWeekdays = old.zip(new).mapIndexedNotNull { index, pair ->
            if (pair.first != pair.second)
                weekdays[index] else null
        }
        if (changedWeekdays.isNotEmpty()) {
            notificationCreator.sendNotification(
                id = HostelUpdateNotification,
                sub = applicationContext.getString(R.string.timetable),
                title= applicationContext.getString(R.string.timetable_changed),
                text = changedWeekdays.joinToString(separator = ", ")
            )
        }
    }
}
