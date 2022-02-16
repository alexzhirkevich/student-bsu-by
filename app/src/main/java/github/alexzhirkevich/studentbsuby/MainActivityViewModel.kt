package github.alexzhirkevich.studentbsuby

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.repo.ApplicationVersion
import github.alexzhirkevich.studentbsuby.repo.RemoteConfigRepository
import github.alexzhirkevich.studentbsuby.repo.UpdateRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository,
    private val updateRepository: UpdateRepository
) : ViewModel() {

    private val _showUpdateDialog = mutableStateOf(false)
    val showUpdateDialog: State<Boolean> get() = _showUpdateDialog

    init {
        remoteConfigRepository.init()
    }

    fun provideActivity(activity: ComponentActivity) {
        activity.lifecycleScope.launchWhenCreated {
            val immediate = remoteConfigRepository.getMinimumStableVersionIfNeeded() != null
            updateRepository.tryUpdate(
                activity,
                immediate,
                onFailedToInAppUpdate = { openGooglePlay(activity) }) {
                _showUpdateDialog.value = true
                while (_showUpdateDialog.value) {
                    delay(100)
                }
            }
        }
    }

    private fun openGooglePlay(context: Context){
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=${BuildConfig.APPLICATION_ID}")
                )
            )
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                )
            )
        }
    }
    fun onUpdateClicked(context: Context) {
        _showUpdateDialog.value = false
    }


}