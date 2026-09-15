package github.alexzhirkevich.studentbsuby.ui.screens.drawer

import androidx.compose.ui.graphics.ImageBitmap
import github.alexzhirkevich.studentbsuby.data.models.User
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.ErrorHandler
import github.alexzhirkevich.studentbsuby.util.SuspendHandlerViewModel
import github.alexzhirkevich.studentbsuby.util.communication.Communication
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication
import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers

class ProfileViewModel(
    val connectivityCommunication: Communication<ConnectivityUi>,
    val userCommunication: StateCommunication<DataState<User>>,
    val imageCommunication: StateCommunication<DataState<ImageBitmap>>,
    dispatchers: Dispatchers,
    errorHandler: ErrorHandler,
    eventHandler: ProfileEventHandler
) : SuspendHandlerViewModel<ProfileEvent>(
    dispatchers = dispatchers,
    errorHandler = errorHandler,
    suspendEventHandler = eventHandler
)
