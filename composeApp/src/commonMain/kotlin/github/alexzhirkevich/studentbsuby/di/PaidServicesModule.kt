package github.alexzhirkevich.studentbsuby.di

import github.alexzhirkevich.studentbsuby.data.models.Bill
import github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo
import github.alexzhirkevich.studentbsuby.data.models.Receipt
import github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment
import github.alexzhirkevich.studentbsuby.repo.PaidServicesRepository
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices.PaidServicesEventHandlerImpl
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices.PaidServicesViewModel
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.communication.StateFlowCommunication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Mirrors the original PaidServicesModule (ViewModelComponent).
 */
val paidServicesModule = module {

    factory { PaidServicesRepository(get(), get(), get(), get()) }

    viewModel {
        val isUpdating = StateFlowCommunication(false)
        val paidInfoBills = StateFlowCommunication<DataState<List<Bill>>>(DataState.Loading)
        val hostelBills = StateFlowCommunication<DataState<List<Bill>>>(DataState.Loading)
        val academDebtReceipts = StateFlowCommunication<DataState<List<Receipt>>>(DataState.Loading)
        val commonReceipts = StateFlowCommunication<DataState<List<Receipt>>>(DataState.Loading)
        val paidInfo = StateFlowCommunication<DataState<PaidServicesInfo>>(DataState.Loading)
        val tutionFee = StateFlowCommunication<DataState<List<TuitionFeePayment>>>(DataState.Loading)

        val eventHandler = PaidServicesEventHandlerImpl(
            paidServicesRepository = get(),
            connectivityManager = get(),
            isUpdatingMapper = isUpdating,
            paidInfoBillsMapper = paidInfoBills,
            hostelBillsMapper = hostelBills,
            academDebtReceiptsMapper = academDebtReceipts,
            commonReceiptsMapper = commonReceipts,
            tutionFeePaymentsMapper = tutionFee,
            paidInfoMapper = paidInfo,
            platformActions = get()
        )

        PaidServicesViewModel(
            isUpdating = isUpdating,
            paidInfoCommunication = paidInfo,
            paidInfoBillsCommunication = paidInfoBills,
            hostelBillsCommunication = hostelBills,
            academDebtReceiptsCommunication = academDebtReceipts,
            commonReceiptsCommunication = commonReceipts,
            tutionFeeCommunication = tutionFee,
            eventHandler = eventHandler,
            errorHandler = get(),
            dispatchers = get()
        )
    }
}
