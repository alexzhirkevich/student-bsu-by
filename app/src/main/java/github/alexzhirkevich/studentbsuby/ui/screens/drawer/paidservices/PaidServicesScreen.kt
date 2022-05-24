package github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBarSwipeRefreshIndicator
import github.alexzhirkevich.studentbsuby.ui.common.NavigationMenuButton
import github.alexzhirkevich.studentbsuby.util.applyIf
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.CollapsingToolbarScope
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState


private sealed class PaidServicesPage(
    @StringRes val  name : Int,
){
    object Info : PaidServicesPage(R.string.info)
    object TuitionFee : PaidServicesPage(R.string.tuition_fee)
    object Hostel : PaidServicesPage(R.string.hostel)
    object AcademDebt : PaidServicesPage(R.string.academ_debt)
    object Common : PaidServicesPage(R.string.common_receipts)
}

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun PaidServicesScreen(
    viewModel: PaidServicesViewModel = hiltViewModel(),
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
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.isUpdating.value)

    Column {
        Spacer(modifier = Modifier
            .statusBarsHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary)
            .zIndex(1f)
        )
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = scaffoldState,
            enabled = refreshState.indicatorOffset==0f,
            scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
            toolbar = {
                Toolbar(onMenuClicked = onMenuClicked)
            }
        ) {
            Body(
                viewModel = viewModel,
                pages = pages,
                swipeEnabled = scaffoldState.offsetY == 0,
                refreshState = refreshState
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun CollapsingToolbarScope.Toolbar(onMenuClicked : () -> Unit, ) {
    TopAppBar(
        modifier = Modifier.zIndex(1f),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        NavigationMenuButton(onClick = onMenuClicked)
        Text(
            text = stringResource(id = R.string.paidservices),
            color = MaterialTheme.colors.onSecondary,
            style = MaterialTheme.typography.subtitle1
        )
    }


}

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
private fun Body(
    viewModel: PaidServicesViewModel,
    pages: List<PaidServicesPage>,
    swipeEnabled : Boolean,
    refreshState: SwipeRefreshState
)  {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

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
                            .applyIf(pagerState.pageCount == tabs.size) {
                                it.pagerTabIndicatorOffset(
                                    pagerState = pagerState,
                                    tabs
                                )
                            }
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
                        text = stringResource(id = page.name),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }


        SwipeRefresh(
            swipeEnabled = swipeEnabled,
            state = refreshState,
            onRefresh = viewModel::update,
            indicator = { state, offset ->
                BsuProgressBarSwipeRefreshIndicator(state = state, trigger = offset)
            },
        ) {
            HorizontalPager(
                count = pages.size,
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
                        emptyErrorMsg = stringResource(id = R.string.academ_debt_empty)
                    ) {
                        viewModel.academDebtReceipts.value
                    }
                    PaidServicesPage.Common -> CommonReceiptsPage(
                        viewModel = viewModel,
                        emptyErrorMsg = stringResource(id = R.string.common_receipts_empty)) {
                        viewModel.commonReceipts.value
                    }
                    PaidServicesPage.Hostel -> HostelBillsPage(viewModel)
                }
            }
        }

    }
}

