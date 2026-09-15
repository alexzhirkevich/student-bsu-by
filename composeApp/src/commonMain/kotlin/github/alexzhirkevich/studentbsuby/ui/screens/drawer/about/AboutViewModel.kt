package github.alexzhirkevich.studentbsuby.ui.screens.drawer.about

import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers
import github.alexzhirkevich.studentbsuby.util.ErrorHandler
import github.alexzhirkevich.studentbsuby.util.SuspendHandlerViewModel

class AboutViewModel(
    errorHandler: ErrorHandler,
    dispatchers: Dispatchers,
    eventHandler: IAboutEventHandler
) : SuspendHandlerViewModel<AboutEvent>(
    errorHandler = errorHandler,
    dispatchers = dispatchers,
    suspendEventHandler = eventHandler
)
