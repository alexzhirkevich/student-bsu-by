package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0003JP\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u000728\u0010\b\u001a4\u0012\u0015\u0012\u0013\u0018\u00018\u0000\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\tH\u0016J\u0013\u0010\u000f\u001a\u0004\u0018\u00018\u0000H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u0004\u0018\u00018\u0000H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u0082\u0001\r\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006#"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/CacheWebRepository;", "T", "Lgithub/alexzhirkevich/studentbsuby/repo/Repository;", "()V", "get", "Lkotlinx/coroutines/flow/Flow;", "dataSource", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "replaceCacheIf", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "cached", "new", "", "getFromCache", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFromWeb", "saveToCache", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/NewsRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/NewsContentRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/CommonReceiptsRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/HostelBillsRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/AcademDebtRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/InfoAndBillsRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/TuitionFeeReceiptsRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/PhotoRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/SubjectsRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/CurrentSemesterRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/TimetableRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/UserRepository;", "app_release"})
public abstract class CacheWebRepository<T extends java.lang.Object> implements github.alexzhirkevich.studentbsuby.repo.Repository<T> {
    
    private CacheWebRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlinx.coroutines.flow.Flow<T> get(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.DataSource dataSource, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super T, ? super T, java.lang.Boolean> replaceCacheIf) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFromCache(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super T> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFromWeb(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super T> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveToCache(T value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}