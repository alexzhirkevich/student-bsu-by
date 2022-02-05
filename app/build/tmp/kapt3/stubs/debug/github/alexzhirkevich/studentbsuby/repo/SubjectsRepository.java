package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJt\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2P\u0010\u000f\u001aL\u0012!\u0012\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\u0002\u00a2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u001f\u0012\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0002\u00a2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010H\u0016J\u001f\u0010\u0016\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J%\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/SubjectsRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/CacheWebRepository;", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/Subject;", "usernameProvider", "Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;", "profileApi", "Lgithub/alexzhirkevich/studentbsuby/api/ProfileApi;", "subjectsDao", "Lgithub/alexzhirkevich/studentbsuby/dao/SubjectsDao;", "(Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;Lgithub/alexzhirkevich/studentbsuby/api/ProfileApi;Lgithub/alexzhirkevich/studentbsuby/dao/SubjectsDao;)V", "get", "Lkotlinx/coroutines/flow/Flow;", "dataSource", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "replaceCacheIf", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "cached", "new", "", "getFromCache", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFromWeb", "saveToCache", "", "value", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@javax.inject.Singleton()
public final class SubjectsRepository extends github.alexzhirkevich.studentbsuby.repo.CacheWebRepository<java.util.List<? extends java.util.List<? extends github.alexzhirkevich.studentbsuby.data.models.Subject>>> {
    private final github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider = null;
    private final github.alexzhirkevich.studentbsuby.api.ProfileApi profileApi = null;
    private final github.alexzhirkevich.studentbsuby.dao.SubjectsDao subjectsDao = null;
    
    @javax.inject.Inject()
    public SubjectsRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.api.ProfileApi profileApi, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.SubjectsDao subjectsDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<java.util.List<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>> get(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.DataSource dataSource, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>, ? super java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>, java.lang.Boolean> replaceCacheIf) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromWeb(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object saveToCache(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>> value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromCache(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Subject>>> continuation) {
        return null;
    }
}