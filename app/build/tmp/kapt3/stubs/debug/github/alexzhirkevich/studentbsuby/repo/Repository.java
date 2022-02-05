package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002JT\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062:\b\u0002\u0010\u0007\u001a4\u0012\u0015\u0012\u0013\u0018\u00018\u0000\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00118\u0000\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\bH&\u0082\u0001\u0001\u000e\u00a8\u0006\u000f"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/Repository;", "T", "", "get", "Lkotlinx/coroutines/flow/Flow;", "dataSource", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "replaceCacheIf", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "cached", "new", "", "Lgithub/alexzhirkevich/studentbsuby/repo/CacheWebRepository;", "app_debug"})
public abstract interface Repository<T extends java.lang.Object> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<T> get(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.DataSource dataSource, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super T, ? super T, java.lang.Boolean> replaceCacheIf);
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 3)
    public final class DefaultImpls {
    }
}