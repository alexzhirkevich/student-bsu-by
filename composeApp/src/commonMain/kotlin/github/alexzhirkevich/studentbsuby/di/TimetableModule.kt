package github.alexzhirkevich.studentbsuby.di

import github.alexzhirkevich.studentbsuby.repo.TimetableRepository
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable.Timetable
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable.TimetableEventHandlerImpl
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable.TimetableViewModel
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.communication.StateFlowCommunication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Mirrors the original TimetableModule (ViewModelComponent).
 */
val timetableModule = module {

    factory { TimetableRepository(get(), get(), get()) }

    viewModel {
        val isUpdating = StateFlowCommunication(false)
        val timetable = StateFlowCommunication<DataState<Timetable>>(DataState.Empty)

        val eventHandler = TimetableEventHandlerImpl(
            timetableRepository = get(),
            connectivityManager = get(),
            calendar = get(),
            timetableMapper = timetable,
            isUpdatingMapper = isUpdating
        )

        TimetableViewModel(
            isUpdating = isUpdating,
            timetableCommunication = timetable,
            dispatchers = get(),
            errorHandler = get(),
            eventHandler = eventHandler,
            calendar = get()
        )
    }
}
