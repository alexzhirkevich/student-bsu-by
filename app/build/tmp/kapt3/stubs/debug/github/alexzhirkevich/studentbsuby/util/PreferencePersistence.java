package github.alexzhirkevich.studentbsuby.util;

import java.lang.System;

@kotlin.Suppress(names = {"unchecked_cast"})
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B+\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ$\u0010\u0011\u001a\u00028\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u00022\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0086\u0002\u00a2\u0006\u0002\u0010\u0015J,\u0010\u0016\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00022\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010\u0017\u001a\u00028\u0000H\u00a6\u0002\u00a2\u0006\u0002\u0010\u0018R\u0013\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0001\u0006\u0019\u001a\u001b\u001c\u001d\u001e\u00a8\u0006\u001f"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/util/PreferencePersistence;", "T", "", "preferences", "Landroid/content/SharedPreferences;", "default", "onChanged", "Lkotlin/Function1;", "", "(Landroid/content/SharedPreferences;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "getDefault", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getOnChanged", "()Lkotlin/jvm/functions/Function1;", "getPreferences", "()Landroid/content/SharedPreferences;", "getValue", "self", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "t", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "Lgithub/alexzhirkevich/studentbsuby/util/BooleanPreferencePersistence;", "Lgithub/alexzhirkevich/studentbsuby/util/StringPreferencePersistence;", "Lgithub/alexzhirkevich/studentbsuby/util/IntPreferencePersistence;", "Lgithub/alexzhirkevich/studentbsuby/util/LongPreferencePersistence;", "Lgithub/alexzhirkevich/studentbsuby/util/FloatPreferencePersistence;", "Lgithub/alexzhirkevich/studentbsuby/util/StringSetPreferencePersistence;", "app_debug"})
public abstract class PreferencePersistence<T extends java.lang.Object> {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<T, kotlin.Unit> onChanged = null;
    
    private PreferencePersistence(android.content.SharedPreferences preferences, T p1_772401952, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> onChanged) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences getPreferences() {
        return null;
    }
    
    public final T getDefault() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<T, kotlin.Unit> getOnChanged() {
        return null;
    }
    
    public final T getValue(@org.jetbrains.annotations.Nullable()
    java.lang.Object self, @org.jetbrains.annotations.NotNull()
    kotlin.reflect.KProperty<?> property) {
        return null;
    }
    
    public abstract void setValue(@org.jetbrains.annotations.Nullable()
    java.lang.Object self, @org.jetbrains.annotations.NotNull()
    kotlin.reflect.KProperty<?> property, T t);
}