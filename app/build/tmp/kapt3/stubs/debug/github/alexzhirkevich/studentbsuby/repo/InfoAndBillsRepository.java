package github.alexzhirkevich.studentbsuby.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00020\u0001B%\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ%\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J%\u0010\u0011\u001a\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J+\u0010\u0012\u001a\u00020\u00132\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/repo/InfoAndBillsRepository;", "Lgithub/alexzhirkevich/studentbsuby/repo/CacheWebRepository;", "Lkotlin/Pair;", "Lgithub/alexzhirkevich/studentbsuby/data/models/PaidServicesInfo;", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/Bill;", "usernameProvider", "Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;", "api", "Lgithub/alexzhirkevich/studentbsuby/api/PaidServicesApi;", "dao", "Lgithub/alexzhirkevich/studentbsuby/dao/PaidServicesDao;", "dateFormat", "Ljava/text/DateFormat;", "(Lgithub/alexzhirkevich/studentbsuby/repo/UsernameProvider;Lgithub/alexzhirkevich/studentbsuby/api/PaidServicesApi;Lgithub/alexzhirkevich/studentbsuby/dao/PaidServicesDao;Ljava/text/DateFormat;)V", "getFromCache", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFromWeb", "saveToCache", "", "value", "(Lkotlin/Pair;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
final class InfoAndBillsRepository extends github.alexzhirkevich.studentbsuby.repo.CacheWebRepository<kotlin.Pair<? extends github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo, ? extends java.util.List<? extends github.alexzhirkevich.studentbsuby.data.models.Bill>>> {
    private final github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider = null;
    private final github.alexzhirkevich.studentbsuby.api.PaidServicesApi api = null;
    private final github.alexzhirkevich.studentbsuby.dao.PaidServicesDao dao = null;
    private final java.text.DateFormat dateFormat = null;
    
    public InfoAndBillsRepository(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.UsernameProvider usernameProvider, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.api.PaidServicesApi api, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.dao.PaidServicesDao dao, @org.jetbrains.annotations.NotNull()
    java.text.DateFormat dateFormat) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromCache(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Pair<github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo, ? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getFromWeb(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Pair<github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo, ? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object saveToCache(@org.jetbrains.annotations.NotNull()
    kotlin.Pair<github.alexzhirkevich.studentbsuby.data.models.PaidServicesInfo, ? extends java.util.List<github.alexzhirkevich.studentbsuby.data.models.Bill>> value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}