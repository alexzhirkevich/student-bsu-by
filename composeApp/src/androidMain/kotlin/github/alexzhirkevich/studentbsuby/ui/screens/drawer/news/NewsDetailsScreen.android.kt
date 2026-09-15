package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news

import android.content.Intent
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import github.alexzhirkevich.studentbsuby.repo.DataSource
import github.alexzhirkevich.studentbsuby.repo.NewsRepository
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBar
import github.alexzhirkevich.studentbsuby.ui.theme.values.Colors
import github.alexzhirkevich.studentbsuby.util.LoginCookieManager
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject

@Composable
actual fun NewsDetailsScreen(id: Int, viewModel: NewsViewModel) {
    val newsRepository = koinInject<NewsRepository>()
    val loginCookieManager = koinInject<LoginCookieManager>()

    val bgColor = Colors.GrayBackground
    var webViewRef by remember { mutableStateOf<WebView?>(null) }

    Box(
        Modifier
            .fillMaxSize()
            .background(bgColor)
            .verticalScroll(rememberScrollState())
    ) {
        BsuProgressBar(
            tint = MaterialTheme.colors.primary,
            size = 100.dp,
            modifier = Modifier.align(Alignment.Center)
        )

        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .windowInsetsPadding(WindowInsets.navigationBars),
            factory = { context ->
                WebView(context).apply {
                    setBackgroundColor(bgColor.toArgb())
                    CookieManager.getInstance().setCookie(
                        newsRepository.baseUrl,
                        loginCookieManager.getCookies()
                    )
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView,
                            request: WebResourceRequest
                        ): Boolean {
                            view.context.startActivity(
                                Intent(Intent.ACTION_VIEW, request.url)
                            )
                            return true
                        }
                    }
                    webViewRef = this
                }
            },
            update = { }
        )

        LaunchedEffect(id, webViewRef) {
            val webView = webViewRef ?: return@LaunchedEffect
            newsRepository.getNewsItem(id, DataSource.Remote).collectLatest {
                webView.loadDataWithBaseURL(
                    newsRepository.newsUrl,
                    it.content,
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        }
    }
}
