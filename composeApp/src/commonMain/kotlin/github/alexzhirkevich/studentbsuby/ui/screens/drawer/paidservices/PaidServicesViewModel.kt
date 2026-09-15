package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices

import github.alexzhirkevich.studentbsuby.data.models.Bill
import github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo
import github.alexzhirkevich.studentbsuby.data.models.Receipt
import github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment
import github.alexzhirkevich.studentbsuby.util.*
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication
import github.alexzhirkevich.studentbsuby.util.dispatchers.Dispatchers

class PaidServicesViewModel(
    override val isUpdating : StateCommunication<Boolean>,
    val paidInfoCommunication : StateCommunication<DataState<PaidServicesInfo>>,
    val paidInfoBillsCommunication : StateCommunication<DataState<List<Bill>>>,
    val hostelBillsCommunication : StateCommunication<DataState<List<Bill>>>,
    val academDebtReceiptsCommunication : StateCommunication<DataState<List<Receipt>>>,
    val commonReceiptsCommunication : StateCommunication<DataState<List<Receipt>>>,
    val tutionFeeCommunication: StateCommunication<DataState<List<TuitionFeePayment>>>,
    eventHandler : PaidServicesEventHandler,
    errorHandler: ErrorHandler,
    dispatchers: Dispatchers,
) : SuspendHandlerViewModel<PaidServicesEvent>(
    dispatchers = dispatchers,
    errorHandler = errorHandler,
    suspendEventHandler = eventHandler
), Updatable {

    override fun update() {
        handle(PaidServicesEvent.UpdateRequested)
    }
}
