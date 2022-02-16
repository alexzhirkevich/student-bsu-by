package github.alexzhirkevich.studentbsuby.ui.screens

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import github.alexzhirkevich.studentbsuby.navigation.Route
import github.alexzhirkevich.studentbsuby.ui.common.animatedComposable
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.DrawerScreen
import github.alexzhirkevich.studentbsuby.ui.screens.login.LoginScreen
import github.alexzhirkevich.studentbsuby.ui.screens.settings.SettingsScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.onebone.toolbar.ExperimentalToolbarApi

@ExperimentalToolbarApi
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

    AnimatedNavHost(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        navController = navController,
        startDestination = Route.AuthScreen.route,
    ) {
        animatedComposable(
            Route.AuthScreen,
            enterTransition = {
                fadeIn()
            },
            popEnterTransition = {
                fadeIn()
            },
            popExitTransition = {
                fadeOut()
            },
            exitTransition = {
                fadeOut()
            }) {
            LoginScreen(navController)
        }
        animatedComposable(
            Route.DrawerScreen,
            enterTransition = {
                fadeIn()
            },
            popEnterTransition = {
                fadeIn()
            },
            popExitTransition = {
                fadeOut()
            },
            exitTransition = {
                fadeOut()
            }
        ) {
            DrawerScreen(navController)
        }
        animatedComposable(
            Route.SettingsScreen,
            enterTransition = {
                slideInHorizontally { it / 2 } + fadeIn()
            },
            popEnterTransition = {
                slideInHorizontally { it / 2 } + fadeIn()
            },
            popExitTransition = {
                slideOutHorizontally { it / 2 } + fadeOut()
            },
            exitTransition = {
                slideOutHorizontally { it / 2 } + fadeOut()
            }
        ) {
            SettingsScreen()
        }
    }
}

