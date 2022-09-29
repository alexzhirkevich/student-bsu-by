package github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable

import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.di.IsTimetableUpdatingQualifier
import github.alexzhirkevich.studentbsuby.util.*
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication
import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel @Inject constructor(
    @IsTimetableUpdatingQualifier
    override val isUpdating: StateCommunication<Boolean>,
    val timetableCommunication: StateCommunication<DataState<Timetable>>,
    dispatchers: Dispatchers,
    errorHandler: ErrorHandler,
    eventHandler: SuspendEventHandler<TimetableEvent>,
    calendar: Calendar
): SuspendHandlerViewModel<TimetableEvent>(
    dispatchers = dispatchers,
    suspendEventHandler = eventHandler,
    errorHandler = errorHandler
), Updatable, Calendar by calendar{

    override fun update() {
        handle(TimetableEvent.UpdateRequested)
    }
}