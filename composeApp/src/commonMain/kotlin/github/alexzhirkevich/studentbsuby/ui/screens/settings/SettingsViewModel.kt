package github.alexzhirkevich.studentbsuby.ui.screens.settings

import androidx.lifecycle.ViewModel
import github.alexzhirkevich.studentbsuby.util.EventHandler
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication

class SettingsViewModel(
    val state : StateCommunication<SettingsState>,
    handler : SettingsEventHandler
) : ViewModel(), EventHandler<SettingsEvent> by handler
