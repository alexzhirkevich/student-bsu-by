package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\'\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ\u001f\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/NewsRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/CacheWebRepository;", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/News;", "api", "Lgithub/alexzhirkevich/studentbsuby/api/ProfileApi;", "dao", "Lgithub/alexzhirkevich/studentbsuby/dao/NewsDao;", "baseUrl", "Landroid/net/Uri;", "(Lgithub/alexzhirkevich/studentbsuby/api/ProfileApi;Lgithub/alexzhirkevich/studentbsuby/dao/NewsDao;Landroid/net/Uri;)V", "getBaseUrl", "()Landroid/net/Uri;", "newsUrl", "", "getNewsUrl", "()Ljava/lang/String;", "getFromCache", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFromWeb", "getNewsItem", "Lkotlinx/coroutines/flow/Flow;", "Lgithub/alexzhirkevich/studentbsuby/data/models/NewsContent;", "id", "", "dataSource", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "(ILgithub/alexzhirkevich/studentbsuby/repo/DataSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveToCache", "", "value", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class NewsRepository extends github.alexzhirkevich.studentbsuby.repo.CacheWebRepository<java.util.List<? extends github.alexzhirkevich.studentbsuby.data.models.News>> {
    private final github.alexzhirkevich.studentbsuby.api.ProfileApi api = null;
    private final github.alexzhirkevich.studentbsuby.dao.NewsDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.net.Uri baseUrl = null;
    
    @javax.inject.Inject()
    public NewsRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.api.ProfileApi api, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.NewsDao dao, @org.jetbrains.annotations.NotNull()
    android.net.Uri baseUrl) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.net.Uri getBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNewsUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromCache(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<github.alexzhirkevich.studentbsuby.data.models.News>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromWeb(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<github.alexzhirkevich.studentbsuby.data.models.News>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object saveToCache(@org.jetbrains.annotations.NotNull()
    java.util.List<github.alexzhirkevich.studentbsuby.data.models.News> value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getNewsItem(int id, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.DataSource dataSource, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<github.alexzhirkevich.studentbsuby.data.models.NewsContent>> continuation) {
        return null;
    }
}