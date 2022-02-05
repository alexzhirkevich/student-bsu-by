package github.alexzhirkevich.studentbsuby.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H&\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0007"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/util/WorkerManager;", "Ljava/lang/Runnable;", "isEnabled", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stop", "", "app_release"})
public abstract interface WorkerManager extends java.lang.Runnable {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isEnabled(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation);
    
    public abstract void stop();
}