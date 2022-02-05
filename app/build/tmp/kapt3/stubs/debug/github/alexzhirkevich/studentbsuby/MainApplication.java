package github.alexzhirkevich.studentbsuby;

import java.lang.System;

@com.google.accompanist.pager.ExperimentalPagerApi()
@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0006B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016\u00a8\u0006\u0007"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/MainApplication;", "Landroid/app/Application;", "Landroidx/work/Configuration$Provider;", "()V", "getWorkManagerConfiguration", "Landroidx/work/Configuration;", "WorkManagerInitializerEntryPoint", "app_debug"})
@androidx.compose.material.ExperimentalMaterialApi()
@androidx.compose.ui.ExperimentalComposeUiApi()
@androidx.compose.animation.ExperimentalAnimationApi()
@androidx.compose.foundation.ExperimentalFoundationApi()
@dagger.hilt.android.HiltAndroidApp()
public final class MainApplication extends android.app.Application implements androidx.work.Configuration.Provider {
    
    public MainApplication() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.work.Configuration getWorkManagerConfiguration() {
        return null;
    }
    
    @dagger.hilt.EntryPoint()
    @dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/MainApplication$WorkManagerInitializerEntryPoint;", "", "hiltWorkerFactory", "Landroidx/hilt/work/HiltWorkerFactory;", "app_debug"})
    public static abstract interface WorkManagerInitializerEntryPoint {
        
        @org.jetbrains.annotations.NotNull()
        public abstract androidx.hilt.work.HiltWorkerFactory hiltWorkerFactory();
    }
}