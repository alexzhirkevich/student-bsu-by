package github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable

import androidx.annotation.IntRange
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.Lesson
import github.alexzhirkevich.studentbsuby.repo.TimetableRepository
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.setState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

enum class LessonState{
    INCOMING,
    RUNNING,
    PASSED
}

typealias Timetable = List<List<Pair<Lesson,LessonState>>>

@FlowPreview
@HiltViewModel
class TimetableViewModel @Inject constructor(
    private val timetableRepository: TimetableRepository
):  ViewModel(), Updatable {

    private val _timetable = MutableStateFlow<DataState<Timetable>>(DataState.Loading)
    val timetable : StateFlow<DataState<Timetable>> = _timetable.asStateFlow()

    @IntRange(from = 1, to = 31)
    val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    @IntRange(from = 0, to = 6)
    val currentDayOfWeek = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)){
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

    @IntRange(from= 0, to = 5) val displayDayOfWeek =
        when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
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
        viewModelScope.launch(Dispatchers.IO) {
            updateTimeTable()
        }
    }

    override fun update() {
        _isUpdating.value = true
        kotlin.runCatching {
            viewModelScope.launch(Dispatchers.IO) {
                updateTimeTable(true)
                setState {
                    _isUpdating.value = false
                }
            }
        }.onFailure { _isUpdating.value = false }
    }

    private suspend fun updateTimeTable(fromWebOnly : Boolean = false) {
        kotlin.runCatching {
            timetableRepository.getTimetable(fromWebOnly).collectLatest {
                _timetable.tryEmit(
                    if (it.flatten().isNotEmpty())
                        DataState.Success(
                            it.map { day ->
                                var hasRunning = false
                                val time = currentTime()
                                day.mapIndexed { idx, lesson ->
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
                )
            }
        }.onFailure {
            _timetable.tryEmit(DataState.Error(R.string.error_load_timetable, it))
        }
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