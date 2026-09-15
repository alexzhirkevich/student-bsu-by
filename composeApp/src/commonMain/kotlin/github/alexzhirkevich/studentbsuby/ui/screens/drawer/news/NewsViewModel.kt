package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news

import github.alexzhirkevich.studentbsuby.data.models.News
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.ErrorHandler
import github.alexzhirkevich.studentbsuby.util.SuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.SuspendHandlerViewModel
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication
import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers

interface NewsEventHandler : SuspendEventHandler<NewsEvent>

class NewsViewModel(
    override val isUpdating: StateCommunication<Boolean>,
    val newsCommunication: StateCommunication<DataState<List<News>>>,
    errorHandler: ErrorHandler,
    dispatchers: Dispatchers,
    eventHandler: NewsEventHandler
) : SuspendHandlerViewModel<NewsEvent>(
    errorHandler = errorHandler,
    dispatchers = dispatchers,
    suspendEventHandler = eventHandler
), Updatable {

    override fun update() {
        handle(NewsEvent.UpdateRequested)
    }
}
