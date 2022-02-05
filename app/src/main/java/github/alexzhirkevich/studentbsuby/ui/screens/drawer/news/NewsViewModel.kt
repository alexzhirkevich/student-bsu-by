package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news

import android.content.Intent
import android.webkit.*
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.News
import github.alexzhirkevich.studentbsuby.repo.DataSource
import github.alexzhirkevich.studentbsuby.repo.NewsRepository
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.LoginCookieManager
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepo : NewsRepository,
    private val logger : Logger,
    private val httpClient: OkHttpClient,
    private val loginCookieManager: LoginCookieManager,
) : ViewModel(), Updatable {

    private val _news = mutableStateOf<DataState<List<News>>>(DataState.Empty)
    val news : State<DataState<List<News>>> get() = _news

    private val _isUpdating = mutableStateOf(false)
    override val isUpdating: State<Boolean> get() = _isUpdating

    init {
        updateNews(DataSource.All)
    }

    override fun update() {
        _isUpdating.value = true
        updateNews(DataSource.Remote)
    }

    suspend fun setupWebView(newsId : Int, webView : WebView) = withContext(Dispatchers.Main) {

        CookieManager.getInstance()
            .setCookie(newsRepo.baseUrl.toString(), loginCookieManager.getCookies())
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.context.startActivity(
                    Intent(Intent.ACTION_VIEW, request.url))
                return true
            }
        }
        newsRepo.getNewsItem(newsId, DataSource.Remote).collectLatest {

            webView.loadDataWithBaseURL(newsRepo.newsUrl,it.content, "text/html", "UTF-8",null)
        }
    }

    private var newsJob : Job?= null
    private fun updateNews(dataSource: DataSource) {
        newsRepo.get(dataSource)
            .flowOn(Dispatchers.IO)
            .onCompletion {
                _isUpdating.value = false
            }
            .onEmpty {
                if (_news.value !is DataState.Success){
                    _news.value = DataState.Empty
                }
            }
            .catch {
                if (_news.value !is DataState.Success){
                    _news.value = DataState.Error(R.string.error_load_messages,it)
                }
                logger.log(
                    msg = "Failed to get news",
                    tag = javaClass.simpleName,
                    logLevel = Logger.LogLevel.Error,
                    cause = it
                )
            }
            .onEach {
                _news.value = if (it.isNotEmpty())
                    DataState.Success(it)
                else DataState.Empty
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
            .also {
                newsJob?.cancel()
                newsJob = it
            }

    }
}