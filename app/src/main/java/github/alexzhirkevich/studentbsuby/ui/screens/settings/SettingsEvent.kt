package github.alexzhirkevich.studentbsuby.ui.screens.settings

import github.alexzhirkevich.studentbsuby.util.Event

sealed interface SettingsEvent : Event {
    class NotificationsEnabled(val enabled : Boolean) : SettingsEvent
    class CollectStatistic(val enabled: Boolean) : SettingsEvent
    class CollectCrashlytics(val enabled : Boolean) : SettingsEvent
    object ShareLogs : SettingsEvent
    object DontKillMyApp : SettingsEvent
    object BackgroundActivityClicked : SettingsEvent
    object AutoStartClicked : SettingsEvent
}