package github.alexzhirkevich.studentbsuby.ui.screens.drawer.hostel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.HostelAdvert
import github.alexzhirkevich.studentbsuby.repo.HostelRepository
import github.alexzhirkevich.studentbsuby.repo.HostelState
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.exceptions.UsernameNotFoundException
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class HostelViewModel @Inject constructor(
    private val hostelRepository: HostelRepository,
    @ApplicationContext private val context : Context,
    private val logger: Logger,
) : ViewModel(),Updatable {

    private val _isUpdating = mutableStateOf(false)
    override val isUpdating: State<Boolean>
        get() = _isUpdating

    private val _hostelState = MutableStateFlow<DataState<HostelState>>(DataState.Loading)
    val hostelState: StateFlow<DataState<HostelState>> = _hostelState.asStateFlow()

    init {
        updateHostelState()
    }

    fun call(hostelAdvert: HostelAdvert) {
        hostelAdvert.phone?.let {
            val phone = it
                .filter {
                    it == '+' || it.isDigit()
                }
            val intent = Intent(
                Intent.ACTION_DIAL,
                Uri.fromParts("tel", phone, null)
            ).apply {
                flags = FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
    }

    fun showOnMap(ad: HostelState.Provided) {
        showOnMap(hostelRepository.getMapAddress(ad.address))
    }

    fun showOnMap(hostelAdvert: HostelAdvert) {
        hostelAdvert.address?.let {
            showOnMap(it)
        }
    }

    fun getHostelImage(ad: HostelState.Provided): String = hostelRepository.getImageForHostel(
        hostelRepository.getHostelNumber(ad.address)
    )

    override fun update() {
        _isUpdating.value = true
        updateHostelState()
    }

    private fun updateHostelState() {
        if (_hostelState.value is DataState.Error)
            _hostelState.tryEmit(DataState.Loading)

        hostelRepository
            .getHostelState()
            .onEmpty { _hostelState.tryEmit(DataState.Empty) }
            .onEach {
                _hostelState.tryEmit(DataState.Success(it))
            }.onCompletion {
                _isUpdating.value = false
            }
            .catch {
                if (_hostelState.value !is DataState.Success) {
                    _hostelState.tryEmit(
                        DataState.Error(
                            message = when (it) {
                                is UsernameNotFoundException ->
                                    R.string.error_username_not_found
                                else -> R.string.error_load_hostel
                            },
                            error = it
                        )
                    )
                }
                logger.log(
                    "Failed to load hostel state",
                    tag = javaClass.simpleName,
                    logLevel = Logger.LogLevel.Error,
                    cause = it
                )
            }
            .launchIn(viewModelScope)
    }

    private fun showOnMap(address : String){
        val uri = Uri.parse("geo:0,0?q=$address")
        val intent = Intent(Intent.ACTION_VIEW, uri)
            .apply {
                flags = FLAG_ACTIVITY_NEW_TASK
            }
        context.startActivity(intent)
    }

}