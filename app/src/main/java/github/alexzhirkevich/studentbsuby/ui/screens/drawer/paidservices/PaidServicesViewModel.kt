package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.Bill
import github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo
import github.alexzhirkevich.studentbsuby.data.models.Receipt
import github.alexzhirkevich.studentbsuby.data.models.TuitionFeePayment
import github.alexzhirkevich.studentbsuby.repo.DataSource
import github.alexzhirkevich.studentbsuby.repo.PaidServicesRepository
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PaidServicesViewModel @Inject constructor(
    private val repo : PaidServicesRepository,
    @ApplicationContext private val context : Context,
    private val logger: Logger,
    ) : ViewModel(), Updatable {

    val dateFormat: DateFormat = SimpleDateFormat("dd.mm.yyy", Locale.getDefault())

    private val _paidInfoBills = mutableStateOf<DataState<List<Bill>>>(DataState.Empty)
    val paidInfoBills: State<DataState<List<Bill>>> = _paidInfoBills

    private val _hostelBills = mutableStateOf<DataState<List<Bill>>>(DataState.Empty)
    val hostelBills: State<DataState<List<Bill>>> = _hostelBills

    private val _tuitionFeeReceipts = mutableStateOf<DataState<List<TuitionFeePayment>>>(DataState.Empty)
    val tuitionFeeReceipts: State<DataState<List<TuitionFeePayment>>> = _tuitionFeeReceipts

    private val _academDebtReceipts = mutableStateOf<DataState<List<Receipt>>>(DataState.Empty)
    val academDebtReceipts: State<DataState<List<Receipt>>> = _academDebtReceipts

    private val _commonReceipts = mutableStateOf<DataState<List<Receipt>>>(DataState.Empty)
    val commonReceipts: State<DataState<List<Receipt>>> = _commonReceipts

    private val _paidInfo = mutableStateOf<DataState<PaidServicesInfo>>(DataState.Empty)
    val paidInfo: State<DataState<PaidServicesInfo>> = _paidInfo

    private val _isUpdating = mutableStateOf(false)
    override val isUpdating: State<Boolean>
        get() = _isUpdating

    @Volatile
    private var updatedComponents: Int = 5
    private val totalComponents = 5

    init {
        updateInternal(DataSource.All,false)
    }

    fun onEripHelpClicked(){
        val uri = Uri.parse(repo.eripUrl)
        val intent = Intent(Intent.ACTION_VIEW, uri)
            .apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        context.startActivity(intent)
    }

    override fun update() {
        updateInternal(DataSource.Remote, true)
    }

    private fun updateInternal(dataSource: DataSource, setState : Boolean){
        _isUpdating.value = setState
        updatedComponents = 0
        updatePaidInfoAndBills(dataSource)
        updateHostelBills(dataSource)
        updateCommonReceipts(dataSource)
        updateTuitionFeeReceipts(dataSource)
        updateAcademDebtReceipts(dataSource)
    }

    private var paidInfoJob: Job? = null

    private fun updatePaidInfoAndBills(dataSource: DataSource) =
        repo.getInfoAndBills(dataSource)
            .flowOn(Dispatchers.IO)
            .onStart {
                if (_paidInfo.value !is DataState.Success) {
                    _paidInfo.value = DataState.Loading
                }
            }
            .onCompletion {
                updatedComponents++
                if (updatedComponents == totalComponents)
                    _isUpdating.value = false
            }
            .onEmpty {
                if (_paidInfo.value !is DataState.Success) {
                    _paidInfo.value = DataState.Empty
                }
            }
            .onEach {
                _paidInfo.value = DataState.Success(it.first)
                _paidInfoBills.value = if (it.second.isNotEmpty())
                    DataState.Success(it.second) else DataState.Empty
            }
            .catch {
                if (_paidInfo.value !is DataState.Success) {
                    _paidInfo.value = DataState.Error(
                        R.string.error_load_paidinfo, it
                    )
                }
                if (_paidInfoBills.value !is DataState.Success && _paidInfoBills.value !is DataState.Empty) {
                    _paidInfoBills.value = DataState.Error(
                        R.string.error_load_bills, it
                    )
                }
                logger.log(
                    "Failed to get contract number",
                    tag = this@PaidServicesViewModel.javaClass.simpleName,
                    logLevel = Logger.LogLevel.Error,
                    cause = it
                )
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
            .also {
                paidInfoJob?.cancel()
                paidInfoJob = it
            }

    private var tuitionFeeJob = mutableStateOf<Job?>(null)
    private var commonReceiptsJob = mutableStateOf<Job?>(null)
    private var hostelBillsJob = mutableStateOf<Job?>(null)

    private fun updateHostelBills(dataSource: DataSource) = update(
        dataSource = repo.getHostelBills(dataSource),
        getValue = { _hostelBills.value },
        setValue = { _hostelBills.value = it },
        error = R.string.error_load_hostel_bills,
        logMessage = "Failed to load hostel bills",
        job = hostelBillsJob
    )

    private fun updateAcademDebtReceipts(dataSource: DataSource) = update(
        dataSource = repo.getAcademDebtFeeReceipts(dataSource),
        getValue = { _academDebtReceipts.value },
        setValue = { _academDebtReceipts.value = it },
        error = R.string.error_load_academ_debt_receipts,
        logMessage = "Failed to load academ debt receipts",
        job = commonReceiptsJob
    )

    private fun updateCommonReceipts(dataSource: DataSource) = update(
        dataSource = repo.getCommonReceipts(dataSource),
        getValue = { _commonReceipts.value },
        setValue = { _commonReceipts.value = it },
        error = R.string.error_load_receipts,
        logMessage = "Failed to load common receipts",
        job = commonReceiptsJob
    )

    private fun updateTuitionFeeReceipts(dataSource: DataSource) = update(
        dataSource = repo.getTuitionFeeReceipts(dataSource),
        getValue = { _tuitionFeeReceipts.value },
        setValue = { _tuitionFeeReceipts.value = it },
        error = R.string.error_load_tuition_fees,
        logMessage = "Failed to load tuition fee",
        job = tuitionFeeJob
    )

    private fun <T> update(
        dataSource: Flow<List<T>>,
        getValue: () -> DataState<List<T>>,
        setValue: (DataState<List<T>>) -> Unit,
        @StringRes error: Int,
        logMessage: String,
        job: MutableState<Job?>
    ) = dataSource
        .flowOn(Dispatchers.IO)
        .onStart {
            if (getValue() !is DataState.Success) {
                setValue(DataState.Loading)
            }
        }
        .onCompletion {
            updatedComponents++
            if (updatedComponents == totalComponents)
                _isUpdating.value = false
        }
        .onEmpty {
            if (getValue() !is DataState.Success) {
                setValue(DataState.Empty)
            }
        }
        .onEach {
            setValue(if (it.isNotEmpty())
                    DataState.Success(it)
                else DataState.Empty)
        }
        .catch {
            if (getValue() !is DataState.Success) {
                setValue(DataState.Error(error, it))
            }

            logger.log(
                logMessage,
                tag = this@PaidServicesViewModel.javaClass.simpleName,
                logLevel = Logger.LogLevel.Error,
                cause = it
            )
        }
        .flowOn(Dispatchers.Main)
        .launchIn(viewModelScope)
        .also {
            kotlin.runCatching {
                job.value?.cancel()
                job.value = it
            }
        }
}