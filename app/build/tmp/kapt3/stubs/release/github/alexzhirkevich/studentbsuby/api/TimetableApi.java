package github.alexzhirkevich.studentbsuby.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J1\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0018\b\u0001\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/api/TimetableApi;", "", "timetable", "Lretrofit2/Response;", "Lokhttp3/ResponseBody;", "dayOfWeek", "", "", "Lgithub/alexzhirkevich/studentbsuby/api/FormUrlEncodedBody;", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface TimetableApi {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "PersonalCabinet/Schedule")
    @retrofit2.http.Headers(value = {"User-Agent: Mozilla"})
    @retrofit2.http.FormUrlEncoded()
    public abstract java.lang.Object timetable(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.FieldMap(encoded = true)
    java.util.Map<java.lang.String, java.lang.String> dayOfWeek, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<okhttp3.ResponseBody>> continuation);
}