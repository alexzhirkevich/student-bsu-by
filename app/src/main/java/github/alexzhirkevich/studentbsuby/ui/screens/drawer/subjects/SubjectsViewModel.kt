package github.alexzhirkevich.studentbsuby.ui.screens.drawer.subjects

import github.alexzhirkevich.studentbsuby.util.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.data.models.Subject
import github.alexzhirkevich.studentbsuby.di.*
import github.alexzhirkevich.studentbsuby.util.*
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SubjectsViewModel @Inject constructor(
    @IsSubjectsUpdatingQualifier
    override val isUpdating : StateCommunication<Boolean>,
    @SemesterQualifier
    val semesterCommunication : StateCommunication<Int>,
    @SearchQualifier
    val searchCommunication : StateCommunication<String>,
    @ExamQualifier
    val withExamCommunication : StateCommunication<Boolean>,
    @CreditQualifier
    val withCreditCommunication : StateCommunication<Boolean>,
    @SubjectsQualifier
    val subjectsCommunication : StateCommunication<DataState<List<List<Subject>>>>,
    @VisibleSubjectsQualifier
    val visibleSubjectsCommunication : StateCommunication<DataState<List<List<Subject>>>>,
    eventHandler : SuspendEventHandler<SubjectsEvent>,
    errorHandler : ErrorHandler,
    dispatchers: Dispatchers
) : SuspendHandlerViewModel<SubjectsEvent>(
    suspendEventHandler = eventHandler,
    errorHandler = errorHandler,
    dispatchers = dispatchers
), Updatable {

    override fun update() {
        handle(SubjectsEvent.UpdateRequested)
    }
}