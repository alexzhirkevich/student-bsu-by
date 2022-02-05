package github.alexzhirkevich.studentbsuby.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010)\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0015H\u0096\u0002R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/util/PreferencesCookieCache;", "Lcom/franmontiel/persistentcookiejar/cache/CookieCache;", "Lgithub/alexzhirkevich/studentbsuby/util/LoginCookieManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "addAll", "", "cookies", "", "Lokhttp3/Cookie;", "canRestoreSession", "", "cleanCookies", "clear", "getCookies", "", "iterator", "", "app_release"})
public final class PreferencesCookieCache implements com.franmontiel.persistentcookiejar.cache.CookieCache, github.alexzhirkevich.studentbsuby.util.LoginCookieManager {
    private final android.content.SharedPreferences prefs = null;
    
    public PreferencesCookieCache(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.util.Iterator<okhttp3.Cookie> iterator() {
        return null;
    }
    
    @java.lang.Override()
    public void addAll(@org.jetbrains.annotations.NotNull()
    java.util.Collection<okhttp3.Cookie> cookies) {
    }
    
    @java.lang.Override()
    public void clear() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getCookies() {
        return null;
    }
    
    @java.lang.Override()
    public void cleanCookies() {
    }
    
    @java.lang.Override()
    public boolean canRestoreSession() {
        return false;
    }
}