package github.alexzhirkevich.studentbsuby.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005\u00a8\u0006\b"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/util/Updatable;", "", "isUpdating", "Landroidx/compose/runtime/State;", "", "()Landroidx/compose/runtime/State;", "update", "", "app_release"})
public abstract interface Updatable {
    
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.compose.runtime.State<java.lang.Boolean> isUpdating();
    
    public abstract void update();
}