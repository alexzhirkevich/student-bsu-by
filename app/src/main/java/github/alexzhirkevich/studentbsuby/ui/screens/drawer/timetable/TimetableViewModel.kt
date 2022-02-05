package github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable

import androidx.annotation.IntRange
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.Lesson
import github.alexzhirkevich.studentbsuby.repo.DataSource
import github.alexzhirkevich.studentbsuby.repo.TimetableRepository
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import github.alexzhirkevich.studentbsuby.util.setState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

enum class LessonState{
    INCOMING,
    RUNNING,
    PASSED
}

typealias Timetable = List<List<Pair<Lesson,LessonState>>>


@HiltViewModel
class TimetableViewModel @Inject constructor(
    private val timetableRepository: TimetableRepository,
    private val logger: Logger
):  ViewModel(), Updatable {

    private val _timetable = MutableStateFlow<DataState<Timetable>>(DataState.Loading)
    val timetable: StateFlow<DataState<Timetable>> = _timetable.asStateFlow()

    @IntRange(from = 1, to = 31)
    val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    @IntRange(from = 0, to = 6)
    val currentDayOfWeek = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> 0
        Calendar.TUESDAY -> 1
        Calendar.WEDNESDAY -> 2
        Calendar.THURSDAY -> 3
        Calendar.FRIDAY -> 4
        Calendar.SATURDAY -> 5
        else -> 6
    }

    @IntRange(from = 0, to = 11)
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    @IntRange(from = 0, to = 5)
    val displayDayOfWeek =
        when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY -> 3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            else -> 0
        }

    private val _isUpdating = mutableStateOf(false)
    override val isUpdating: State<Boolean>
        get() = _isUpdating

    init {
        updateTimeTable(DataSource.All)
    }

    override fun update() {
        _isUpdating.value = true
        updateTimeTable(DataSource.Remote)
    }


    private var timetableJob : Job? = null
    private fun updateTimeTable(dataSource: DataSource) =
        timetableRepository
            .get(dataSource)
            .flowOn(Dispatchers.IO)
            .map {
                if (it.flatten().isNotEmpty())
                    DataState.Success(
                        it.map { day ->
                            var hasRunning = false
                            val time = currentTime()
                            day.mapIndexed { _, lesson ->
                                lesson to when {
                                    lesson.dayOfWeek < currentDayOfWeek ||
                                            lesson.dayOfWeek == currentDayOfWeek && lesson.ends <= time ->
                                        LessonState.PASSED
                                    !hasRunning && lesson.dayOfWeek == currentDayOfWeek &&
                                            lesson.ends > time ->
                                        LessonState.RUNNING.also { hasRunning = true }
                                    else -> LessonState.INCOMING
                                }
                            }
                        })
                else DataState.Empty
            }
            .onEmpty {
                if (_timetable.value !is DataState.Success){
                    _timetable.value is DataState.Empty
                }
            }
            .onCompletion {
                _isUpdating.value = false
            }
            .onEach {
                _timetable.value = it
            }
            .catch {
                if (_timetable.value !is DataState.Success) {
                    _timetable.tryEmit(DataState.Error(R.string.error_load_timetable, it))
                }
                logger.log(
                    "Failed to get timetable",
                    this@TimetableViewModel.javaClass.simpleName
                )
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope).also {
                timetableJob?.cancel()
                timetableJob = it
            }


    private fun currentTime() : String{
        return Calendar.getInstance().let {
            "${it.get(Calendar.HOUR_OF_DAY).let { if (it>9) it else "0$it" }}:" +
                    "${it.get(Calendar.MINUTE).let { if (it>9) it else "0$it" }}"
        }
    }
}

val Lesson.startHour : String
    get() = starts.split(":").firstOrNull().orEmpty()