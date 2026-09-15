@file:OptIn(ExperimentalAnimationApi::class)

package github.alexzhirkevich.studentbsuby.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import github.alexzhirkevich.studentbsuby.navigation.Route
import github.alexzhirkevich.studentbsuby.ui.common.animatedComposable
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.DrawerScreen
import github.alexzhirkevich.studentbsuby.ui.screens.login.LoginEvent
import github.alexzhirkevich.studentbsuby.ui.screens.login.LoginScreen
import github.alexzhirkevich.studentbsuby.ui.screens.login.LoginViewModel
import github.alexzhirkevich.studentbsuby.ui.screens.settings.SettingsScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen() {

    val navController = rememberNavController()

//    navController.setLifecycleOwner(LocalLifecycleOwner.current)
//    LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher?.let {
//        navController.setOnBackPressedDispatcher(it)
//    }


    val loginVm = koinViewModel<LoginViewModel>()

    LaunchedEffect(null) {
        loginVm.handle(LoginEvent.InitLogin(navController))
    }

    NavHost(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        navController = navController,
        startDestination = if (loginVm.skipLogin)
            Route.DrawerScreen.route else Route.AuthScreen.route,
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
            SettingsScreen(onBackClicked = { navController.popBackStack() })
        }
    }
}

