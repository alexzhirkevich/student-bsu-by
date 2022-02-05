package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJB\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\f0\u00020\u0002\"\u0004\b\u0000\u0010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00022\u0018\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u00020\u000e0\u00100\u0002H\u0002Jt\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142P\u0010\u0015\u001aL\u0012!\u0012\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\u0002\u00a2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u001f\u0012\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0002\u00a2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b0\u0016H\u0016J\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ%\u0010\u001f\u001a\u00020 2\u0012\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006#"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/TimetableRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/CacheWebRepository;", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/Lesson;", "usernameProvider", "Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;", "timetableApi", "Lgithub/alexzhirkevich/studentbsuby/api/TimetableApi;", "lessonsDao", "Lgithub/alexzhirkevich/studentbsuby/dao/LessonsDao;", "(Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;Lgithub/alexzhirkevich/studentbsuby/api/TimetableApi;Lgithub/alexzhirkevich/studentbsuby/dao/LessonsDao;)V", "chunk", "T", "spans", "", "values", "Lkotlin/Pair;", "get", "Lkotlinx/coroutines/flow/Flow;", "dataSource", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "replaceCacheIf", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "cached", "new", "", "getFromCache", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFromWeb", "saveToCache", "", "value", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class TimetableRepository extends github.alexzhirkevich.studentbsuby.repo.CacheWebRepository<java.util.List<? extends java.util.List<? extends github.alexzhirkevich.studentbsuby.data.models.Lesson>>> {
    private final github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider = null;
    private final github.alexzhirkevich.studentbsuby.api.TimetableApi timetableApi = null;
    private final github.alexzhirkevich.studentbsuby.dao.LessonsDao lessonsDao = null;
    
    @javax.inject.Inject()
    public TimetableRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.api.TimetableApi timetableApi, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.LessonsDao lessonsDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<java.util.List<java.util.List<github.alexzhirkevich.studentbsuby.data.models.Lesson>>> get(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.DataSource dataSource, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Lesson>>, ? super java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Lesson>>, java.lang.Boolean> replaceCacheIf) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromWeb(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Lesson>>> continuation) {
        return null;
    }
    
    private final <T extends java.lang.Object>java.util.List<java.util.List<T>> chunk(java.util.List<java.lang.Integer> spans, java.util.List<? extends kotlin.Pair<? extends T, java.lang.Integer>> values) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromCache(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Lesson>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object saveToCache(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Lesson>> value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}