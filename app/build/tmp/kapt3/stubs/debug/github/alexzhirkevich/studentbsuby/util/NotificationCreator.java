package github.alexzhirkevich.studentbsuby.util;

import java.lang.System;

@com.google.accompanist.pager.ExperimentalPagerApi()
@kotlinx.coroutines.ExperimentalCoroutinesApi()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\bJ&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001c"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/util/NotificationCreator;", "", "context", "Landroid/content/Context;", "channelId", "", "channelName", "channelDescription", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "intent", "Landroid/app/PendingIntent;", "kotlin.jvm.PlatformType", "getIntent", "()Landroid/app/PendingIntent;", "intent$delegate", "Lkotlin/Lazy;", "manager", "Landroid/app/NotificationManager;", "getManager", "()Landroid/app/NotificationManager;", "manager$delegate", "sendNotification", "", "id", "", "sub", "title", "text", "app_debug"})
@androidx.compose.foundation.ExperimentalFoundationApi()
@androidx.compose.animation.ExperimentalAnimationApi()
@androidx.compose.ui.ExperimentalComposeUiApi()
@androidx.compose.material.ExperimentalMaterialApi()
public final class NotificationCreator {
    private final android.content.Context context = null;
    private final java.lang.String channelId = null;
    private final java.lang.String channelName = null;
    private final java.lang.String channelDescription = null;
    private final kotlin.Lazy manager$delegate = null;
    private final kotlin.Lazy intent$delegate = null;
    
    public NotificationCreator(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String channelId, @org.jetbrains.annotations.NotNull()
    java.lang.String channelName, @org.jetbrains.annotations.NotNull()
    java.lang.String channelDescription) {
        super();
    }
    
    private final android.app.NotificationManager getManager() {
        return null;
    }
    
    private final android.app.PendingIntent getIntent() {
        return null;
    }
    
    public final void sendNotification(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String sub, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
}