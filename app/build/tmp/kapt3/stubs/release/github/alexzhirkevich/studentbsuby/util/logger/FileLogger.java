package github.alexzhirkevich.studentbsuby.util.logger;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J*\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/util/logger/FileLogger;", "Lgithub/alexzhirkevich/studentbsuby/util/logger/DefaultLogger;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "logDir", "Ljava/io/File;", "logFile", "timestampDateFormat", "Ljava/text/DateFormat;", "log", "", "msg", "", "tag", "logLevel", "Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger$LogLevel;", "cause", "", "share", "tryInit", "app_release"})
public final class FileLogger extends github.alexzhirkevich.studentbsuby.util.logger.DefaultLogger {
    private final java.io.File logDir = null;
    private final java.io.File logFile = null;
    private final java.text.DateFormat timestampDateFormat = null;
    
    public FileLogger(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    public void log(@org.jetbrains.annotations.NotNull()
    java.lang.String msg, @org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.logger.Logger.LogLevel logLevel, @org.jetbrains.annotations.Nullable()
    java.lang.Throwable cause) {
    }
    
    private final void tryInit() {
    }
    
    @java.lang.Override()
    public void share(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}