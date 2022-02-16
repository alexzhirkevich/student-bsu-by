package github.alexzhirkevich.studentbsuby.ui.screens.drawer.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.repo.RemoteConfigRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val configRepository: RemoteConfigRepository
) : ViewModel() {

    init {
        FirebaseRemoteConfig.getInstance().fetchAndActivate()
    }

    fun onEmailClicked() {
        kotlin.runCatching {
            Intent(Intent.ACTION_SENDTO).apply {
                type = "text/html"
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(configRepository.mail()))
                putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
            }.let {
                context.startActivity(it)
            }
        }
    }

    fun onTgClicked() {
        kotlin.runCatching {
            Intent(Intent.ACTION_VIEW, Uri.parse(configRepository.telegram()))
                .apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }.let {
                    context.startActivity(it)
                }
        }
    }
}