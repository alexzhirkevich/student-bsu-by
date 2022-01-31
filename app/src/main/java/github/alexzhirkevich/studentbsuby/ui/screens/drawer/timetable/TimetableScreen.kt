package github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.ui.common.*
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.animatedSquaresBackground
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import me.onebone.toolbar.*

private const val TabsHeight = 40

@ExperimentalToolbarApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@FlowPreview
@Composable
fun TimetableScreen(
    timetableViewModel: TimetableViewModel = hiltViewModel(),
    onMenuClicked : () -> Unit = {}
) {

    val timetable by timetableViewModel.timetable.collectAsState()

    when(timetable){
        is DataState.Error-> ErrorScreen(
            toolbarText = stringResource(id = R.string.timetable),
            error = stringResource(id = (timetable as DataState.Error).message),
            updater = timetableViewModel,
            onMenuClicked = onMenuClicked
        )
        else -> SuccessTimetableScreen(timetableViewModel,onMenuClicked)
    }

}

@FlowPreview
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun SuccessTimetableScreen(
    timetableViewModel: TimetableViewModel = hiltViewModel(),
    onMenuClicked : () -> Unit = {}
) {
    val scaffoldState = rememberCollapsingToolbarScaffoldState(
        toolbarState = rememberCollapsingToolbarState(0)
    )
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = timetableViewModel.isUpdating.value
    )

    val pagerState = rememberPagerState()

    LaunchedEffect(Unit) {
        scaffoldState.toolbarState.collapse(0)
        scaffoldState.toolbarState.expand(500)
    }

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .bsuBackgroundPattern(
                MaterialTheme.colors.primary.copy(alpha = .05f),
                true
            ),
        state = scaffoldState,
        enabled = swipeRefreshState.indicatorOffset == 0f,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            Toolbar(
                toolbarState = scaffoldState.toolbarState,
                viewModel = timetableViewModel,
                onMenuClicked = onMenuClicked,
                pagerState = pagerState,
            )
        }
    ) {
        Body(
            viewModel = timetableViewModel,
            refreshState = swipeRefreshState,
            swipeEnabled = scaffoldState.toolbarState.progress == 1f,
            pagerState = pagerState
        )
    }
}

@ExperimentalPagerApi
@FlowPreview
@Composable
private fun CollapsingToolbarScope.Toolbar(
    toolbarState: CollapsingToolbarState,
    pagerState: PagerState,
    viewModel: TimetableViewModel,
    onMenuClicked: () -> Unit
) {

    val scope = rememberCoroutineScope()

    val timetable by viewModel.timetable.collectAsState()

    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier
            .zIndex(1f)
            .statusBarsPadding(),
        backgroundColor = Color.Transparent
    ) {
        val status = LocalWindowInsets.current.statusBars.bottom
        with(LocalDensity.current) {
            AnimatedVisibility(
                visible = toolbarState.height > 3 * TabsHeight.dp.roundToPx() + status,
                enter = slideInVertically { -2 * it },
                exit = slideOutVertically { -2 * it }
            ) {

               BurgerMenuButton(onClick = onMenuClicked)
            }
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .parallax(.5f)
            .background(MaterialTheme.colors.secondary)
            .alpha(toolbarState.progress)
            .animatedSquaresBackground(
                color = MaterialTheme.colors.primary.copy(alpha = .05f),
                count = 10,
                size = 200.dp
            )
    ) {
        Row(
            Modifier
                .align(Alignment.Center)
                .padding(top = 30.dp + TabsHeight.dp / 2, bottom = 30.dp + TabsHeight.dp / 2),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = viewModel.currentDay.toString(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(10.dp),
                color = MaterialTheme.colors.primary
            )
            Column {
                Text(
                    text = stringArrayResource(id = R.array.weekdays)
                            [viewModel.currentDayOfWeek],
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.primary

                )
                Text(
                    text = "${stringArrayResource(id = R.array.months)[viewModel.currentMonth]} ${viewModel.currentYear}".uppercase(),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primary

                )
            }
        }
    }

    Box(
        Modifier
            .road(
                whenExpanded = Alignment.BottomCenter,
                whenCollapsed = Alignment.BottomCenter
            )

    ) {

        Spacer(modifier = Modifier.statusBarsHeight(TabsHeight.dp))
        TabRow(
            modifier = Modifier
                .height(TabsHeight.dp)
                .align(Alignment.BottomCenter),
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent,
            indicator = { tabs ->
                Box {
                    Spacer(
                        modifier = Modifier
                            .pagerTabIndicatorOffset(pagerState, tabs)
                            .height(2.dp)
                            .background(MaterialTheme.colors.primary)
                            .align(Alignment.BottomCenter)
                    )
                }
            }
        ) {
            val weekdays = stringArrayResource(id = R.array.weekdays_short)
            for (i in 0..5) {
                Tab(
                    selected = pagerState.currentPage == i,
                    onClick = {
                        if (timetable is DataState.Success<*>) {
                            scope.launch {
                                kotlin.runCatching {
                                    pagerState.scrollToPage(i)
                                }
                            }
                        }
                    }) {
                    Text(
                        text = weekdays[i],
                    )
                }
            }
        }
    }

}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@FlowPreview
@Composable
private fun Body(
    swipeEnabled: Boolean,
    refreshState: SwipeRefreshState,
    viewModel: TimetableViewModel,
    pagerState: PagerState,
) {

    val timetable by viewModel.timetable.collectAsState()


    Column{

        when (val tt = timetable) {
            is DataState.Success,is DataState.Empty -> {
                SwipeRefresh(
                    state = refreshState,
                    onRefresh = viewModel::update,
                    indicator = { state, trigger ->
                        BsuProgressBarSwipeRefreshIndicator(state, trigger)
                    },
                    swipeEnabled = swipeEnabled,
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    if (tt is DataState.Success) {
                        Pager(
                            offset = refreshState.indicatorOffset,
                            pagerState = pagerState,
                            timetable = tt.value
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            ErrorWidget(
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(top = 70.dp),
                                title = stringResource(id = R.string.empty),
                                error = stringResource(id = R.string.timetable_empty)
                            )
                        }
                    }
                }
            }
            is DataState.Loading -> Box(
                modifier = Modifier
                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState())
            ) {
                BsuProgressBar(
                    Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 100.dp),
                    size = 100.dp,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    }
}

@FlowPreview
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun Pager(
    pagerState: PagerState,
    timetable: Timetable,
    offset : Float,
) {

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState,
        count = timetable.size
    ) {

        if (timetable[it].isNotEmpty()) {
            TimetableWidget(
                list = timetable[it],
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        translationY = offset
                    }
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                ErrorWidget(
                    modifier = Modifier
                        .padding(top = 70.dp)
                        .align(Alignment.TopCenter),
                    title = stringResource(id = R.string.empty),
                    error = stringResource(id = R.string.timetable_empty_for_day)
                )
            }
        }
    }
}
