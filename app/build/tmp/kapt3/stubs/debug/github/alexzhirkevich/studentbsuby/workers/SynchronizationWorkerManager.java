package github.alexzhirkevich.studentbsuby.workers;

import java.lang.System;

@com.google.accompanist.pager.ExperimentalPagerApi()
@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0006H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/workers/SynchronizationWorkerManager;", "Lgithub/alexzhirkevich/studentbsuby/util/WorkerManager;", "workManager", "Landroidx/work/WorkManager;", "(Landroidx/work/WorkManager;)V", "isEnabled", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "run", "", "stop", "app_debug"})
@javax.inject.Singleton()
@androidx.compose.foundation.ExperimentalFoundationApi()
@androidx.compose.animation.ExperimentalAnimationApi()
@androidx.compose.ui.ExperimentalComposeUiApi()
@androidx.compose.material.ExperimentalMaterialApi()
public final class SynchronizationWorkerManager implements github.alexzhirkevich.studentbsuby.util.WorkerManager {
    private final androidx.work.WorkManager workManager = null;
    
    @javax.inject.Inject()
    public SynchronizationWorkerManager(@org.jetbrains.annotations.NotNull()
    androidx.work.WorkManager workManager) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object isEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @java.lang.Override()
    public void run() {
    }
    
    @java.lang.Override()
    public void stop() {
    }
}