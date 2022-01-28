package github.alexzhirkevich.studentbsuby.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import okhttp3.Interceptor
import okhttp3.Response

fun NavController.navigate(
    route: Route,
    options : NavOptionsBuilder.() -> Unit = {}) {

    navigate(route.route,options)
}

fun NavController.popToRoute(route: Route, inclusive: Boolean) {

    if (!popBackStack(route.route, inclusive)) {
        popBackStack()
        navigate(route.route)
        //  this.disableFor(animLen)
    }
}