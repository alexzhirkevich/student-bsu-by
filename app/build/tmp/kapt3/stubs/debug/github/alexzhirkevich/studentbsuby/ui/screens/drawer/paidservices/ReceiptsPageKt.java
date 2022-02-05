package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 2, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001ao\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\t2\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\f0\u000b2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00110\u000e2\u0006\u0010\u0012\u001a\u00020\u000f2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00010\u000e\u00a2\u0006\u0002\b\u0014H\u0007\u00a8\u0006\u0015"}, d2 = {"ReceiptWidget", "", "receipt", "Lgithub/alexzhirkevich/studentbsuby/data/models/Receipt;", "dateFormat", "Ljava/text/DateFormat;", "modifier", "Landroidx/compose/ui/Modifier;", "ReceiptsPage", "T", "receipts", "Lgithub/alexzhirkevich/studentbsuby/util/DataState;", "", "header", "Lkotlin/Function1;", "", "complete", "", "emptyErrorMsg", "widget", "Landroidx/compose/runtime/Composable;", "app_debug"})
public final class ReceiptsPageKt {
    
    @androidx.compose.runtime.Composable()
    @androidx.compose.foundation.ExperimentalFoundationApi()
    public static final <T extends java.lang.Object>void ReceiptsPage(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.DataState<? extends java.util.List<? extends T>> receipts, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super T, java.lang.String> header, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, java.lang.Boolean> complete, @org.jetbrains.annotations.NotNull()
    java.lang.String emptyErrorMsg, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> widget) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ReceiptWidget(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.data.models.Receipt receipt, @org.jetbrains.annotations.NotNull()
    java.text.DateFormat dateFormat, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}