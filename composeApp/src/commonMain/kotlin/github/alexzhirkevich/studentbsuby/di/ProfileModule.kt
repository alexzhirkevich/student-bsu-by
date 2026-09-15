package github.alexzhirkevich.studentbsuby.di

import androidx.compose.ui.graphics.ImageBitmap
import github.alexzhirkevich.studentbsuby.data.models.User
import github.alexzhirkevich.studentbsuby.repo.PhotoRepository
import github.alexzhirkevich.studentbsuby.repo.UserRepository
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.ConnectivityUi
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.ConnectivityUiSerializer
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.DrawerRoute
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.ProfileEventHandlerImpl
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.ProfileViewModel
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.communication.BroadcastReceiverCommunication
import github.alexzhirkevich.studentbsuby.util.communication.BroadcastReceiverMapper
import github.alexzhirkevich.studentbsuby.util.communication.StateFlowCommunication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Mirrors the original ProfileModule (ViewModelComponent).
 */
val profileModule = module {

    factory { UserRepository(get(), get(), get()) }

    factory { PhotoRepository(get(), get()) }

    viewModel {
        val imageCommunication = StateFlowCommunication<DataState<ImageBitmap>>(DataState.Loading)
        val userCommunication = StateFlowCommunication<DataState<User>>(DataState.Loading)
        val routeCommunication = StateFlowCommunication<DrawerRoute>(DrawerRoute.Timetable)
        val connectivityCommunication = BroadcastReceiverCommunication("ConnectivityUi", ConnectivityUiSerializer)
        val connectivityMapper = BroadcastReceiverMapper("ConnectivityUi", ConnectivityUiSerializer)

        val eventHandler = ProfileEventHandlerImpl(
            dispatchers = get(),
            connectivityManager = get(),
            loginRepository = get(),
            userRepository = get(),
            photoRepository = get(),
            routeMapper = routeCommunication,
            connectivityMapper = connectivityMapper,
            imageMapper = imageCommunication,
            userMapper = userCommunication
        )

        ProfileViewModel(
            connectivityCommunication = connectivityCommunication,
            userCommunication = userCommunication,
            imageCommunication = imageCommunication,
            dispatchers = get(),
            errorHandler = get(),
            eventHandler = eventHandler
        )
    }
}
