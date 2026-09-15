package github.alexzhirkevich.studentbsuby.ui.screens.settings

import github.alexzhirkevich.studentbsuby.repo.SettingsRepository
import github.alexzhirkevich.studentbsuby.util.EventHandler
import github.alexzhirkevich.studentbsuby.util.PlatformActions
import github.alexzhirkevich.studentbsuby.util.communication.StateMapper
import github.alexzhirkevich.studentbsuby.util.logger.Logger

interface SettingsEventHandler : EventHandler<SettingsEvent>

class SettingsEventHandlerImpl(
    private val settingsRepository: SettingsRepository,
    private val mapper : StateMapper<SettingsState>,
    private val logger: Logger,
    private val platformActions: PlatformActions
) : SettingsEventHandler {

    override fun handle(event: SettingsEvent) = when(event){
        is SettingsEvent.CollectCrashlytics -> {
            settingsRepository.collectCrashlytics = event.enabled
            mapper.map(mapper.current.copy(collectCrashlytics = event.enabled))
        }
        is SettingsEvent.CollectStatistic -> {
            settingsRepository.collectStatistics = event.enabled
            mapper.map(mapper.current.copy(collectStatistic = event.enabled))
        }
        is SettingsEvent.NotificationsEnabled -> {
            settingsRepository.synchronizationEnabled = event.enabled
            mapper.map(mapper.current.copy(notificationsEnabled = event.enabled))
        }
        is SettingsEvent.ShareLogs -> {
            runCatching {
                logger.share()
            }
            Unit
        }
        is SettingsEvent.DontKillMyApp -> {
            runCatching {
                platformActions.openUrl("https://dontkillmyapp.com/")
            }
            Unit
        }
    }
}
