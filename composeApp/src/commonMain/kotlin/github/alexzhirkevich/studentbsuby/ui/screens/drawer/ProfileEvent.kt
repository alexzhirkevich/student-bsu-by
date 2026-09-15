package github.alexzhirkevich.studentbsuby.ui.screens.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import github.alexzhirkevich.studentbsuby.navigation.Route
import github.alexzhirkevich.studentbsuby.resources.Res
import github.alexzhirkevich.studentbsuby.resources.about
import github.alexzhirkevich.studentbsuby.resources.hostel
import github.alexzhirkevich.studentbsuby.resources.news
import github.alexzhirkevich.studentbsuby.resources.paidservices
import github.alexzhirkevich.studentbsuby.resources.subjects
import github.alexzhirkevich.studentbsuby.resources.timetable
import github.alexzhirkevich.studentbsuby.util.Event
import github.alexzhirkevich.studentbsuby.util.communication.Serializer
import org.jetbrains.compose.resources.StringResource

sealed class DrawerRoute(
    val icon: ImageVector, val title : StringResource, val  route: Route
){
    object Subjects : DrawerRoute(Icons.Default.Dashboard, Res.string.subjects, Route.DrawerScreen.Subjects)
    object Timetable : DrawerRoute(Icons.Default.FormatListBulleted, Res.string.timetable, Route.DrawerScreen.Timetable)
    object About : DrawerRoute(Icons.Default.Info, Res.string.about, Route.DrawerScreen.About)
    object Hostel : DrawerRoute(Icons.Default.House, Res.string.hostel, Route.DrawerScreen.Hostel)
    object PaidServices : DrawerRoute(Icons.Default.Payment, Res.string.paidservices, Route.DrawerScreen.PaidServices)
    object News : DrawerRoute(Icons.Default.Campaign, Res.string.news, Route.DrawerScreen.News)
}

enum class ConnectivityUi {
    Connected,
    Connecting,
    Offline,
}
object ConnectivityUiSerializer : Serializer<ConnectivityUi> {
    override fun serialize(value: ConnectivityUi): Map<String, Any?> {
        return mapOf("key" to value.name)
    }

    override fun deserialize(bundle: Map<String, Any?>): ConnectivityUi {
        val name = bundle["key"] as String
        return ConnectivityUi.valueOf(name)
    }
}

sealed interface ProfileEvent : Event {

    object UpdateRequested : ProfileEvent
    class Logout(val navController: NavController) : ProfileEvent
    class RouteSelected(
        val route : DrawerRoute,
        val  navController: NavController
    ) : ProfileEvent
    class SettingsClicked(val navController: NavController) : ProfileEvent
}
