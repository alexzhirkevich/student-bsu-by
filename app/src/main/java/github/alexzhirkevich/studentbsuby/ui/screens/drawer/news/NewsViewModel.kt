package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news

import github.alexzhirkevich.studentbsuby.util.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.data.models.News
import github.alexzhirkevich.studentbsuby.di.IsNewsUpdatingQualifier
import github.alexzhirkevich.studentbsuby.util.*
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    @IsNewsUpdatingQualifier
    override val isUpdating: StateCommunication<Boolean>,
    val newsCommunication: StateCommunication<DataState<List<News>>>,
    errorHandler: ErrorHandler,
    dispatchers: Dispatchers,
    eventHandler: SuspendEventHandler<NewsEvent>
) : SuspendHandlerViewModel<NewsEvent>(
    errorHandler = errorHandler,
    dispatchers = dispatchers,
    suspendEventHandler = eventHandler
), Updatable {

    override fun update() {
        handle(NewsEvent.UpdateRequested)
    }
}