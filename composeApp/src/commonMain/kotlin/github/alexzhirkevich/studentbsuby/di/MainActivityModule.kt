package github.alexzhirkevich.studentbsuby.di

import github.alexzhirkevich.studentbsuby.MainActivityEventHandlerImpl
import github.alexzhirkevich.studentbsuby.MainActivityViewModel
import github.alexzhirkevich.studentbsuby.repo.RemoteConfigRepository
import github.alexzhirkevich.studentbsuby.repo.ReviewRepository
import github.alexzhirkevich.studentbsuby.repo.UpdateRepository
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication
import github.alexzhirkevich.studentbsuby.util.communication.StateFlowCommunication
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Mirrors the original MainActivityModule (ViewModelComponent).
 * `@ShowUpdateQualifier` -> named("ShowUpdate").
 */
val mainModule = module {

    // MainActivityViewModel and MainActivityEventHandlerImpl must share this exact
    // instance (the original module kept one StateFlowCommunication(false) and handed
    // it to both). The stored instance is a StateFlowCommunication<Boolean>, so it
    // also serves as the handler's Mapper<Boolean>.
    single<StateCommunication<Boolean>>(named("ShowUpdate")) { StateFlowCommunication(false) }

    factory { RemoteConfigRepository(get(), get(), get()) }

    factory { UpdateRepository(get()) }

    factory { ReviewRepository(get(), get()) }

    viewModel {
        val showUpdate = get<StateCommunication<Boolean>>(named("ShowUpdate"))
        val eventHandler = MainActivityEventHandlerImpl(
            dispatchers = get(),
            remoteConfigRepository = get(),
            updateRepository = get(),
            reviewRepository = get(),
            showUpdateRequired = showUpdate as StateFlowCommunication<Boolean>,
            platformActions = get()
        )
        MainActivityViewModel(
            showUpdateDialog = showUpdate,
            dispatchers = get(),
            errorHandler = get(),
            eventHandler = eventHandler
        )
    }
}
