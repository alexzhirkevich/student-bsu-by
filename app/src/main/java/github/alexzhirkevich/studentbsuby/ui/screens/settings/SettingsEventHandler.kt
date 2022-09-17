package github.alexzhirkevich.studentbsuby.ui.screens.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.wanghong.cromappwhitelist.AppWhitelist
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.repo.SettingsRepository
import github.alexzhirkevich.studentbsuby.util.EventHandler
import github.alexzhirkevich.studentbsuby.util.communication.StateMapper
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class SettingsEventHandler(
    settingsRepository: SettingsRepository,
    mapper : StateMapper<SettingsState>,
    logger: Logger,
    context: Context
) : EventHandler<SettingsEvent> {

    private val notificationsEnabledHandler = NotificationsEnabledHandler(settingsRepository, mapper)
    private val collectStatisticHandler = CollectStatisticHandler(settingsRepository, mapper)
    private val collectCrashlyticsHandler = CollectCrashlyticsHandler(settingsRepository, mapper,context)
    private val shareLogsHandler = ShareLogsHandler(context, logger)
    private val dontKillMyAppHandler = DontKillMyAppHandler(context)
    private val backgroundActivityClickedHandler = BackgroundActivityClickedHandler(context)
    private val autoStartClickedHandler = AutoStartClickedHandler(context)

    override fun handle(event: SettingsEvent) = when(event){
        is SettingsEvent.CollectCrashlytics -> collectCrashlyticsHandler.handle(event)
        is SettingsEvent.CollectStatistic -> collectStatisticHandler.handle(event)
        is SettingsEvent.NotificationsEnabled -> notificationsEnabledHandler.handle(event)
        is SettingsEvent.ShareLogs -> shareLogsHandler.handle(event)
        is SettingsEvent.DontKillMyApp -> dontKillMyAppHandler.handle(event)
        is SettingsEvent.AutoStartClicked -> autoStartClickedHandler.handle(event)
        is SettingsEvent.BackgroundActivityClicked -> backgroundActivityClickedHandler.handle(event)
    }
}


@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
private class NotificationsEnabledHandler(
    private val settingsRepository: SettingsRepository,
    private val mapper : StateMapper<SettingsState>
) : EventHandler<SettingsEvent.NotificationsEnabled> {

    override fun handle(event: SettingsEvent.NotificationsEnabled) {
        settingsRepository.synchronizationEnabled = event.enabled
        mapper.map(mapper.current.copy(notificationsEnabled = event.enabled))
    }
}

@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
private class CollectStatisticHandler(
    private val settingsRepository: SettingsRepository,
    private val mapper : StateMapper<SettingsState>
) : EventHandler<SettingsEvent.CollectStatistic> {

    override fun handle(event: SettingsEvent.CollectStatistic) {
        settingsRepository.collectStatistics = event.enabled
        mapper.map(mapper.current.copy(collectStatistic = event.enabled))
    }
}

@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
private class CollectCrashlyticsHandler(
    private val settingsRepository: SettingsRepository,
    private val mapper : StateMapper<SettingsState>,
    private val context: Context
) : EventHandler<SettingsEvent.CollectCrashlytics> {

    override fun handle(event: SettingsEvent.CollectCrashlytics) {
        settingsRepository.collectCrashlytics = event.enabled
        mapper.map(mapper.current.copy(collectCrashlytics = event.enabled))
    }
}

private class ShareLogsHandler(
    private val context: Context,
    private val logger: Logger
) : EventHandler<SettingsEvent.ShareLogs> {
    override fun handle(event: SettingsEvent.ShareLogs) =
        logger.share(context)
}

private class DontKillMyAppHandler(
    private val context: Context
) : EventHandler<SettingsEvent.DontKillMyApp> {
    override fun handle(event: SettingsEvent.DontKillMyApp) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://dontkillmyapp.com/")
            ).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    }
}

private class AutoStartClickedHandler(
    private val context: Context
) : EventHandler<SettingsEvent.AutoStartClicked>{
    override fun handle(event: SettingsEvent.AutoStartClicked) {
        if (AppWhitelist.hasAutoStartSetting(context))
            AppWhitelist.settingForAutoStart(context)
        else Toast.makeText(context, R.string.no_need, Toast.LENGTH_SHORT).show()
    }
}

private class BackgroundActivityClickedHandler(
    private val context: Context
) : EventHandler<SettingsEvent.BackgroundActivityClicked>{
    override fun handle(event: SettingsEvent.BackgroundActivityClicked) {
        if (AppWhitelist.hasBatterySaverSetting(context))
            AppWhitelist.settingForBatterySaver(context)
        else Toast.makeText(context, R.string.no_need,Toast.LENGTH_SHORT).show()
    }
}