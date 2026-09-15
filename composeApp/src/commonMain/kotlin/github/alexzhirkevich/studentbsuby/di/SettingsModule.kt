package github.alexzhirkevich.studentbsuby.di

import github.alexzhirkevich.studentbsuby.repo.SettingsRepository
import github.alexzhirkevich.studentbsuby.ui.screens.settings.SettingsEventHandlerImpl
import github.alexzhirkevich.studentbsuby.ui.screens.settings.SettingsState
import github.alexzhirkevich.studentbsuby.ui.screens.settings.SettingsViewModel
import github.alexzhirkevich.studentbsuby.util.communication.StateFlowCommunication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Mirrors the original SettingsModule (ViewModelComponent).
 */
val settingsModule = module {

    // Unqualified ObservableSettings resolves the default preferences file;
    // AnalyticsReporter/CrashReporter/WorkerManager come from the platform modules.
    factory { SettingsRepository(get(), get(), get(), get()) }

    viewModel {
        val repo = get<SettingsRepository>()
        val stateCommunication = StateFlowCommunication(
            initial = SettingsState(
                notificationsEnabled = repo.synchronizationEnabled,
                collectStatistic = repo.collectStatistics,
                collectCrashlytics = repo.collectCrashlytics
            )
        )
        val eventHandler = SettingsEventHandlerImpl(
            settingsRepository = repo,
            mapper = stateCommunication,
            logger = get(),
            platformActions = get()
        )
        SettingsViewModel(
            state = stateCommunication,
            handler = eventHandler
        )
    }
}
