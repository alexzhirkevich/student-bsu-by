package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.zIndex
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.academ_debt
import github.alexzhirkevich.studentbsuby.resources.academ_debt_empty
import github.alexzhirkevich.studentbsuby.resources.common_receipts
import github.alexzhirkevich.studentbsuby.resources.common_receipts_empty
import github.alexzhirkevich.studentbsuby.resources.hostel
import github.alexzhirkevich.studentbsuby.resources.info
import github.alexzhirkevich.studentbsuby.resources.paidservices
import github.alexzhirkevich.studentbsuby.resources.tuition_fee
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBarSwipeRefreshIndicator
import github.alexzhirkevich.studentbsuby.ui.common.NavigationMenuButton
import github.alexzhirkevich.studentbsuby.ui.common.swiperefresh.SwipeRefresh
import github.alexzhirkevich.studentbsuby.ui.common.swiperefresh.rememberSwipeRefreshState
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.CollapsingToolbarScaffold
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.CollapsingToolbarScope
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.ScrollStrategy
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.rememberCollapsingToolbarScaffoldState
import github.alexzhirkevich.studentbsuby.util.communication.collectAsState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

private sealed class PaidServicesPage(
    val name : StringResource,
){
    data object Info : PaidServicesPage(Res.string.info)
    data object TuitionFee : PaidServicesPage(Res.string.tuition_fee)
    data object Hostel : PaidServicesPage(Res.string.hostel)
    data object AcademDebt : PaidServicesPage(Res.string.academ_debt)
    data object Common : PaidServicesPage(Res.string.common_receipts)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaidServicesScreen(
    isTablet : Boolean,
    viewModel: PaidServicesViewModel = koinViewModel(),
    onMenuClicked : () -> Unit ={}
) {
    val pages = remember {
        listOf(
            PaidServicesPage.Info,
            PaidServicesPage.TuitionFee,
            PaidServicesPage.AcademDebt,
            PaidServicesPage.Hostel,
            PaidServicesPage.Common,
        )
    }

    val scaffoldState = rememberCollapsingToolbarScaffoldState()

    Column {
        Spacer(modifier = Modifier
            .windowInsetsTopHeight(WindowInsets.statusBars)
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary)
            .zIndex(1f)
        )
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = scaffoldState,
            scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
            toolbar = {
                Toolbar(isTablet = isTablet, onMenuClicked = onMenuClicked)
            }
        ) {
            Body(
                viewModel = viewModel,
                pages = pages,
            )
        }
    }
}

@Composable
private fun CollapsingToolbarScope.Toolbar(isTablet : Boolean ,onMenuClicked : () -> Unit, ) {
    TopAppBar(
        modifier = Modifier.zIndex(1f),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        if (!isTablet) {
            NavigationMenuButton(onClick = onMenuClicked)
        }
        Text(
            text = stringResource(Res.string.paidservices),
            color = MaterialTheme.colors.onSecondary,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
private fun Body(
    viewModel: PaidServicesViewModel,
    pages: List<PaidServicesPage>,
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { pages.size }
    val isUpdating by viewModel.isUpdating.collectAsState()
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = isUpdating
    )

    Column(Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.secondary,
            edgePadding = 0.dp,
            modifier = Modifier.fillMaxWidth(),
            indicator = { tabs ->
                Box {
                    Spacer(
                        modifier = Modifier
                            .pagerTabIndicatorOffset(
                                pagerState = pagerState,
                                tabPositions = tabs
                            )
                            .height(2.dp)
                            .zIndex(Float.MAX_VALUE)
                            .align(Alignment.BottomCenter)
                            .background(MaterialTheme.colors.primary)
                    )
                }
            }
        ) {
            pages.forEachIndexed { index, page ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }) {
                    Text(
                        text = stringResource(page.name),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }

        SwipeRefresh(
            state = refreshState,
            onRefresh = viewModel::update,
            indicator = { state, offset ->
                BsuProgressBarSwipeRefreshIndicator(state = state, trigger = offset)
            },
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        translationY = refreshState.indicatorOffset
                    }
            ) { idx ->
                when (pages[idx]) {
                    PaidServicesPage.Info -> InfoPage(viewModel)
                    PaidServicesPage.TuitionFee -> TuitionFeePage(viewModel)
                    PaidServicesPage.AcademDebt -> CommonReceiptsPage(
                        viewModel = viewModel,
                        emptyErrorMsg = stringResource(Res.string.academ_debt_empty)
                    ) {
                        viewModel.academDebtReceiptsCommunication
                            .collectAsState().value
                    }
                    PaidServicesPage.Common -> CommonReceiptsPage(
                        viewModel = viewModel,
                        emptyErrorMsg = stringResource(Res.string.common_receipts_empty)
                    ) {
                        viewModel.commonReceiptsCommunication
                            .collectAsState().value
                    }
                    PaidServicesPage.Hostel -> HostelBillsPage(viewModel)
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
