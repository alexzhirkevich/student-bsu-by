package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u0013\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/UserRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/CacheWebRepository;", "Lgithub/alexzhirkevich/studentbsuby/data/models/User;", "dao", "Lgithub/alexzhirkevich/studentbsuby/dao/UsersDao;", "api", "Lgithub/alexzhirkevich/studentbsuby/api/ProfileApi;", "usernameProvider", "Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;", "(Lgithub/alexzhirkevich/studentbsuby/dao/UsersDao;Lgithub/alexzhirkevich/studentbsuby/api/ProfileApi;Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;)V", "getFromCache", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFromWeb", "saveToCache", "", "value", "(Lgithub/alexzhirkevich/studentbsuby/data/models/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UserRepository extends github.alexzhirkevich.studentbsuby.repo.CacheWebRepository<github.alexzhirkevich.studentbsuby.data.models.User> {
    private final github.alexzhirkevich.studentbsuby.dao.UsersDao dao = null;
    private final github.alexzhirkevich.studentbsuby.api.ProfileApi api = null;
    private final github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider = null;
    
    @javax.inject.Inject()
    public UserRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.UsersDao dao, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.api.ProfileApi api, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromCache(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super github.alexzhirkevich.studentbsuby.data.models.User> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromWeb(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super github.alexzhirkevich.studentbsuby.data.models.User> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object saveToCache(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.data.models.User value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}