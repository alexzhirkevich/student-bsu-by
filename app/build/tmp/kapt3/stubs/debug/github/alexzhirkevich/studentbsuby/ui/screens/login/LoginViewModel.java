package github.alexzhirkevich.studentbsuby.ui.screens.login;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@com.google.accompanist.pager.ExperimentalPagerApi()
@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\"\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000f00H\u0082@\u00f8\u0001\u0000\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00a2\u0006\u0004\b1\u00102J\u0006\u00103\u001a\u000204J\b\u00105\u001a\u000204H\u0014J\u000e\u00106\u001a\u0002042\u0006\u0010\u001b\u001a\u00020\u000fJ\u000e\u00107\u001a\u0002042\u0006\u00108\u001a\u00020\u0015J\u000e\u00109\u001a\u0002042\u0006\u00103\u001a\u00020\u0015J\u000e\u0010:\u001a\u0002042\u0006\u0010;\u001a\u00020\u0015J9\u0010<\u001a\u0002042\u0006\u0010=\u001a\u00020\u000f2\u001e\b\u0002\u0010>\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002040@\u0012\u0006\u0012\u0004\u0018\u00010A0?H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010BJ\u0006\u0010C\u001a\u000204J\u0011\u0010D\u001a\u000204H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00102R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00120\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001c8F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120 \u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00150 \u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u001d\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00120\u001c8F\u00a2\u0006\u0006\u001a\u0004\b&\u0010\u001eR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00150\u001c8F\u00a2\u0006\u0006\u001a\u0004\b(\u0010\u001eR\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00150\u001c8F\u00a2\u0006\u0006\u001a\u0004\b*\u0010\u001eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001c8F\u00a2\u0006\u0006\u001a\u0004\b,\u0010\u001eR\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00150\u001c8F\u00a2\u0006\u0006\u001a\u0004\b.\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006E"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/screens/login/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "loginRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/LoginRepository;", "synchronizationWorkerManager", "Lgithub/alexzhirkevich/studentbsuby/workers/SynchronizationWorkerManager;", "settingsRepository", "Lgithub/alexzhirkevich/studentbsuby/repo/SettingsRepository;", "resources", "Landroid/content/res/Resources;", "logger", "Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;", "(Lgithub/alexzhirkevich/studentbsuby/repo/LoginRepository;Lgithub/alexzhirkevich/studentbsuby/workers/SynchronizationWorkerManager;Lgithub/alexzhirkevich/studentbsuby/repo/SettingsRepository;Landroid/content/res/Resources;Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;)V", "_autoLogin", "Landroidx/compose/runtime/MutableState;", "", "_captchaBitmap", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lgithub/alexzhirkevich/studentbsuby/util/DataState;", "Landroidx/compose/ui/graphics/ImageBitmap;", "_captchaText", "", "_loggedIn", "_loginText", "_passwordText", "_shouldShowSplashScreen", "_splashText", "autoLogin", "Landroidx/compose/runtime/State;", "getAutoLogin", "()Landroidx/compose/runtime/State;", "captchaBitmap", "Lkotlinx/coroutines/flow/StateFlow;", "getCaptchaBitmap", "()Lkotlinx/coroutines/flow/StateFlow;", "captchaText", "getCaptchaText", "loggedIn", "getLoggedIn", "loginText", "getLoginText", "passwordText", "getPasswordText", "shouldShowSplashScreen", "getShouldShowSplashScreen", "splashText", "getSplashText", "init", "Lkotlin/Result;", "init-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "", "onCleared", "setAutoLogin", "setCaptchaText", "text", "setLoginText", "setPasswordText", "password", "updateAndRecognizeSameCaptcha", "show", "onCapcthaLoaded", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCaptcha", "updateCaptchaInternal", "app_debug"})
@androidx.compose.foundation.ExperimentalFoundationApi()
@androidx.compose.animation.ExperimentalAnimationApi()
@androidx.compose.ui.ExperimentalComposeUiApi()
@androidx.compose.material.ExperimentalMaterialApi()
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    private final github.alexzhirkevich.studentbsuby.repo.LoginRepository loginRepository = null;
    private final github.alexzhirkevich.studentbsuby.workers.SynchronizationWorkerManager synchronizationWorkerManager = null;
    private final github.alexzhirkevich.studentbsuby.repo.SettingsRepository settingsRepository = null;
    private final android.content.res.Resources resources = null;
    private final github.alexzhirkevich.studentbsuby.util.logger.Logger logger = null;
    private final androidx.compose.runtime.MutableState<java.lang.String> _splashText = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _shouldShowSplashScreen = null;
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.util.DataState<java.lang.Boolean>> _loggedIn = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<github.alexzhirkevich.studentbsuby.util.DataState<androidx.compose.ui.graphics.ImageBitmap>> _captchaBitmap = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<github.alexzhirkevich.studentbsuby.util.DataState<androidx.compose.ui.graphics.ImageBitmap>> captchaBitmap = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _captchaText = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> captchaText = null;
    private final androidx.compose.runtime.MutableState<java.lang.String> _loginText = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _autoLogin = null;
    private final androidx.compose.runtime.MutableState<java.lang.String> _passwordText = null;
    
    @javax.inject.Inject()
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.LoginRepository loginRepository, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.workers.SynchronizationWorkerManager synchronizationWorkerManager, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    android.content.res.Resources resources, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.logger.Logger logger) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.String> getSplashText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.Boolean> getShouldShowSplashScreen() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.lang.Boolean>> getLoggedIn() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<github.alexzhirkevich.studentbsuby.util.DataState<androidx.compose.ui.graphics.ImageBitmap>> getCaptchaBitmap() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getCaptchaText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.String> getLoginText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.Boolean> getAutoLogin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<java.lang.String> getPasswordText() {
        return null;
    }
    
    public final void updateCaptcha() {
    }
    
    public final void setAutoLogin(boolean autoLogin) {
    }
    
    public final void setLoginText(@org.jetbrains.annotations.NotNull()
    java.lang.String login) {
    }
    
    public final void setPasswordText(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void setCaptchaText(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void login() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    private final java.lang.Object updateCaptchaInternal(kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final java.lang.Object updateAndRecognizeSameCaptcha(boolean show, kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> onCapcthaLoaded, kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}