package github.alexzhirkevich.studentbsuby.ui.screens.drawer.data

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.repo.SettingsRepository
import javax.inject.Inject



@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

}