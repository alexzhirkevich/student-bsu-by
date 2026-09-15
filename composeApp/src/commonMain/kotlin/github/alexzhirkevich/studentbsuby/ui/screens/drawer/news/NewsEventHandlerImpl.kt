package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news

import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.error_load_news
import github.alexzhirkevich.studentbsuby.data.models.News
import github.alexzhirkevich.studentbsuby.repo.DataSource
import github.alexzhirkevich.studentbsuby.repo.NewsRepository
import github.alexzhirkevich.studentbsuby.util.ConnectivityManager
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.BaseSuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.SuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.communication.Mapper
import github.alexzhirkevich.studentbsuby.util.communication.StateMapper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class NewsEventHandlerImpl(
    newsRepository: NewsRepository,
    connectivityManager: ConnectivityManager,
    isUpdatingMapper: Mapper<Boolean>,
    newsMapper: StateMapper<DataState<List<News>>>
) : NewsEventHandler, SuspendEventHandler<NewsEvent> by SuspendEventHandler.from(
    UpdateRequestedHandler(
        connectivityManager = connectivityManager,
        newsRepository = newsRepository,
        isUpdatingMapper = isUpdatingMapper,
        newsMapper = newsMapper
    )
)

private class UpdateRequestedHandler(
    private val connectivityManager: ConnectivityManager,
    private val newsRepository: NewsRepository,
    private val isUpdatingMapper : Mapper<Boolean>,
    private val newsMapper : StateMapper<DataState<List<News>>>
) : BaseSuspendEventHandler<NewsEvent.UpdateRequested>(
    NewsEvent.UpdateRequested::class
) {

    override suspend fun launch() {
        isUpdatingMapper.map(false)
        newsMapper.map(DataState.Loading)
        update(DataSource.All)
        connectivityManager.isNetworkConnected.collect {
            if (it){
                update(DataSource.Remote)
            }
        }
    }

    override suspend fun handle(event: NewsEvent.UpdateRequested) {
        isUpdatingMapper.map(true)
        update(DataSource.Remote)
        isUpdatingMapper.map(false)
    }

    private suspend fun update(dataSource: DataSource){
        newsRepository.get(dataSource)
            .onEach {
                if (it.isEmpty()) {
                    newsMapper.map(DataState.Empty)
                } else {
                    newsMapper.map(DataState.Success(it))
                }
            }
            .catch {
                if (newsMapper.current !is DataState.Success){
                    newsMapper.map(DataState.Error(
                        Res.string.error_load_news, it
                    ))
                }
            }
            .collect()
    }
}
