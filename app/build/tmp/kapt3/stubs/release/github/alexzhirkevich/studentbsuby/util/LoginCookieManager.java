package github.alexzhirkevich.studentbsuby.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/util/LoginCookieManager;", "", "canRestoreSession", "", "cleanCookies", "", "getCookies", "", "app_release"})
public abstract interface LoginCookieManager {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getCookies();
    
    public abstract void cleanCookies();
    
    public abstract boolean canRestoreSession();
}