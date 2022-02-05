package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R+\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8V@TX\u0096\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProviderImpl;", "Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;", "encryptedPreferences", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "getEncryptedPreferences", "()Landroid/content/SharedPreferences;", "<set-?>", "", "username", "getUsername", "()Ljava/lang/String;", "setUsername", "(Ljava/lang/String;)V", "username$delegate", "Lgithub/alexzhirkevich/studentbsuby/util/PreferencePersistence;", "app_debug"})
public class UsernameProviderImpl implements github.alexzhirkevich.studentbsuby.repo.UsernameProvider {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences encryptedPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final github.alexzhirkevich.studentbsuby.util.PreferencePersistence username$delegate = null;
    
    @javax.inject.Inject()
    public UsernameProviderImpl(@org.jetbrains.annotations.NotNull()
    @github.alexzhirkevich.studentbsuby.di.Encrypted()
    android.content.SharedPreferences encryptedPreferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences getEncryptedPreferences() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getUsername() {
        return null;
    }
    
    protected void setUsername(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
}