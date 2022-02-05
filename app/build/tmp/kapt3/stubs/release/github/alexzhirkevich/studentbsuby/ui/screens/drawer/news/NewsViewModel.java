package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ!\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\u001bH\u0016J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020$H\u0002R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00100\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00148VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00100\u00148F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006%"}, d2 = {"Lgithub/alexzhirkevich/studentbsuby/ui/screens/drawer/news/NewsViewModel;", "Landroidx/lifecycle/ViewModel;", "Lgithub/alexzhirkevich/studentbsuby/util/Updatable;", "newsRepo", "Lgithub/alexzhirkevich/studentbsuby/repo/NewsRepository;", "logger", "Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;", "httpClient", "Lokhttp3/OkHttpClient;", "loginCookieManager", "Lgithub/alexzhirkevich/studentbsuby/util/LoginCookieManager;", "(Lgithub/alexzhirkevich/studentbsuby/repo/NewsRepository;Lgithub/alexzhirkevich/studentbsuby/util/logger/Logger;Lokhttp3/OkHttpClient;Lgithub/alexzhirkevich/studentbsuby/util/LoginCookieManager;)V", "_isUpdating", "Landroidx/compose/runtime/MutableState;", "", "_news", "Lgithub/alexzhirkevich/studentbsuby/util/DataState;", "", "Lgithub/alexzhirkevich/studentbsuby/data/models/News;", "isUpdating", "Landroidx/compose/runtime/State;", "()Landroidx/compose/runtime/State;", "news", "getNews", "newsJob", "Lkotlinx/coroutines/Job;", "setupWebView", "", "newsId", "", "webView", "Landroid/webkit/WebView;", "(ILandroid/webkit/WebView;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "updateNews", "dataSource", "Lgithub/alexzhirkevich/studentbsuby/repo/DataSource;", "app_release"})
public final class NewsViewModel extends androidx.lifecycle.ViewModel implements github.alexzhirkevich.studentbsuby.util.Updatable {
    private final github.alexzhirkevich.studentbsuby.repo.NewsRepository newsRepo = null;
    private final github.alexzhirkevich.studentbsuby.util.logger.Logger logger = null;
    private final okhttp3.OkHttpClient httpClient = null;
    private final github.alexzhirkevich.studentbsuby.util.LoginCookieManager loginCookieManager = null;
    private final androidx.compose.runtime.MutableState<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.News>>> _news = null;
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> _isUpdating = null;
    private kotlinx.coroutines.Job newsJob;
    
    @javax.inject.Inject()
    public NewsViewModel(@org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.repo.NewsRepository newsRepo, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.logger.Logger logger, @org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient httpClient, @org.jetbrains.annotations.NotNull()
    github.alexzhirkevich.studentbsuby.util.LoginCookieManager loginCookieManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.runtime.State<github.alexzhirkevich.studentbsuby.util.DataState<java.util.List<github.alexzhirkevich.studentbsuby.data.models.News>>> getNews() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public androidx.compose.runtime.State<java.lang.Boolean> isUpdating() {
        return null;
    }
    
    @java.lang.Override()
    public void update() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setupWebView(int newsId, @org.jetbrains.annotations.NotNull()
    android.webkit.WebView webView, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    private final void updateNews(github.alexzhirkevich.studentbsuby.repo.DataSource dataSource) {
    }
}