package github.alexzhirkevich.studentbsuby.ui.screens.drawer

import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.User
import github.alexzhirkevich.studentbsuby.navigation.Route
import github.alexzhirkevich.studentbsuby.navigation.navigate
import github.alexzhirkevich.studentbsuby.ui.common.animatedComposable
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.about.AboutScreen
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.hostel.HostelScreen
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.news.NewsScreen
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.paidservices.PaidServicesScreen
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.subjects.SubjectsScreen
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable.TimetableScreen
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import me.onebone.toolbar.ExperimentalToolbarApi

private val AvatarWidth = 150.dp
private val AvatarHeight = 200.dp
private val CardTopPadding = 60.dp
private val CardPaddings = 12.dp

private sealed class DrawerRoute(
    val icon: ImageVector, @StringRes val title : Int, val  route: Route){
    object Subjects : DrawerRoute(Icons.Default.Dashboard, R.string.subjects, Route.DrawerScreen.Subjects)
    object Timetable : DrawerRoute(Icons.Default.FormatListBulleted, R.string.timetable, Route.DrawerScreen.Timetable)
    object About : DrawerRoute(Icons.Default.Info, R.string.about, Route.DrawerScreen.About)
    object Hostel : DrawerRoute(Icons.Default.House, R.string.hostel, Route.DrawerScreen.Hostel)
    object PaidServices : DrawerRoute(Icons.Default.Payment, R.string.paidservices, Route.DrawerScreen.PaidServices)
    object News : DrawerRoute(Icons.Default.Campaign, R.string.news, Route.DrawerScreen.News)
}

@ExperimentalComposeUiApi
@ExperimentalToolbarApi

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun DrawerScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()

    val childNavController = rememberAnimatedNavController()
    val initial =  DrawerRoute.Timetable


    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.background(MaterialTheme.colors.secondary),
        drawerShape = remember { DrawerShape() },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {

            DrawerContent(
                navController = navController,
                childNavController = childNavController,
                routes = listOf(
                    DrawerRoute.News,
                    DrawerRoute.Subjects,
                    DrawerRoute.Timetable,
                    DrawerRoute.Hostel,
                    DrawerRoute.PaidServices,
                    DrawerRoute.About,
                    ),
                initial = initial,
                profileViewModel = profileViewModel
            ){
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }

        },
        topBar = {

        }
    ) {

        val onMenuClicked : () -> Unit = remember {{
            scope.launch {
                scaffoldState.drawerState.let {
                    if (it.isClosed)
                        it.animateTo(DrawerValue.Open, tween())
                    else it.close()
                }
            }
        }}
        AnimatedNavHost(
            navController = childNavController,
            startDestination = initial.route.route
        ) {

            val animIn = scaleIn(initialScale = .95f) + fadeIn()
            val animOut = scaleOut(targetScale = .95f) + fadeOut()


            animatedComposable(
                DrawerRoute.News.route,
                enterTransition = { animIn },
                exitTransition = {animOut}
            ) {
                NewsScreen(onMenuClicked = onMenuClicked)
            }
            animatedComposable(
                DrawerRoute.Subjects.route,
                enterTransition = { animIn },
                exitTransition = {animOut}
            ) {
                SubjectsScreen(onMenuClicked = onMenuClicked)
            }

            animatedComposable(
                DrawerRoute.Timetable.route,
                enterTransition = { animIn },
                exitTransition = {animOut}
            ){
                TimetableScreen(onMenuClicked = onMenuClicked)
            }

            animatedComposable(
                DrawerRoute.Hostel.route,
                enterTransition = { animIn },
                exitTransition = {animOut}
            ){
                HostelScreen(onMenuClicked = onMenuClicked)
            }

            animatedComposable(
                DrawerRoute.About.route,
                enterTransition = { animIn },
                exitTransition = {animOut}
            ){
                AboutScreen(onMenuClicked = onMenuClicked)
            }

            animatedComposable(
                DrawerRoute.PaidServices.route,
                enterTransition = { animIn },
                exitTransition = {animOut}
            ){
                PaidServicesScreen(onMenuClicked = onMenuClicked)
            }
        }
    }
}


@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
private fun DrawerContent(
    navController: NavController,
    childNavController: NavController,
    routes: List<DrawerRoute>,
    initial: DrawerRoute,
    profileViewModel: ProfileViewModel,
    onRouteSelected: () -> Unit = {}
) {
    Column(
        Modifier.background(MaterialTheme.colors.secondary)
    ) {
        Box(
            Modifier
                .clip(ProfileRoundShape())
                .background(MaterialTheme.colors.primaryVariant)
                .bsuBackgroundPattern(
                    MaterialTheme.colors.onPrimary
                        .copy(alpha = .1f)
                )
                .statusBarsPadding()
        ) {
            Box(
                Modifier
                    .padding(
                        start = CardPaddings + AvatarWidth,
                        end = CardPaddings,
                    )
                    .height(CardTopPadding)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_text),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .scale(1.4f)
                        .align(Alignment.Center)
                )
            }

            ProfileCard(
                photo = profileViewModel.photo.value,
                user = profileViewModel.user.value,
                modifier = Modifier.padding(
                    top = CardTopPadding,
                    start = CardPaddings,
                    end = CardPaddings,
                    bottom = CardPaddings
                )
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .navigationBarsWithImePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            var currentRoute by rememberSaveable {
                mutableStateOf(initial.route.route)
            }
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                routes.forEach {
                    DrawerButton(
                        icon = it.icon,
                        text = stringResource(id = it.title),
                        onClick = {
                            onRouteSelected()
                            if (currentRoute != it.route.route) {
                                currentRoute = it.route.route
                                childNavController.navigate(it.route) {
                                    val last =
                                        childNavController.backQueue.lastOrNull()?.destination?.id
                                            ?: childNavController.graph.findStartDestination().id
                                    popUpTo(last) {
                                        childNavController.backQueue.last().destination
                                        saveState = true
                                        inclusive = true
                                    }

                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        })
                }
                Spacer(
                    modifier = Modifier
                        .height(.5.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.typography.caption.color)
                )
                DrawerButton(
                    icon = Icons.Default.Settings,
                    text = stringResource(id = R.string.settings),
                    onClick = {
                        onRouteSelected()
                        navController.navigate(Route.SettingsScreen)
                    })

            }

            DrawerButton(
                icon = Icons.Default.Logout,
                text = stringResource(id = R.string.logout),
                onClick = {
                    profileViewModel.logout()
                    navController.navigate(Route.AuthScreen) {
                        launchSingleTop = true
                        popUpTo(Route.DrawerScreen.route) {
                            inclusive = true
                        }
                    }
                })
        }
    }
}

@Composable
private fun DrawerButton(
    icon : ImageVector,
    text : String,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
    content : @Composable RowScope.() -> Unit = {}
){

    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(
            color = MaterialTheme.colors.primary,
        )
    ){
        Row(
            modifier
                .clickable(onClick = onClick)
                .heightIn(min = 45.dp)
                .padding(
                    horizontal = 15.dp,
                    vertical = 5.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Menu Icon",
                tint = MaterialTheme.colors.primary,
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.weight(1f)
            )
            content()
        }
    }
}

@Composable
private fun ProfileCard(
    photo : DataState<ImageBitmap>,
    user: DataState<User>,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Card(
            shape = MaterialTheme.shapes.medium,
            backgroundColor = MaterialTheme.colors.secondary,
            elevation = 5.dp,
            modifier = Modifier
                .offset(y = (-AvatarWidth / 3))
                .zIndex(2f)
        ) {
            val photoValue = photo.valueOrNull()
            if (photoValue != null) {
                Image(
                    bitmap = photoValue,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Photo",
                    modifier = Modifier
                        .width(AvatarWidth)
                        .height(AvatarHeight)
                )
            } else Spacer(modifier = Modifier
                .width(AvatarWidth)
                .height(AvatarHeight))
        }
        
        Card(
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
        ) {

            val userValue = user.valueOrNull() ?: User()

            Column(Modifier.padding(CardPaddings)) {

                Text(
                    text = userValue.name,
                    modifier = Modifier
                        .padding(start = AvatarWidth),
                    maxLines = 3,
                    overflow =TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = userValue.faculty,
                    modifier = Modifier.padding(start = AvatarWidth),
                    maxLines = 4,
                    overflow =TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.caption
                )
            }
            Column(Modifier
                    .padding(
                        top = AvatarHeight *2/3 + CardPaddings + 10.dp,
                        start = CardPaddings,
                        end = CardPaddings,
                        bottom = CardPaddings
                    )
            ) {
                Text(
                    text = userValue.info,
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.body1,
                )
                Text(
                    text = userValue.avgGrade,
                    color = MaterialTheme.colors.onSecondary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}