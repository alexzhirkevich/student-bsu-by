package github.alexzhirkevich.studentbsuby

import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.di.ShowUpdateQualifier
import github.alexzhirkevich.studentbsuby.util.ErrorHandler
import github.alexzhirkevich.studentbsuby.util.SuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.SuspendHandlerViewModel
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    @ShowUpdateQualifier
    val showUpdateDialog : StateCommunication<Boolean>,
    dispatchers: Dispatchers,
    errorHandler: ErrorHandler,
    eventHandler: MainActivityEventHandler
) : SuspendHandlerViewModel<MainActivityEvent>(
    dispatchers = dispatchers,
    suspendEventHandler = eventHandler,
    errorHandler = errorHandler
)