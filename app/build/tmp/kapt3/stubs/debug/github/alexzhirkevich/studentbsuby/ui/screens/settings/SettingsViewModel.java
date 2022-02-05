package github.alexzhirkevich.studentbsuby.ui.screens.settings;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@com.google.accompanist.pager.ExperimentalPagerApi()
@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\tJ\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\tJ\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\tJ\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r8F\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\r8F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\r8F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/screens/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/SettingsRepository;", "logger", "Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;", "(Lgithub/alexzhirkevich/studentbsuby/repo/SettingsRepository;Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;)V", "_collectCrashlytics", "Landroidx/compose/runtime/MutableState;", "", "_collectStatistics", "_notificationsEnabled", "collectCrashlytics", "Landroidx/compose/runtime/State;", "getCollectCrashlytics", "()Landroidx/compose/runtime/State;", "collectStatistics", "getCollectStatistics", "notificationsEnabled", "getNotificationsEnabled", "setCollectCrashlytics", "", "enabled", "setCollectStatistics", "setNotificationsEnabled", "shareLogs", "context", "Landroid/content/Context;", "app_debug"})
@androidx.compose.foundation.ExperimentalFoundationApi()
@androidx.compose.animation.ExperimentalAnimationApi()
@androidx.compose.ui.ExperimentalComposeUiApi()
@androidx.compose.material.ExperimentalMaterialApi()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    private final github.alexzhirkevich.studentbsuby.repo.SettingsRepository settingsRepository = null;
    private final github.alexzhirkevich.studentbsuby.util.logger.Logger logger = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _notificationsEnabled = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _collectStatistics = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _collectCrashlytics = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.logger.Logger logger) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.Boolean> getNotificationsEnabled() {
        return null;
    }
    
    public final void setNotificationsEnabled(boolean enabled) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.Boolean> getCollectStatistics() {
        return null;
    }
    
    public final void setCollectStatistics(boolean enabled) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.Boolean> getCollectCrashlytics() {
        return null;
    }
    
    public final void setCollectCrashlytics(boolean enabled) {
    }
    
    public final void shareLogs(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}