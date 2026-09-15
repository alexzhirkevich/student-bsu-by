package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices

import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.error_load_paidinfo
import github.alexzhirkevich.studentbsuby.resources.error_load_hostel_bills
import github.alexzhirkevich.studentbsuby.resources.error_load_academ_debt_receipts
import github.alexzhirkevich.studentbsuby.resources.error_load_receipts
import github.alexzhirkevich.studentbsuby.resources.error_load_tuition_fees
import github.alexzhirkevich.studentbsuby.data.models.Bill
import github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo
import github.alexzhirkevich.studentbsuby.data.models.Receipt
import github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment
import github.alexzhirkevich.studentbsuby.repo.DataSource
import github.alexzhirkevich.studentbsuby.repo.PaidServicesRepository
import github.alexzhirkevich.studentbsuby.util.*
import github.alexzhirkevich.studentbsuby.util.communication.Mapper
import github.alexzhirkevich.studentbsuby.util.communication.StateMapper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

interface PaidServicesEventHandler : SuspendEventHandler<PaidServicesEvent>

class PaidServicesEventHandlerImpl(
    private val paidServicesRepository: PaidServicesRepository,
    private val connectivityManager: ConnectivityManager,
    private val isUpdatingMapper : Mapper<Boolean>,
    private val paidInfoBillsMapper : StateMapper<DataState<List<Bill>>>,
    private val hostelBillsMapper : StateMapper<DataState<List<Bill>>>,
    private val academDebtReceiptsMapper : StateMapper<DataState<List<Receipt>>>,
    private val commonReceiptsMapper : StateMapper<DataState<List<Receipt>>>,
    private val tutionFeePaymentsMapper: StateMapper<DataState<List<TuitionFeePayment>>>,
    private val paidInfoMapper : StateMapper<DataState<PaidServicesInfo>>,
    private val platformActions: PlatformActions
) : PaidServicesEventHandler, SuspendEventHandler<PaidServicesEvent> by SuspendEventHandler.from(
    UpdateRequestedHandler(
        paidServicesRepository = paidServicesRepository,
        connectivityManager = connectivityManager,
        isUpdatingMapper = isUpdatingMapper,
        paidInfoBillsMapper = paidInfoBillsMapper,
        hostelBillsMapper = hostelBillsMapper,
        academDebtReceiptsMapper = academDebtReceiptsMapper,
        tutionFeePaymentsMapper = tutionFeePaymentsMapper,
        commonReceiptsMapper = commonReceiptsMapper,
        paidInfoMapper = paidInfoMapper
    ),
    EripHelpClickedHandler(platformActions, paidServicesRepository)
)

private class UpdateRequestedHandler(
    private val paidServicesRepository: PaidServicesRepository,
    private val connectivityManager: ConnectivityManager,
    private val isUpdatingMapper : Mapper<Boolean>,
    private val paidInfoBillsMapper : StateMapper<DataState<List<Bill>>>,
    private val hostelBillsMapper : StateMapper<DataState<List<Bill>>>,
    private val academDebtReceiptsMapper : StateMapper<DataState<List<Receipt>>>,
    private val tutionFeePaymentsMapper : StateMapper<DataState<List<TuitionFeePayment>>>,
    private val commonReceiptsMapper : StateMapper<DataState<List<Receipt>>>,
    private val paidInfoMapper : StateMapper<DataState<PaidServicesInfo>>
) : BaseSuspendEventHandler<PaidServicesEvent.UpdateRequested>(
    PaidServicesEvent.UpdateRequested::class
) {

    override suspend fun launch() {
        isUpdatingMapper.map(false)
        paidInfoBillsMapper.map(DataState.Loading)
        hostelBillsMapper.map(DataState.Loading)
        academDebtReceiptsMapper.map(DataState.Loading)
        tutionFeePaymentsMapper.map(DataState.Loading)
        commonReceiptsMapper.map(DataState.Loading)
        paidInfoMapper.map(DataState.Loading)
        update(DataSource.All)
        connectivityManager.isNetworkConnected.collect {
            if (it){
                update(DataSource.Remote)
            }
        }
    }

    override suspend fun handle(event: PaidServicesEvent.UpdateRequested) {
        isUpdatingMapper.map(true)
        update(DataSource.Remote)
        isUpdatingMapper.map(false)
    }

    private suspend fun update(source: DataSource) {
        paidServicesRepository.getInfoAndBills(source)
            .process(
                { DataState.Success(
                    paidInfoMapper.current.valueOrNull() to
                            paidInfoBillsMapper.current.valueOrNull())
                },
                {
                    if (it is DataState.Success) {
                        paidInfoMapper.map( it.value.first?.let {
                            DataState.Success(it)
                        } ?: DataState.Error(Res.string.error_load_paidinfo))

                        paidInfoBillsMapper.map(
                            it.value.second?.let {
                                if (it.isEmpty())
                                    DataState.Empty
                                else DataState.Success(it)
                            } ?: DataState.Error(Res.string.error_load_paidinfo)
                        )
                    }
                },
                Res.string.error_load_paidinfo
            )
        paidServicesRepository.getHostelBills(source)
            .process(
                hostelBillsMapper::current,
                {
                    hostelBillsMapper.map(if (it.valueOrNull()?.isEmpty() == true)
                        DataState.Empty else it
                    )
                },
                Res.string.error_load_hostel_bills
            )
        paidServicesRepository.getAcademDebtFeeReceipts(source)
            .process(
                academDebtReceiptsMapper::current,
                {
                    academDebtReceiptsMapper.map(if (it.valueOrNull()?.isEmpty() == true)
                        DataState.Empty else it
                    )
                },
                Res.string.error_load_academ_debt_receipts
            )
        paidServicesRepository.getCommonReceipts(source)
            .process(
                commonReceiptsMapper::current,
                {
                    commonReceiptsMapper.map(if (it.valueOrNull()?.isEmpty() == true)
                        DataState.Empty else it
                    )
                },
                Res.string.error_load_receipts
            )
        paidServicesRepository.getTuitionFeeReceipts()
            .process(
                tutionFeePaymentsMapper::current,
                {
                    tutionFeePaymentsMapper.map(if (it.valueOrNull()?.isEmpty() == true)
                        DataState.Empty else it
                    )
                },
                Res.string.error_load_tuition_fees
            )
    }

    private suspend fun <T> Flow<T>.process(
        get : () -> DataState<T>,
        set : (DataState<T>) -> Unit,
        errorMsg : StringResource
    ) = coroutineScope {
        launch {
            onEach {
                set(DataState.Success(it))
            }.onEmpty {
                set(DataState.Empty)
            }.catch {
                if (get() !is DataState.Success) {
                    set(DataState.Error(errorMsg, it))
                }
            }.collect()
        }
    }
}

private class EripHelpClickedHandler(
    private val platformActions: PlatformActions,
    private val paidServicesRepository: PaidServicesRepository
) : BaseSuspendEventHandler<PaidServicesEvent.EripHelpClicked>(
    PaidServicesEvent.EripHelpClicked::class
) {
    override suspend fun handle(event: PaidServicesEvent.EripHelpClicked) {
        platformActions.openUrl(paidServicesRepository.eripUrl)
    }
}
