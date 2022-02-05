package github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\'2\u0006\u0010-\u001a\u00020.H\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R2\u0010\u000b\u001a&\u0012\"\u0012 \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\u000e0\u000e0\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u00020\u00138\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0016\u0010\u0018\u001a\u00020\u00138\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\u001a\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0016\u0010\u001c\u001a\u00020\u00138\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\n0\u001f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010 R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R9\u0010!\u001a*\u0012&\u0012$\u0012 \u0012\u001e\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\u000e0\u000ej\u0002`#0\r0\"\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0010\u0010&\u001a\u0004\u0018\u00010\'X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/screens/drawer/timetable/TimetableViewModel;", "Landroidx/lifecycle/ViewModel;", "Lgithub/alexzhirkevich/studentbsuby/util/Updatable;", "timetableRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/TimetableRepository;", "logger", "Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;", "(Lgithub/alexzhirkevich/studentbsuby/repo/TimetableRepository;Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;)V", "_isUpdating", "Landroidx/compose/runtime/MutableState;", "", "_timetable", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lgithub/alexzhirkevich/studentbsuby/util/DataState;", "", "Lkotlin/Pair;", "Lgithub/alexzhirkevich/studentbsuby/data/models/Lesson;", "Lgithub/alexzhirkevich/studentbsuby/ui/screens/drawer/timetable/LessonState;", "currentDay", "", "getCurrentDay", "()I", "currentDayOfWeek", "getCurrentDayOfWeek", "currentMonth", "getCurrentMonth", "currentYear", "getCurrentYear", "displayDayOfWeek", "getDisplayDayOfWeek", "isUpdating", "Landroidx/compose/runtime/State;", "()Landroidx/compose/runtime/State;", "timetable", "Lkotlinx/coroutines/flow/StateFlow;", "Lgithub/alexzhirkevich/studentbsuby/ui/screens/drawer/timetable/Timetable;", "getTimetable", "()Lkotlinx/coroutines/flow/StateFlow;", "timetableJob", "Lkotlinx/coroutines/Job;", "currentTime", "", "update", "", "updateTimeTable", "dataSource", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "app_debug"})
public final class TimetableViewModel extends androidx.lifecycle.ViewModel implements github.alexzhirkevich.studentbsuby.util.Updatable {
    private final github.alexzhirkevich.studentbsuby.repo.TimetableRepository timetableRepository = null;
    private final github.alexzhirkevich.studentbsuby.util.logger.Logger logger = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<java.util.List<kotlin.Pair<github.alexzhirkevich.studentbsuby.data.models.Lesson, github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable.LessonState>>>>> _timetable = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<java.util.List<kotlin.Pair<github.alexzhirkevich.studentbsuby.data.models.Lesson, github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable.LessonState>>>>> timetable = null;
    @androidx.annotation.IntRange(from = 1L, to = 31L)
    private final int currentDay = 0;
    @androidx.annotation.IntRange(from = 0L, to = 6L)
    private final int currentDayOfWeek = 0;
    @androidx.annotation.IntRange(from = 0L, to = 11L)
    private final int currentMonth = 0;
    private final int currentYear = 0;
    @androidx.annotation.IntRange(from = 0L, to = 5L)
    private final int displayDayOfWeek = 0;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _isUpdating = null;
    private kotlinx.coroutines.Job timetableJob;
    
    @javax.inject.Inject()
    public TimetableViewModel(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.TimetableRepository timetableRepository, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.logger.Logger logger) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<java.util.List<kotlin.Pair<github.alexzhirkevich.studentbsuby.data.models.Lesson, github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable.LessonState>>>>> getTimetable() {
        return null;
    }
    
    public final int getCurrentDay() {
        return 0;
    }
    
    public final int getCurrentDayOfWeek() {
        return 0;
    }
    
    public final int getCurrentMonth() {
        return 0;
    }
    
    public final int getCurrentYear() {
        return 0;
    }
    
    public final int getDisplayDayOfWeek() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.compose.runtime.State<java.lang.Boolean> isUpdating() {
        return null;
    }
    
    @java.lang.Override()
    public void update() {
    }
    
    private final kotlinx.coroutines.Job updateTimeTable(github.alexzhirkevich.studentbsuby.repo.DataSource dataSource) {
        return null;
    }
    
    private final java.lang.String currentTime() {
        return null;
    }
}