package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u00010B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u0006\u0010!\u001a\u00020\rJ\u0019\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020 H\u0086P\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\u0011\u0010%\u001a\u00020&H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\'J)\u0010(\u001a\u00020&2\u0006\u0010(\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010*J\u0006\u0010+\u001a\u00020,J\u001b\u0010-\u001a\u0004\u0018\u00010 2\u0006\u0010.\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082D\u00a2\u0006\u0002\n\u0000R+\u0010\u0019\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\u00188F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u0014\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00061"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/LoginRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProviderImpl;", "api", "Lgithub/alexzhirkevich/studentbsuby/api/LoginApi;", "encryptedPreferences", "Landroid/content/SharedPreferences;", "preferences", "captchaRecognizer", "Lgithub/alexzhirkevich/studentbsuby/util/CaptchaRecognizer;", "loginCookieManager", "Lgithub/alexzhirkevich/studentbsuby/util/LoginCookieManager;", "(Lgithub/alexzhirkevich/studentbsuby/api/LoginApi;Landroid/content/SharedPreferences;Landroid/content/SharedPreferences;Lgithub/alexzhirkevich/studentbsuby/util/CaptchaRecognizer;Lgithub/alexzhirkevich/studentbsuby/util/LoginCookieManager;)V", "<set-?>", "", "autoLogin", "getAutoLogin", "()Z", "setAutoLogin", "(Z)V", "autoLogin$delegate", "Lgithub/alexzhirkevich/studentbsuby/util/PreferencePersistence;", "currentUpdateCount", "", "overflowCount", "", "password", "getPassword", "()Ljava/lang/String;", "setPassword", "(Ljava/lang/String;)V", "password$delegate", "updatedBitmap", "Landroid/graphics/Bitmap;", "canRestoreSession", "getCaptchaText", "bitmap", "(Landroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initialize", "Lgithub/alexzhirkevich/studentbsuby/repo/LoginRepository$LoginResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "captcha", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "", "updateCaptcha", "keepText", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "LoginResponse", "app_release"})
@javax.inject.Singleton()
public final class LoginRepository extends github.alexzhirkevich.studentbsuby.repo.UsernameProviderImpl {
    private final github.alexzhirkevich.studentbsuby.api.LoginApi api = null;
    private final android.content.SharedPreferences preferences = null;
    private final github.alexzhirkevich.studentbsuby.util.CaptchaRecognizer captchaRecognizer = null;
    private final github.alexzhirkevich.studentbsuby.util.LoginCookieManager loginCookieManager = null;
    @org.jetbrains.annotations.NotNull()
    private final github.alexzhirkevich.studentbsuby.util.PreferencePersistence password$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final github.alexzhirkevich.studentbsuby.util.PreferencePersistence autoLogin$delegate = null;
    private final int overflowCount = 5;
    private int currentUpdateCount = 0;
    private android.graphics.Bitmap updatedBitmap;
    
    @javax.inject.Inject()
    public LoginRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.api.LoginApi api, @org.jetbrains.annotations.NotNull()
    @github.alexzhirkevich.studentbsuby.di.Encrypted()
    android.content.SharedPreferences encryptedPreferences, @org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences preferences, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.CaptchaRecognizer captchaRecognizer, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.LoginCookieManager loginCookieManager) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPassword() {
        return null;
    }
    
    private final void setPassword(java.lang.String p0) {
    }
    
    public final boolean getAutoLogin() {
        return false;
    }
    
    public final void setAutoLogin(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object initialize(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super github.alexzhirkevich.studentbsuby.repo.LoginRepository.LoginResponse> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object login(@org.jetbrains.annotations.NotNull()
    java.lang.String login, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String captcha, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super github.alexzhirkevich.studentbsuby.repo.LoginRepository.LoginResponse> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateCaptcha(boolean keepText, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super android.graphics.Bitmap> continuation) {
        return null;
    }
    
    public final boolean canRestoreSession() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCaptchaText(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
        return null;
    }
    
    public final void logout() {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\t\u0010\u0015\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/LoginRepository$LoginResponse;", "", "success", "", "loggedIn", "loginResult", "", "(ZZLjava/lang/String;)V", "getLoggedIn", "()Z", "getLoginResult", "()Ljava/lang/String;", "getSuccess", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_release"})
    public static final class LoginResponse {
        private final boolean success = false;
        private final boolean loggedIn = false;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.String loginResult = null;
        
        @org.jetbrains.annotations.NotNull()
        public final github.alexzhirkevich.studentbsuby.repo.LoginRepository.LoginResponse copy(boolean success, boolean loggedIn, @org.jetbrains.annotations.Nullable()
        java.lang.String loginResult) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public java.lang.String toString() {
            return null;
        }
        
        public LoginResponse(boolean success, boolean loggedIn, @org.jetbrains.annotations.Nullable()
        java.lang.String loginResult) {
            super();
        }
        
        public final boolean component1() {
            return false;
        }
        
        public final boolean getSuccess() {
            return false;
        }
        
        public final boolean component2() {
            return false;
        }
        
        public final boolean getLoggedIn() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getLoginResult() {
            return null;
        }
    }
}