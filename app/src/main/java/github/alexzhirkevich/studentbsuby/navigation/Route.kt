package github.alexzhirkevich.studentbsuby.navigation

import androidx.navigation.NamedNavArgument

sealed class Route(val route : String) {

    open val navArguments : List<NamedNavArgument> = emptyList()

    object AuthScreen : Route("auth")

    object DrawerScreen : Route("drawer"){
        object Subjects : Route("drawer_subjects")
        object Timetable : Route("drawer_timetable")
        object About : Route("drawer_about")
        object Hostel : Route("drawer_hostel")
    }

}