package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.empty
import github.alexzhirkevich.studentbsuby.resources.news_empty
import github.alexzhirkevich.studentbsuby.resources.something_gone_wrong
import github.alexzhirkevich.studentbsuby.data.models.News
import github.alexzhirkevich.studentbsuby.navigation.Route
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBar
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBarSwipeRefreshIndicator
import github.alexzhirkevich.studentbsuby.ui.common.ErrorWidget
import github.alexzhirkevich.studentbsuby.ui.common.swiperefresh.SwipeRefresh
import github.alexzhirkevich.studentbsuby.ui.common.swiperefresh.rememberSwipeRefreshState
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.communication.collectAsState
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsListScreen(
    viewModel: NewsViewModel,
    navController: NavController
) {
    val isRefreshing by viewModel.isUpdating.collectAsState()
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = isRefreshing
    )

    val news by viewModel.newsCommunication.collectAsState()

    SwipeRefresh(
        state = refreshState,
        onRefresh = viewModel::update,
        indicator = { state, trigger ->
            BsuProgressBarSwipeRefreshIndicator(state = state, trigger = trigger)
        }
    ) {
        when (val n = news) {
            is DataState.Success -> Body(
                news = n.value,
                modifier = Modifier.graphicsLayer {
                    translationY = refreshState.indicatorOffset
                }
            ) {
                navController.navigate(Route.DrawerScreen.News.NewsDetail(it.id.toString()).route)
            }
            is DataState.Empty -> Error(
                title = stringResource(Res.string.empty),
                error = stringResource(Res.string.news_empty)
            )
            is DataState.Error -> Error(
                title = stringResource(Res.string.something_gone_wrong),
                error = stringResource(n.message)
            )
            is DataState.Loading -> Loading()
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun Body(
    news : List<News>,
    modifier: Modifier = Modifier,
    onClick : (News) -> Unit = {}
) {
    LazyColumn(
        modifier.fillMaxSize(),
    ) {
        items(news.size) {
            NewsWidget(
                news = news[it],
                modifier = Modifier.padding(5.dp)
            ) {
                onClick(news[it])
            }
        }
        item { Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars)) }
    }
}

@ExperimentalMaterialApi
@Composable
private fun NewsWidget(news: News, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        backgroundColor = MaterialTheme.colors.secondary,
        modifier =  modifier ,
        onClick = onClick
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = news.title,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold
            )
            news.preview?.let {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        }
    }
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize()){
        BsuProgressBar(
            Modifier.align(Alignment.Center),
            size = 100.dp,
            tint = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun Error(
    title: String,
    error: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ErrorWidget(
            title = title,
            error = error,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
        )
    }
}
