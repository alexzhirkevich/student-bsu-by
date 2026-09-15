package github.alexzhirkevich.studentbsuby.ui.screens.drawer.hostel

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.close
import github.alexzhirkevich.studentbsuby.resources.error_load_timetable
import github.alexzhirkevich.studentbsuby.resources.hostel
import github.alexzhirkevich.studentbsuby.resources.hostel_not_provided
import github.alexzhirkevich.studentbsuby.resources.hostel_not_provided_ext
import github.alexzhirkevich.studentbsuby.resources.hostel_provided
import github.alexzhirkevich.studentbsuby.resources.show_on_map
import github.alexzhirkevich.studentbsuby.data.models.HostelAdvert
import github.alexzhirkevich.studentbsuby.repo.HostelState
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBar
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBarSwipeRefreshIndicator
import github.alexzhirkevich.studentbsuby.ui.common.NavigationMenuButton
import github.alexzhirkevich.studentbsuby.ui.common.ErrorScreen
import github.alexzhirkevich.studentbsuby.ui.common.swiperefresh.SwipeRefresh
import github.alexzhirkevich.studentbsuby.ui.common.swiperefresh.rememberSwipeRefreshState
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern
import github.alexzhirkevich.studentbsuby.util.communication.collectAsState
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.CollapsingToolbarScaffold
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.ScrollStrategy
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.rememberCollapsingToolbarScaffoldState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HostelScreen(
    isTablet : Boolean,
    onMenuClicked : () -> Unit,
    hostelViewModel: HostelViewModel = koinViewModel()
) {
    val state by hostelViewModel.hostelStateCommunication.collectAsState()

    when (state) {
        is DataState.Success<*> -> {
            when (val value = state.valueOrNull()) {
                is HostelState.Provided -> ProvidedHostelScreen(
                    isTablet = isTablet,
                    address = value.address,
                    onMenuClicked = onMenuClicked,
                    onShowOnMapClicked = {
                        hostelViewModel.handle(HostelEvent.ShowHostelOnMapClicked(value))
                    },
                    image = hostelViewModel.getHostelImage(value),
                    updater = hostelViewModel
                )
                is HostelState.NotProvided -> NonProvidedHostelScreen(
                    isTablet,
                    ads = value.adverts,
                    viewModel = hostelViewModel,
                    onMenuClicked = onMenuClicked
                )
                else -> {}
            }
        }
        is DataState.Loading -> LoadingHostelScreen(
            isTablet = isTablet,
            onMenuClicked = onMenuClicked,
        )
        is DataState.Empty -> ErrorScreen(
            isTablet = isTablet,
            toolbarText = stringResource(Res.string.hostel),
            onMenuClicked = onMenuClicked,
            updater = hostelViewModel,
            error = stringResource(Res.string.error_load_timetable)
        )
        is DataState.Error -> ErrorScreen(
            isTablet = isTablet,
            toolbarText = stringResource(Res.string.hostel),
            onMenuClicked = onMenuClicked,
            updater = hostelViewModel,
            error = stringResource((state as DataState.Error).message)
        )
    }
}

@Composable
fun LoadingHostelScreen(
    isTablet: Boolean,
    onMenuClicked: () -> Unit = {}
) {
    Box(
        Modifier
            .fillMaxSize()
            .bsuBackgroundPattern(
                MaterialTheme.colors.primary.copy(alpha = .05f),
                true
            ),
    ) {
        Column(
            Modifier
                .background(MaterialTheme.colors.secondary)
                .zIndex(2f)
        ) {
            Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = Color.Transparent
            ) {
                if (!isTablet) {
                    NavigationMenuButton(onClick = onMenuClicked)
                }
                Text(
                    text = stringResource(Res.string.hostel),
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
        BsuProgressBar(
            modifier = Modifier.align(Alignment.Center),
            tint = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun ProvidedHostelScreen(
    isTablet: Boolean,
    address: String,
    onMenuClicked: () -> Unit,
    onShowOnMapClicked: () -> Unit,
    image: String? = null,
    updater: Updatable
) {
    val scaffoldState = rememberCollapsingToolbarScaffoldState()
    val isRefreshing by updater.isUpdating.collectAsState()
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = isRefreshing
    )
    LaunchedEffect(Unit) {
        scaffoldState.toolbarState.collapse(0)
        scaffoldState.toolbarState.expand(500)
    }

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = scaffoldState,
        enabled = false,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbarModifier = Modifier.background(MaterialTheme.colors.secondary),
        toolbar = {
            Column(Modifier.zIndex(2f)) {
                Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp
                ) {
                    if (!isTablet) {
                        NavigationMenuButton(onClick = onMenuClicked)
                    }
                    AnimatedVisibility(visible = scaffoldState.toolbarState.progress == 0f) {
                        Text(
                            text = stringResource(Res.string.hostel),
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }
            image?.let {
                AsyncImage(
                    model = it,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.4f)
                        .alpha(scaffoldState.toolbarState.progress)
                )
            }
        }) {
        SwipeRefresh(
            state = refreshState,
            swipeEnabled = scaffoldState.toolbarState.progress == 1f,
            onRefresh = updater::update,
            indicator = { state, offset ->
                BsuProgressBarSwipeRefreshIndicator(state = state, trigger = offset)
            },
            modifier = Modifier
                .fillMaxSize()
                .bsuBackgroundPattern(
                    MaterialTheme.colors.primary.copy(alpha = .05f),
                    true
                )
        ) {
            Column(
                Modifier
                    .graphicsLayer {
                        translationY = refreshState.indicatorOffset
                    }
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .padding(
                            horizontal = 30.dp,
                            vertical = 30.dp
                        )
                        .widthIn(max = 400.dp),
                    elevation = 3.dp,
                    backgroundColor = MaterialTheme.colors.secondary,
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(Res.string.hostel_provided),
                            style = MaterialTheme.typography.h2,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = address,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
                Button(
                    onClick = onShowOnMapClicked,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            tint = MaterialTheme.colors.onPrimary,
                            contentDescription = "Show on map"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stringResource(Res.string.show_on_map),
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NonProvidedHostelScreen(
    isTablet: Boolean,
    ads: List<HostelAdvert>,
    viewModel: HostelViewModel,
    onMenuClicked: () -> Unit,
) {
    var needShowDialog by rememberSaveable { mutableStateOf(true) }

    if (needShowDialog) {
        Dialog(onDismissRequest = { needShowDialog = false }) {
            Card(backgroundColor = MaterialTheme.colors.secondary) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 5.dp
                    )
                ) {
                    Text(
                        text = stringResource(Res.string.hostel_not_provided),
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(Res.string.hostel_not_provided_ext),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    TextButton(
                        onClick = { needShowDialog = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(Res.string.close),
                            color = MaterialTheme.colors.onSecondary
                        )
                    }
                }
            }
        }
    }

    val scaffoldState = rememberCollapsingToolbarScaffoldState()
    val isRefreshing by viewModel.isUpdating.collectAsState()
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = isRefreshing
    )

    Column {
        Spacer(
            modifier = Modifier
                .zIndex(2f)
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(MaterialTheme.colors.secondary)
        )
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f),
            state = scaffoldState,
            scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
            toolbarModifier = Modifier.background(MaterialTheme.colors.secondary),
            toolbar = {
                Column(modifier = Modifier.animateContentSize()) {
                    TopAppBar(
                        backgroundColor = Color.Transparent,
                        elevation = 1.dp
                    ) {
                        if (!isTablet) {
                            NavigationMenuButton(onClick = onMenuClicked)
                        }
                        Text(
                            text = stringResource(Res.string.hostel),
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }) {
            SwipeRefresh(
                state = refreshState,
                onRefresh = viewModel::update,
                indicator = { state, offset ->
                    BsuProgressBarSwipeRefreshIndicator(state = state, trigger = offset)
                },
                modifier = Modifier
                    .fillMaxSize()
                    .bsuBackgroundPattern(
                        MaterialTheme.colors.primary.copy(alpha = .05f),
                        true
                    )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            translationY = refreshState.indicatorOffset
                        }
                ) {
                    items(ads.size) {
                        HostelAdWidget(
                            modifier = Modifier.padding(5.dp),
                            ad = ads[it],
                            onLocateClicked = {
                                viewModel.handle(HostelEvent.ShowAdOnMapClicked(ads[it]))
                            },
                            onCallClicked = {
                                viewModel.handle(HostelEvent.CallClicked(ads[it]))
                            }
                        )
                    }
                    item { Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars)) }
                }
            }
        }
    }
}
