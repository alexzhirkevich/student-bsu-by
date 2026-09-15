package github.alexzhirkevich.studentbsuby.di

import github.alexzhirkevich.studentbsuby.data.models.Subject
import github.alexzhirkevich.studentbsuby.repo.CurrentSemesterRepository
import github.alexzhirkevich.studentbsuby.repo.SubjectsRepository
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.subjects.SubjectsEventHandlerImpl
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.subjects.SubjectsViewModel
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.communication.StateFlowCommunication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Mirrors the original SubjectsModule (ViewModelComponent).
 */
val subjectsModule = module {

    // Was @Singleton on the class itself.
    single { SubjectsRepository(get(), get(), get()) }

    // Unqualified ObservableSettings resolves the default preferences file
    // (the original injected unqualified SharedPreferences = default prefs).
    factory { CurrentSemesterRepository(get(), get(), get()) }

    viewModel {
        val isUpdating = StateFlowCommunication(false)
        val semester = StateFlowCommunication(0)
        val search = StateFlowCommunication("")
        val withExam = StateFlowCommunication(false)
        val withCredit = StateFlowCommunication(false)
        val subjects = StateFlowCommunication<DataState<List<List<Subject>>>>(DataState.Loading)
        val visibleSubjects = StateFlowCommunication<DataState<List<List<Subject>>>>(DataState.Loading)

        val eventHandler = SubjectsEventHandlerImpl(
            subjectRepository = get(),
            currentSemesterRepository = get(),
            connectivityManager = get(),
            isUpdatingMapper = isUpdating,
            semesterMapper = semester,
            searchMapper = search,
            withExamMapper = withExam,
            withCreditMapper = withCredit,
            subjectsMapper = subjects,
            visibleSubjectsMapper = visibleSubjects
        )

        SubjectsViewModel(
            isUpdating = isUpdating,
            semesterCommunication = semester,
            searchCommunication = search,
            withExamCommunication = withExam,
            withCreditCommunication = withCredit,
            subjectsCommunication = subjects,
            visibleSubjectsCommunication = visibleSubjects,
            eventHandler = eventHandler,
            errorHandler = get(),
            dispatchers = get()
        )
    }
}
