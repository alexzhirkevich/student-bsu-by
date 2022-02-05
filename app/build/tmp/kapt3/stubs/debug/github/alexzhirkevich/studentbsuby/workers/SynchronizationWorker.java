package github.alexzhirkevich.studentbsuby.workers;

import java.lang.System;

@androidx.hilt.work.HiltWorker()
@com.google.accompanist.pager.ExperimentalPagerApi()
@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B3\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0011\u0010\u000f\u001a\u00020\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0013H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J0\u0010\u0019\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u001a2\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u001aH\u0002J\u0011\u0010\u001c\u001a\u00020\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J?\u0010\u001c\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u001f2\u0018\u0010 \u001a\u0014\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u00020\u00150!H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006$"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/workers/SynchronizationWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "parameters", "Landroidx/work/WorkerParameters;", "hostelRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelRepository;", "timetableRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/TimetableRepository;", "loginRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/LoginRepository;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lgithub/alexzhirkevich/studentbsuby/repo/HostelRepository;Lgithub/alexzhirkevich/studentbsuby/repo/TimetableRepository;Lgithub/alexzhirkevich/studentbsuby/repo/LoginRepository;)V", "notificationCreator", "Lgithub/alexzhirkevich/studentbsuby/util/NotificationCreator;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "", "notifyHostelChanged", "", "old", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelState;", "new", "notifyTimetableChanged", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/Lesson;", "update", "T", "repo", "Lgithub/alexzhirkevich/studentbsuby/repo/CacheWebRepository;", "notify", "Lkotlin/Function2;", "(Lgithub/alexzhirkevich/studentbsuby/repo/CacheWebRepository;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
@androidx.compose.foundation.ExperimentalFoundationApi()
@androidx.compose.animation.ExperimentalAnimationApi()
@androidx.compose.ui.ExperimentalComposeUiApi()
@androidx.compose.material.ExperimentalMaterialApi()
public final class SynchronizationWorker extends androidx.work.CoroutineWorker {
    private final github.alexzhirkevich.studentbsuby.repo.HostelRepository hostelRepository = null;
    private final github.alexzhirkevich.studentbsuby.repo.TimetableRepository timetableRepository = null;
    private final github.alexzhirkevich.studentbsuby.repo.LoginRepository loginRepository = null;
    @org.jetbrains.annotations.NotNull()
    public static final github.alexzhirkevich.studentbsuby.workers.SynchronizationWorker.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "SynchronizationWorker";
    private final github.alexzhirkevich.studentbsuby.util.NotificationCreator notificationCreator = null;
    
    @dagger.assisted.AssistedInject()
    public SynchronizationWorker(@org.jetbrains.annotations.NotNull()
    @dagger.assisted.Assisted()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    @dagger.assisted.Assisted()
    androidx.work.WorkerParameters parameters, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.HostelRepository hostelRepository, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.TimetableRepository timetableRepository, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.LoginRepository loginRepository) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> continuation) {
        return null;
    }
    
    private final java.lang.Object login(kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final java.lang.Object update(kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> continuation) {
        return null;
    }
    
    private final <T extends java.lang.Object>java.lang.Object update(github.alexzhirkevich.studentbsuby.repo.CacheWebRepository<T> repo, kotlin.jvm.functions.Function2<? super T, ? super T, kotlin.Unit> notify, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void notifyHostelChanged(github.alexzhirkevich.studentbsuby.repo.HostelState old, github.alexzhirkevich.studentbsuby.repo.HostelState p1_54480) {
    }
    
    private final void notifyTimetableChanged(java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Lesson>> old, java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Lesson>> p1_54480) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/workers/SynchronizationWorker$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}