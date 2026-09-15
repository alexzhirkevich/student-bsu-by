package github.alexzhirkevich.studentbsuby.di

import github.alexzhirkevich.studentbsuby.data.models.News
import github.alexzhirkevich.studentbsuby.repo.NewsRepository
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.news.NewsEventHandlerImpl
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.news.NewsViewModel
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.communication.StateFlowCommunication
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Mirrors the original NewsModule (ViewModelComponent).
 */
val newsModule = module {

    factory {
        NewsRepository(
            api = get(),
            dao = get(),
            baseUrl = get(named("BaseUrl")),
        )
    }

    viewModel {
        val newsCommunication = StateFlowCommunication<DataState<List<News>>>(DataState.Loading)
        val isUpdatingCommunication = StateFlowCommunication(false)

        val eventHandler = NewsEventHandlerImpl(
            newsRepository = get(),
            connectivityManager = get(),
            isUpdatingMapper = isUpdatingCommunication,
            newsMapper = newsCommunication
        )

        NewsViewModel(
            isUpdating = isUpdatingCommunication,
            newsCommunication = newsCommunication,
            errorHandler = get(),
            dispatchers = get(),
            eventHandler = eventHandler
        )
    }
}
