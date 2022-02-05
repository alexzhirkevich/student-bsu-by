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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.glide.GlideImage
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.HostelAdvert
import github.alexzhirkevich.studentbsuby.repo.HostelState
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBar
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBarSwipeRefreshIndicator
import github.alexzhirkevich.studentbsuby.ui.common.NavigationMenuButton
import github.alexzhirkevich.studentbsuby.ui.common.ErrorScreen
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ExperimentalToolbarApi
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@ExperimentalToolbarApi
@ExperimentalMaterialApi
@Composable
fun HostelScreen(
    onMenuClicked : () -> Unit,
    hostelViewModel: HostelViewModel = hiltViewModel()
) {
    val state by hostelViewModel.hostelState.collectAsState()

    when (state) {
        is DataState.Success<*> -> {
            when (val value = state.valueOrNull()) {
                is HostelState.Provided -> ProvidedHostelScreen(
                    address = value.address,
                    onMenuClicked = onMenuClicked,
                    onShowOnMapClicked = {
                        hostelViewModel.showOnMap(value)
                    },
                    image = hostelViewModel.getHostelImage(value),
                    updater = hostelViewModel
                )
                is HostelState.NotProvided -> NonProvidedHostelScreen(
                    ads = value.adverts,
                    viewModel = hostelViewModel,
                    onMenuClicked = onMenuClicked
                )
            }
        }
        is DataState.Loading -> LoadingHostelScreen(
            onMenuClicked = onMenuClicked,
        )
        is DataState.Empty -> ErrorScreen(
            toolbarText = stringResource(id = R.string.hostel),
            onMenuClicked = onMenuClicked,
            updater = hostelViewModel,
            error = stringResource(id = R.string.error_load_timetable)
        )
        is DataState.Error -> ErrorScreen(
            toolbarText = stringResource(id = R.string.hostel),
            onMenuClicked = onMenuClicked,
            updater = hostelViewModel,
            error = stringResource(id = (state as DataState.Error).message)
        )
    }
}

@Composable
fun LoadingHostelScreen(onMenuClicked: () -> Unit = {}) {

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
            Spacer(modifier = Modifier.statusBarsHeight())
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = Color.Transparent
            ) {
                NavigationMenuButton(onClick = onMenuClicked)
                Text(
                    text = stringResource(id = R.string.hostel),
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
        BsuProgressBar(modifier = Modifier.align(Alignment.Center))
    }
}


@ExperimentalToolbarApi
@Composable
private fun ProvidedHostelScreen(
    address : String,
    onMenuClicked: () -> Unit,
    onShowOnMapClicked : () -> Unit,
    image : String?=null,
    updater : Updatable
) {
    val scaffoldState = rememberCollapsingToolbarScaffoldState()
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = updater.isUpdating.value)
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
                Spacer(modifier = Modifier.statusBarsHeight())
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp
                ) {
                    NavigationMenuButton(onClick = onMenuClicked)
                    AnimatedVisibility(visible = scaffoldState.toolbarState.progress == 0f) {
                        Text(
                            text = stringResource(id = R.string.hostel),
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }
            image?.let {
                GlideImage(
                    imageModel = it,
                    modifier = Modifier
                        .fillMaxWidth()
//                        .parallax()
                        .aspectRatio(1.4f)
                        .alpha(scaffoldState.toolbarState.progress),
                    loading = {
                        BsuProgressBar(
                            modifier = Modifier.align(Alignment.Center),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                )
            }
        }) {
        SwipeRefresh(
            state = refreshState,
            swipeEnabled = scaffoldState.toolbarState.progress == 1f,
            onRefresh = updater::update,
            indicator = { state,offset->
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
                        modifier = Modifier
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(id = R.string.hostel_provided),
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
                            text = stringResource(id = R.string.show_on_map),
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }

            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun NonProvidedHostelScreen(
    ads : List<HostelAdvert>,
    viewModel: HostelViewModel,
    onMenuClicked: () -> Unit,
) {

    var needShowDialog by rememberSaveable {
        mutableStateOf(true)
    }

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
                        text = stringResource(id = R.string.hostel_not_provided),
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.hostel_not_provided_ext),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    TextButton(
                        onClick = { needShowDialog=false },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.close),
                            color = MaterialTheme.colors.onSecondary
                        )
                    }
                }
            }
        }
    }

    val scaffoldState = rememberCollapsingToolbarScaffoldState()
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.isUpdating.value
    )

    Column {
        Spacer(
            modifier = Modifier
                .zIndex(2f)
                .fillMaxWidth()
                .statusBarsHeight()
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
                Column(
                    modifier = Modifier.animateContentSize()
                ) {

                    TopAppBar(
                        backgroundColor = Color.Transparent,
                        elevation = 0.dp
                    ) {
                        NavigationMenuButton(onClick = onMenuClicked)
                        Text(
                            text = stringResource(id = R.string.hostel),
                            color = MaterialTheme.colors.onSecondary,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
            }) {
            SwipeRefresh(
                state = refreshState,
                onRefresh = viewModel::update,
                indicator = { state,offset->
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
                        .navigationBarsWithImePadding()
                        .graphicsLayer {
                            translationY = refreshState.indicatorOffset
                        }

                ) {
                    items(ads.size) {
                        HostelAdWidget(
                            modifier = Modifier
                                .padding(5.dp),
                            ad = ads[it],
                            onLocateClicked = {
                                viewModel.showOnMap(ads[it])
                            },
                            onCallClicked = {
                                viewModel.call(ads[it])
                            }
                        )
                    }
                }
            }
        }
    }
}