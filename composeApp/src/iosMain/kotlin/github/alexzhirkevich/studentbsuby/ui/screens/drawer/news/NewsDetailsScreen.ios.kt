package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import github.alexzhirkevich.studentbsuby.repo.DataSource
import github.alexzhirkevich.studentbsuby.repo.NewsRepository
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBar
import github.alexzhirkevich.studentbsuby.ui.theme.values.Colors
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject
import platform.WebKit.WKWebView
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun NewsDetailsScreen(id: Int, viewModel: NewsViewModel) {
    val newsRepository = koinInject<NewsRepository>()
    val bgColor = Colors.GrayBackground
    var webViewRef by remember { mutableStateOf<WKWebView?>(null) }

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

        UIKitView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                WKWebView().apply {
                    webViewRef = this
                }
            },
            update = { }
        )

        LaunchedEffect(id, webViewRef) {
            val webView = webViewRef ?: return@LaunchedEffect
            newsRepository.getNewsItem(id, DataSource.Remote).collectLatest {
                webView.loadHTMLString(it.content, baseURL = null)
            }
        }
    }
}
