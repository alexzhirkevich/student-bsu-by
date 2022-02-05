package github.alexzhirkevich.studentbsuby.util.logger;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u000eJ0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&\u00a8\u0006\u000f"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;", "", "log", "", "msg", "", "tag", "logLevel", "Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger$LogLevel;", "cause", "", "share", "context", "Landroid/content/Context;", "LogLevel", "app_release"})
public abstract interface Logger {
    
    public abstract void log(@org.jetbrains.annotations.NotNull()
    java.lang.String msg, @org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.logger.Logger.LogLevel logLevel, @org.jetbrains.annotations.Nullable()
    java.lang.Throwable cause);
    
    public abstract void share(@org.jetbrains.annotations.NotNull()
    android.content.Context context);
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger$LogLevel;", "", "(Ljava/lang/String;I)V", "Warning", "Error", "app_release"})
    public static enum LogLevel {
        /*public static final*/ Warning /* = new Warning() */,
        /*public static final*/ Error /* = new Error() */;
        
        LogLevel() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 3)
    public final class DefaultImpls {
    }
}