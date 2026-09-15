package github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.zIndex
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.empty
import github.alexzhirkevich.studentbsuby.resources.months
import github.alexzhirkevich.studentbsuby.resources.something_gone_wrong
import github.alexzhirkevich.studentbsuby.resources.timetable_empty
import github.alexzhirkevich.studentbsuby.resources.timetable_empty_for_day
import github.alexzhirkevich.studentbsuby.resources.weekdays
import github.alexzhirkevich.studentbsuby.resources.weekdays_short
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBar
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBarSwipeRefreshIndicator
import github.alexzhirkevich.studentbsuby.ui.common.ErrorWidget
import github.alexzhirkevich.studentbsuby.ui.common.NavigationMenuButton
import github.alexzhirkevich.studentbsuby.ui.common.swiperefresh.SwipeRefresh
import github.alexzhirkevich.studentbsuby.ui.common.swiperefresh.rememberSwipeRefreshState
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.CollapsingToolbarScaffold
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.CollapsingToolbarScope
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.CollapsingToolbarState
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.ScrollStrategy
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.rememberCollapsingToolbarScaffoldState
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.rememberCollapsingToolbarState
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.animatedSquaresBackground
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern
import github.alexzhirkevich.studentbsuby.util.communication.collectAsState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringArrayResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

private const val TabsHeight = 40

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TimetableScreen(
    isTablet: Boolean,
    timetableViewModel: TimetableViewModel = koinViewModel(),
    onMenuClicked : () -> Unit = {},
) {
    val scaffoldState = rememberCollapsingToolbarScaffoldState(
        toolbarState = rememberCollapsingToolbarState()
    )

    val pagerState = rememberPagerState { 6 }

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
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            Toolbar(
                isTablet = isTablet,
                toolbarState = scaffoldState.toolbarState,
                viewModel = timetableViewModel,
                onMenuClicked = onMenuClicked,
                pagerState = pagerState,
            )
        }
    ) {
        val swipeDownEnabled by remember {
            derivedStateOf {
                scaffoldState.toolbarState.progress >= 1f - Float.MIN_VALUE
            }
        }
        Body(
            viewModel = timetableViewModel,
            pagerState = pagerState,
            swipeDownEnabled = swipeDownEnabled
        )
    }
}

@Composable
private fun CollapsingToolbarScope.Toolbar(
    isTablet : Boolean,
    toolbarState: CollapsingToolbarState,
    pagerState: PagerState,
    viewModel: TimetableViewModel,
    onMenuClicked: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val timetable by viewModel.timetableCommunication.collectAsState()
    val density = LocalDensity.current

    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier
            .zIndex(1f)
            .windowInsetsPadding(WindowInsets.statusBars),
        backgroundColor = Color.Transparent
    ) {
        val iconVisible by remember {
            derivedStateOf {
                toolbarState.height > 3 * TabsHeight * density.density
            }
        }
        AnimatedVisibility(
            visible = iconVisible,
            enter = slideInVertically { -2 * it },
            exit = slideOutVertically { -2 * it }
        ) {
            if (!isTablet) {
                NavigationMenuButton(onClick = onMenuClicked)
            }
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .parallax(.5f)
            .background(MaterialTheme.colors.secondary)
            .graphicsLayer {
                alpha = toolbarState.progress
            }
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
                text = viewModel.dayOfMonth.toString(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(10.dp),
                color = MaterialTheme.colors.primary
            )
            Column {
                Text(
                    text = stringArrayResource(Res.array.weekdays)[viewModel.dayOfWeek],
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.primary
                )
                Text(
                    text = "${stringArrayResource(Res.array.months)[viewModel.month]} ${viewModel.year}".uppercase(),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }

    Box(
        Modifier
            .zIndex(2f)
            .road(
                whenExpanded = Alignment.BottomCenter,
                whenCollapsed = Alignment.BottomCenter
            )
    ) {
        TabRow(
            modifier = Modifier
                .height(TabsHeight.dp)
                .align(Alignment.BottomCenter),
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Transparent,
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                        .height(2.dp)
                        .background(MaterialTheme.colors.primary)
                )
            }
        ) {
            val weekdays = stringArrayResource(Res.array.weekdays_short)
            for (i in 0..5) {
                Tab(
                    selected = pagerState.currentPage == i,
                    onClick = {
                        if (timetable is DataState.Success<*>) {
                            scope.launch {
                                runCatching {
                                    pagerState.scrollToPage(i)
                                }
                            }
                        }
                    }) {
                    Text(text = weekdays[i])
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Body(
    viewModel: TimetableViewModel,
    pagerState: PagerState,
    swipeDownEnabled: Boolean,
) {
    val timetable by viewModel.timetableCommunication.collectAsState()
    val isUpdating by viewModel.isUpdating.collectAsState()
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = isUpdating
    )

    SwipeRefresh(
        state = refreshState,
        onRefresh = viewModel::update,
        indicator = { state, trigger ->
            BsuProgressBarSwipeRefreshIndicator(state, trigger)
        },
        swipeEnabled = swipeDownEnabled && timetable !is DataState.Loading,
        modifier = Modifier.fillMaxSize()
    ) {
        when (val tt = timetable) {
            is DataState.Success -> {
                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    state = pagerState
                ) { page ->
                    if (tt.value[page].isNotEmpty()) {
                        TimetableWidget(
                            list = tt.value[page],
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    translationY = refreshState.indicatorOffset
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
                                title = stringResource(Res.string.empty),
                                error = stringResource(Res.string.timetable_empty_for_day)
                            )
                        }
                    }
                }
            }
            DataState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    ErrorWidget(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 70.dp),
                        title = stringResource(Res.string.empty),
                        error = stringResource(Res.string.timetable_empty)
                    )
                }
            }
            is DataState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    ErrorWidget(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 70.dp),
                        title = stringResource(Res.string.something_gone_wrong),
                        error = stringResource(tt.message)
                    )
                }
            }
            DataState.Loading -> {
                Box(Modifier.fillMaxSize()) {
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
}

private fun Modifier.pagerTabIndicatorOffset(
    pagerState: PagerState,
    tabPositions: List<TabPosition>,
    pageIndexMapping: (Int) -> Int = { it },
): Modifier = layout { measurable, constraints ->
    if (tabPositions.isEmpty()) {
        layout(constraints.maxWidth, 0) {}
    } else {
        val currentPage = minOf(tabPositions.lastIndex, pageIndexMapping(pagerState.currentPage))
        val currentTab = tabPositions[currentPage]
        val previousTab = tabPositions.getOrNull(currentPage - 1)
        val nextTab = tabPositions.getOrNull(currentPage + 1)
        val fraction = pagerState.currentPageOffsetFraction

        val indicatorWidth = if (fraction > 0 && nextTab != null) {
            lerp(currentTab.width, nextTab.width, fraction).roundToPx()
        } else if (fraction < 0 && previousTab != null) {
            lerp(currentTab.width, previousTab.width, -fraction).roundToPx()
        } else {
            currentTab.width.roundToPx()
        }
        val indicatorOffset = if (fraction > 0 && nextTab != null) {
            lerp(currentTab.left, nextTab.left, fraction).roundToPx()
        } else if (fraction < 0 && previousTab != null) {
            lerp(currentTab.left, previousTab.left, -fraction).roundToPx()
        } else {
            currentTab.left.roundToPx()
        }

        val placeable = measurable.measure(
            Constraints(
                minWidth = indicatorWidth,
                maxWidth = indicatorWidth,
                minHeight = 0,
                maxHeight = constraints.maxHeight
            )
        )

        layout(constraints.maxWidth, maxOf(placeable.height, constraints.minHeight)) {
            placeable.placeRelative(
                indicatorOffset,
                maxOf(constraints.minHeight - placeable.height, 0)
            )
        }
    }
}
