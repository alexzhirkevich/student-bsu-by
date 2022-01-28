package github.alexzhirkevich.studentbsuby.ui.screens

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import github.alexzhirkevich.studentbsuby.navigation.Route
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.DrawerScreen
import github.alexzhirkevich.studentbsuby.ui.screens.login.LoginScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MainScreen() {

    val navController = rememberAnimatedNavController()

    navController.setLifecycleOwner(LocalLifecycleOwner.current)
    LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher?.let {
        navController.setOnBackPressedDispatcher(it)
    }

//    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)

    AnimatedNavHost(
        navController = navController,
        startDestination = Route.AuthScreen.route,
    ) {
        animatedComposable(
            Route.AuthScreen,
            exitTransition = { fadeOut()},
        ) {
            LoginScreen(navController)
        }
        animatedComposable(
            Route.DrawerScreen,
            enterTransition = {
                fadeIn()
            },
            popEnterTransition = {
                fadeIn()
            }
        ){
            DrawerScreen(navController)
//
        }
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.animatedComposable(
    route: Route,
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Left)
    },
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? ={
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left)
    },
    popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Right)
    },
    popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Right)

    },
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route.route,
        route.navArguments,
        deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content
    )
}