package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0011\u0010\u0011\u001a\u00020\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0007J\u0010\u0010\u0015\u001a\u00020\u000e2\b\b\u0001\u0010\u0016\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eJ\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/HostelRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/CacheWebRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelState;", "usernameProvider", "Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;", "profileApi", "Lgithub/alexzhirkevich/studentbsuby/api/ProfileApi;", "hostelDao", "Lgithub/alexzhirkevich/studentbsuby/dao/HostelDao;", "preferences", "Landroid/content/SharedPreferences;", "(Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;Lgithub/alexzhirkevich/studentbsuby/api/ProfileApi;Lgithub/alexzhirkevich/studentbsuby/dao/HostelDao;Landroid/content/SharedPreferences;)V", "images", "", "", "getFromCache", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFromWeb", "getHostelNumber", "", "address", "getImageForHostel", "number", "getMapAddress", "saveToCache", "", "value", "(Lgithub/alexzhirkevich/studentbsuby/repo/HostelState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class HostelRepository extends github.alexzhirkevich.studentbsuby.repo.CacheWebRepository<github.alexzhirkevich.studentbsuby.repo.HostelState> {
    private final github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider = null;
    private final github.alexzhirkevich.studentbsuby.api.ProfileApi profileApi = null;
    private final github.alexzhirkevich.studentbsuby.dao.HostelDao hostelDao = null;
    private final android.content.SharedPreferences preferences = null;
    private final java.util.List<java.lang.String> images = null;
    
    @javax.inject.Inject()
    public HostelRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.api.ProfileApi profileApi, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.HostelDao hostelDao, @org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences preferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMapAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
        return null;
    }
    
    @androidx.annotation.IntRange(from = 0L, to = 11L)
    public final int getHostelNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String address) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getImageForHostel(@androidx.annotation.IntRange(from = 0L, to = 11L)
    int number) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromWeb(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super github.alexzhirkevich.studentbsuby.repo.HostelState> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object saveToCache(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.HostelState value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromCache(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super github.alexzhirkevich.studentbsuby.repo.HostelState> continuation) {
        return null;
    }
}