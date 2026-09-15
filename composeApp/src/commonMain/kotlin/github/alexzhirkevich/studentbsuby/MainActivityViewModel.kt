package github.alexzhirkevich.studentbsuby

import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers
import github.alexzhirkevich.studentbsuby.util.ErrorHandler
import github.alexzhirkevich.studentbsuby.util.SuspendHandlerViewModel
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication


class MainActivityViewModel(
    val showUpdateDialog : StateCommunication<Boolean>,
    dispatchers: Dispatchers,
    errorHandler: ErrorHandler,
    eventHandler: MainActivityEventHandler
) : SuspendHandlerViewModel<MainActivityEvent>(
    dispatchers = dispatchers,
    suspendEventHandler = eventHandler,
    errorHandler = errorHandler
)
