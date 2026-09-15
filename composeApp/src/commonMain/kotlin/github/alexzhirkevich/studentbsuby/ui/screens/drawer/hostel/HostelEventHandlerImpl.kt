package github.alexzhirkevich.studentbsuby.ui.screens.drawer.hostel

import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.error_load_hostel
import github.alexzhirkevich.studentbsuby.repo.DataSource
import github.alexzhirkevich.studentbsuby.repo.HostelRepository
import github.alexzhirkevich.studentbsuby.repo.HostelState
import github.alexzhirkevich.studentbsuby.util.BaseSuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.ConnectivityManager
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.PlatformActions
import github.alexzhirkevich.studentbsuby.util.SuspendEventHandler
import github.alexzhirkevich.studentbsuby.util.communication.Mapper
import github.alexzhirkevich.studentbsuby.util.communication.StateMapper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty

interface HostelEventHandler : SuspendEventHandler<HostelEvent>

class HostelEventHandlerImpl(
    hostelRepository: HostelRepository,
    connectivityManager: ConnectivityManager,
    isUpdatingMapper: Mapper<Boolean>,
    hostelStateMapper: StateMapper<DataState<HostelState>>,
    platformActions: PlatformActions
) : HostelEventHandler, SuspendEventHandler<HostelEvent> by SuspendEventHandler.from(
    UpdateRequestedHandler(
        hostelRepository = hostelRepository,
        connectivityManager = connectivityManager,
        isUpdatingMapper = isUpdatingMapper,
        hostelStateMapper = hostelStateMapper
    ),
    ShowHostelOnMapClickedHandler(platformActions, hostelRepository),
    ShowAdOnMapClickedHandler(platformActions, hostelRepository),
    CallAdClickedHandler(platformActions)
)

private class UpdateRequestedHandler(
    private val hostelRepository: HostelRepository,
    private val connectivityManager: ConnectivityManager,
    private val isUpdatingMapper: Mapper<Boolean>,
    private val hostelStateMapper: StateMapper<DataState<HostelState>>
) : BaseSuspendEventHandler<HostelEvent.UpdateRequested>(
    HostelEvent.UpdateRequested::class
){

    override suspend fun launch() {
        isUpdatingMapper.map(false)
        hostelStateMapper.map(DataState.Loading)
        update(DataSource.All)
        connectivityManager.isNetworkConnected.collect {
            if (it){
                update(DataSource.Remote)
            }
        }
    }

    override suspend fun handle(event: HostelEvent.UpdateRequested) {
        isUpdatingMapper.map(true)
        update(DataSource.Remote)
        isUpdatingMapper.map(false)
    }

    private suspend fun update(dataSource: DataSource){
        hostelRepository.get(dataSource)
            .onEach {
                hostelStateMapper.map(DataState.Success(it))
            }
            .onEmpty {
                hostelStateMapper.map(DataState.Empty)
            }
            .catch {
                if (hostelStateMapper.current !is DataState.Success){
                    hostelStateMapper.map(DataState.Error(
                        Res.string.error_load_hostel, it
                    ))
                }
            }
            .collect()
    }
}

private class ShowHostelOnMapClickedHandler(
    private val platformActions: PlatformActions,
    private val hostelRepository: HostelRepository,
) : BaseSuspendEventHandler<HostelEvent.ShowHostelOnMapClicked>(
    HostelEvent.ShowHostelOnMapClicked::class
) {
    override suspend fun handle(event: HostelEvent.ShowHostelOnMapClicked) {
        val address = hostelRepository.getMapAddress(event.hostel.address)
        platformActions.openUrl("geo:0,0?q=$address")
    }
}

private class ShowAdOnMapClickedHandler(
    private val platformActions: PlatformActions,
    private val hostelRepository: HostelRepository,
) : BaseSuspendEventHandler<HostelEvent.ShowAdOnMapClicked>(
    HostelEvent.ShowAdOnMapClicked::class
) {

    override suspend fun handle(event: HostelEvent.ShowAdOnMapClicked) {
        event.ad.address?.let {
            val address = hostelRepository.getMapAddress(it)
            platformActions.openUrl("geo:0,0?q=$address")
        }
    }
}

private class CallAdClickedHandler(
    private val platformActions: PlatformActions
) : BaseSuspendEventHandler<HostelEvent.CallClicked>(
    HostelEvent.CallClicked::class
){
    override suspend fun handle(event: HostelEvent.CallClicked) {
        event.ad.phone?.let {
            val phone = it.filter { char -> char == '+' || char.isDigit() }
            platformActions.openUrl("tel:$phone")
        }
    }
}
