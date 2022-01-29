package github.alexzhirkevich.studentbsuby.ui.screens.drawer.subjects

import android.accounts.NetworkErrorException
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.Subject
import github.alexzhirkevich.studentbsuby.repo.SubjectsRepository
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.exceptions.UsernameNotFoundException
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import github.alexzhirkevich.studentbsuby.util.setState
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class SubjectsViewModel @Inject constructor(
    private val subjectsRepository: SubjectsRepository,
    private val logger : Logger,
) : ViewModel(), Updatable {

    private val _currentSemester = mutableStateOf(
        subjectsRepository.getCurrentSemesterFromCache()?:0 )
    val currentSemester : State<Int>
        get() = _currentSemester

    private val _isUpdating = mutableStateOf(false)
    override val isUpdating: State<Boolean>
        get() = _isUpdating

//    private val _subjects = mutableStateOf<DataState<List<List<Subject>>>>(DataState.Empty)
//    val subjects : State<DataState<List<List<Subject>>>>
//    get() = _subjects

    private val _subjects = MutableStateFlow<DataState<List<List<Subject>>>>(DataState.Loading)
    val subjects : StateFlow<DataState<List<List<Subject>>>>
        = _subjects.asStateFlow()

    private val _visibleSubjects = MutableStateFlow<DataState<List<List<Subject>>>>(DataState.Loading)
    val visibleSubjects : StateFlow<DataState<List<List<Subject>>>>
            = _visibleSubjects.asStateFlow()

    private val _searchText = mutableStateOf("")
    val searchText : State<String>
    get() = _searchText

    private val _withCredit = mutableStateOf(false)
    val withCredit : State<Boolean>
        get() = _withCredit

    private val _withExam = mutableStateOf(false)
    val withExam: State<Boolean>
        get() = _withExam

//    @Volatile private var realSubjects : List<List<Subject>>?=null

    fun setFilter(withCredit : Boolean, withExam : Boolean){
        _withCredit.value = withCredit
        _withExam.value = withExam

        applySubjectFilter()
    }

    fun search(name : String){
        _searchText.value = name
        applySubjectFilter()
    }

    override fun update() {
        _isUpdating.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateSubjects(true)
            }finally {
                setState {
                    _isUpdating.value = false
                }
            }
        }
    }


    init {
        viewModelScope.launch(Dispatchers.IO) {
            updateSubjects()
            updateCurrentSemester()
        }
    }

    private fun applySubjectFilter() {
        _visibleSubjects.tryEmit(subjects.value.valueOrNull()?.map {
            it.filter {
                (_searchText.value.isEmpty() || it.name.contains(_searchText.value,true)) &&
                (!withCredit.value || it.hasCredit) &&
                        (!withExam.value || it.hasExam) }
        }?.takeIf { it.any(Collection<*>::isNotEmpty) }?.let {
            DataState.Success(it)
        }?: DataState.Empty)
    }

    private suspend fun updateCurrentSemester() {
        kotlin.runCatching {
            subjectsRepository.currentSemester().collectLatest {
                setState {
                    _currentSemester.value = it
                }
            }
        }.onFailure {
            logger.log(
                msg = "Failed to get current semester",
                tag = javaClass.simpleName,
                logLevel = Logger.LogLevel.Error,
                cause = it
            )
        }
    }

    @FlowPreview
    private suspend fun updateSubjects(fromWebOnly : Boolean = false){
        kotlin.runCatching {
            subjectsRepository.getSubjects(fromWebOnly).collectLatest {
                _subjects.tryEmit(
                    if (it.isNotEmpty())
                        DataState.Success(it) else DataState.Empty
                )
                applySubjectFilter()
            }
        }.onFailure {
            if (_subjects.value !is DataState.Success) {
                _subjects.tryEmit(
                    DataState.Error(
                        message = when (it) {
                            is UsernameNotFoundException ->
                                R.string.error_username_not_found
                            else -> R.string.error_load_subjects
                        },
                        error = it
                    )
                )
            }
            logger.log(
                msg = "Failed to get current semester",
                tag = javaClass.simpleName,
                logLevel = Logger.LogLevel.Error,
                cause = it
            )
        }
    }
}