package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@com.google.accompanist.pager.ExperimentalPagerApi()
@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bR+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR+\u0010\u0012\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0013\u0010\r\"\u0004\b\u0014\u0010\u000fR+\u0010\u0016\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/SettingsRepository;", "", "preferences", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "synchronizationWorkerManager", "Lgithub/alexzhirkevich/studentbsuby/workers/SynchronizationWorkerManager;", "(Landroid/content/SharedPreferences;Landroid/content/Context;Lgithub/alexzhirkevich/studentbsuby/workers/SynchronizationWorkerManager;)V", "<set-?>", "", "collectCrashlytics", "getCollectCrashlytics", "()Z", "setCollectCrashlytics", "(Z)V", "collectCrashlytics$delegate", "Lgithub/alexzhirkevich/studentbsuby/util/PreferencePersistence;", "collectStatistics", "getCollectStatistics", "setCollectStatistics", "collectStatistics$delegate", "synchronizationEnabled", "getSynchronizationEnabled", "setSynchronizationEnabled", "synchronizationEnabled$delegate", "app_debug"})
@androidx.compose.foundation.ExperimentalFoundationApi()
@androidx.compose.animation.ExperimentalAnimationApi()
@androidx.compose.ui.ExperimentalComposeUiApi()
@androidx.compose.material.ExperimentalMaterialApi()
public final class SettingsRepository {
    private final github.alexzhirkevich.studentbsuby.workers.SynchronizationWorkerManager synchronizationWorkerManager = null;
    @org.jetbrains.annotations.NotNull()
    private final github.alexzhirkevich.studentbsuby.util.PreferencePersistence synchronizationEnabled$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final github.alexzhirkevich.studentbsuby.util.PreferencePersistence collectStatistics$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final github.alexzhirkevich.studentbsuby.util.PreferencePersistence collectCrashlytics$delegate = null;
    
    @javax.inject.Inject()
    public SettingsRepository(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences preferences, @org.jetbrains.annotations.NotNull()
    @dagger.hilt.android.qualifiers.ApplicationContext()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.workers.SynchronizationWorkerManager synchronizationWorkerManager) {
        super();
    }
    
    public final boolean getSynchronizationEnabled() {
        return false;
    }
    
    public final void setSynchronizationEnabled(boolean p0) {
    }
    
    public final boolean getCollectStatistics() {
        return false;
    }
    
    public final void setCollectStatistics(boolean p0) {
    }
    
    public final boolean getCollectCrashlytics() {
        return false;
    }
    
    public final void setCollectCrashlytics(boolean p0) {
    }
}