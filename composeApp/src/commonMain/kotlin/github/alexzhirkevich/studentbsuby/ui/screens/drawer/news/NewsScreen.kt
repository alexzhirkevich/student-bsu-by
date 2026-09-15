package github.alexzhirkevich.studentbsuby.ui.screens.drawer.news

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.news
import github.alexzhirkevich.studentbsuby.navigation.Route
import github.alexzhirkevich.studentbsuby.ui.common.NavigationMenuButton
import github.alexzhirkevich.studentbsuby.ui.common.animatedComposable
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.CollapsingToolbarScaffold
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.ScrollStrategy
import github.alexzhirkevich.studentbsuby.ui.common.toolbar.rememberCollapsingToolbarScaffoldState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
expect fun NewsDetailsScreen(id: Int, viewModel: NewsViewModel)

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun NewsScreen(
    isTablet: Boolean,
    viewModel: NewsViewModel = koinViewModel(),
    onMenuClicked: () -> Unit,
) {
    val navController = rememberNavController()

    val items = remember {
        listOf(
            Route.DrawerScreen.News.NewsList,
            Route.DrawerScreen.News.NewsDetail
        )
    }

    var currentRoute by rememberSaveable {
        mutableStateOf(Route.DrawerScreen.News.NewsList.route)
    }

    val scaffoldState = rememberCollapsingToolbarScaffoldState()

    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(MaterialTheme.colors.secondary)
                .zIndex(1f)
        )
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize()
                .bsuBackgroundPattern(
                    color = MaterialTheme.colors.primary.copy(alpha = .05f)
                ),
            state = scaffoldState,
            scrollStrategy = ScrollStrategy.EnterAlways,
            toolbar = {
                Toolbar(
                    isTablet = isTablet,
                    inDetail = currentRoute != Route.DrawerScreen.News.NewsList.route,
                    onMenuClicked = onMenuClicked,
                    onBackClicked = { navController.popBackStack() }
                )
            }
        ) {
            NavHost(navController = navController, startDestination = items[0].route) {
                animatedComposable(Route.DrawerScreen.News.NewsList) {
                    currentRoute = Route.DrawerScreen.News.NewsList.route
                    NewsListScreen(
                        viewModel = viewModel,
                        navController = navController,
                    )
                }

                animatedComposable(Route.DrawerScreen.News.NewsDetail) {
                    currentRoute = Route.DrawerScreen.News.route
                    val id = Route.DrawerScreen.News.NewsDetail.getArguments(it)

                    NewsDetailsScreen(
                        id = id,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Toolbar(
    isTablet: Boolean,
    inDetail: Boolean,
    onMenuClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    Column {
        TopAppBar(
            modifier = Modifier.zIndex(1f),
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.secondary
        ) {
            AnimatedContent(
                targetState = inDetail,
                transitionSpec = {
                    (scaleIn() + fadeIn()).togetherWith(scaleOut() + fadeOut())
                }
            ) { isDetailMode ->
                if (isDetailMode) {
                    NavigationMenuButton(
                        icon = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        onClick = onBackClicked
                    )
                } else {
                    if (!isTablet) {
                        NavigationMenuButton(onClick = onMenuClicked)
                    }
                }
            }
            Text(
                text = stringResource(Res.string.news),
                color = MaterialTheme.colors.onSecondary,
                style = MaterialTheme.typography.subtitle1
            )
        }

        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(LocalContentColor.current.copy(.1f)))
    }
}
