package github.alexzhirkevich.studentbsuby.ui.screens.drawer.subjects;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\b\u0010-\u001a\u00020.H\u0002J\u000e\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020\u0010J\u000e\u00101\u001a\u00020.2\u0006\u00102\u001a\u00020\u0007J\u0016\u00103\u001a\u00020.2\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u000eJ\b\u00104\u001a\u00020.H\u0016J\u0010\u00105\u001a\u00020&2\u0006\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u00020&2\u0006\u00106\u001a\u000207H\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0011\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00140\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0016\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00140\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u001a8F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001a8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100\u001a8F\u00a2\u0006\u0006\u001a\u0004\b \u0010\u001cR)\u0010!\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00140\u00130\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R)\u0010\'\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00140\u00130\"\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010$R\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001a8F\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u001cR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001a8F\u00a2\u0006\u0006\u001a\u0004\b,\u0010\u001c\u00a8\u00069"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/screens/drawer/subjects/SubjectsViewModel;", "Landroidx/lifecycle/ViewModel;", "Lgithub/alexzhirkevich/studentbsuby/util/Updatable;", "subjectsRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/SubjectsRepository;", "currentSemesterRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/Repository;", "", "logger", "Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;", "(Lgithub/alexzhirkevich/studentbsuby/repo/SubjectsRepository;Lgithub/alexzhirkevich/studentbsuby/repo/Repository;Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;)V", "_currentSemester", "Landroidx/compose/runtime/MutableState;", "_isUpdating", "", "_searchText", "", "_subjects", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lgithub/alexzhirkevich/studentbsuby/util/DataState;", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/Subject;", "_visibleSubjects", "_withCredit", "_withExam", "currentSemester", "Landroidx/compose/runtime/State;", "getCurrentSemester", "()Landroidx/compose/runtime/State;", "currentSemesterChangedByUser", "isUpdating", "searchText", "getSearchText", "subjects", "Lkotlinx/coroutines/flow/StateFlow;", "getSubjects", "()Lkotlinx/coroutines/flow/StateFlow;", "subjectsJob", "Lkotlinx/coroutines/Job;", "visibleSubjects", "getVisibleSubjects", "withCredit", "getWithCredit", "withExam", "getWithExam", "applySubjectFilter", "", "search", "name", "setCurrentSemester", "it", "setFilter", "update", "updateCurrentSemester", "dataSource", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "updateSubjects", "app_debug"})
public final class SubjectsViewModel extends androidx.lifecycle.ViewModel implements github.alexzhirkevich.studentbsuby.util.Updatable {
    private final github.alexzhirkevich.studentbsuby.repo.SubjectsRepository subjectsRepository = null;
    private final github.alexzhirkevich.studentbsuby.repo.Repository<java.lang.Integer> currentSemesterRepository = null;
    private final github.alexzhirkevich.studentbsuby.util.logger.Logger logger = null;
    private final androidx.compose.runtime.MutableState<java.lang.Integer> _currentSemester = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _isUpdating = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>>> _subjects = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>>> subjects = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>>> _visibleSubjects = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>>> visibleSubjects = null;
    private final androidx.compose.runtime.MutableState<java.lang.String> _searchText = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _withCredit = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _withExam = null;
    @kotlin.jvm.Volatile()
    private volatile boolean currentSemesterChangedByUser = false;
    private kotlinx.coroutines.Job subjectsJob;
    
    @javax.inject.Inject()
    public SubjectsViewModel(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.SubjectsRepository subjectsRepository, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.Repository<java.lang.Integer> currentSemesterRepository, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.logger.Logger logger) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.Integer> getCurrentSemester() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.compose.runtime.State<java.lang.Boolean> isUpdating() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>>> getSubjects() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>>> getVisibleSubjects() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.String> getSearchText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.Boolean> getWithCredit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.Boolean> getWithExam() {
        return null;
    }
    
    public final void setCurrentSemester(int it) {
    }
    
    public final void setFilter(boolean withCredit, boolean withExam) {
    }
    
    public final void search(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
    }
    
    @java.lang.Override()
    public void update() {
    }
    
    private final void applySubjectFilter() {
    }
    
    private final kotlinx.coroutines.Job updateCurrentSemester(github.alexzhirkevich.studentbsuby.repo.DataSource dataSource) {
        return null;
    }
    
    private final kotlinx.coroutines.Job updateSubjects(github.alexzhirkevich.studentbsuby.repo.DataSource dataSource) {
        return null;
    }
}