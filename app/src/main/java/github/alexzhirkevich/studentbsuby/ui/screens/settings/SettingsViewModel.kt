package github.alexzhirkevich.studentbsuby.ui.screens.settings

import android.content.Context
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.sourceInformationMarkerEnd
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.ViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.repo.SettingsRepository
import github.alexzhirkevich.studentbsuby.ui.theme.ThemeSelector
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val logger : Logger,
) : ViewModel() {

    private val _notificationsEnabled = mutableStateOf(settingsRepository.synchronizationEnabled)
    val notificationsEnabled : State<Boolean> get() = _notificationsEnabled
    fun setNotificationsEnabled(enabled : Boolean){
        _notificationsEnabled.value = enabled
        settingsRepository.synchronizationEnabled = enabled
    }

    private val _collectStatistics = mutableStateOf(settingsRepository.collectStatistics)
    val collectStatistics : State<Boolean> get() = _collectStatistics
    fun setCollectStatistics(enabled : Boolean){
        _collectStatistics.value = enabled
        settingsRepository.collectStatistics = enabled
    }

    private val _collectCrashlytics = mutableStateOf(settingsRepository.collectCrashlytics)
    val collectCrashlytics : State<Boolean> get() = _collectCrashlytics
    fun setCollectCrashlytics(enabled : Boolean){
        _collectCrashlytics.value = enabled
        settingsRepository.collectCrashlytics = enabled
    }

    fun shareLogs(context: Context){
        logger.share(context)
    }
}