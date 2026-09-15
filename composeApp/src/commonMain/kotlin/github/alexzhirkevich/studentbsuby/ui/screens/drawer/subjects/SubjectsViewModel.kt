package github.alexzhirkevich.studentbsuby.ui.screens.drawer.subjects

import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers
import github.alexzhirkevich.studentbsuby.data.models.Subject
import github.alexzhirkevich.studentbsuby.util.*
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication

class SubjectsViewModel(
    override val isUpdating : StateCommunication<Boolean>,
    val semesterCommunication : StateCommunication<Int>,
    val searchCommunication : StateCommunication<String>,
    val withExamCommunication : StateCommunication<Boolean>,
    val withCreditCommunication : StateCommunication<Boolean>,
    val subjectsCommunication : StateCommunication<DataState<List<List<Subject>>>>,
    val visibleSubjectsCommunication : StateCommunication<DataState<List<List<Subject>>>>,
    eventHandler : SubjectsEventHandler,
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
