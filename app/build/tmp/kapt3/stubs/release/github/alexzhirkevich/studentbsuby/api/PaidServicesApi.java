package github.alexzhirkevich.studentbsuby.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/api/PaidServicesApi;", "", "academicDebt", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "common", "hostelBills", "info", "tuitionFee", "app_release"})
public abstract interface PaidServicesApi {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "PersonalCabinet/Pay/Info")
    public abstract java.lang.Object info(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Headers(value = {"User-Agent: Mozilla"})
    @retrofit2.http.GET(value = "PersonalCabinet/Pay/report")
    public abstract java.lang.Object tuitionFee(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Headers(value = {"User-Agent: Mozilla"})
    @retrofit2.http.GET(value = "PersonalCabinet/Pay/reportAkadem")
    public abstract java.lang.Object academicDebt(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Headers(value = {"User-Agent: Mozilla"})
    @retrofit2.http.GET(value = "PersonalCabinet/Pay/HousePay")
    public abstract java.lang.Object hostelBills(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Headers(value = {"User-Agent: Mozilla"})
    @retrofit2.http.GET(value = "PersonalCabinet/Pay/reportOther")
    public abstract java.lang.Object common(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
}