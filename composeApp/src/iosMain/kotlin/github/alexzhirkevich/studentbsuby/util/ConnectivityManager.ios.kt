@file:OptIn(ExperimentalForeignApi::class)

package github.alexzhirkevich.studentbsuby.util

import github.alexzhirkevich.studentbsuby.util.communication.MutableStateCommunication
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Network.*
import platform.darwin.dispatch_queue_create

class InternetConnectivityManager(
    override val isNetworkConnected: MutableStateCommunication<Boolean>,
) : ConnectivityManager {

    private var wifiAvailable = false
    private var cellAvailable = false

    private val monitor = nw_path_monitor_create()

    init {
        nw_path_monitor_set_queue(
            monitor,
            dispatch_queue_create("ConnectivityManager", null)
        )
        nw_path_monitor_set_update_handler(monitor) { path ->
            val satisfied = nw_path_get_status(path) == nw_path_status_satisfied
            wifiAvailable = satisfied && nw_path_uses_interface_type(path, nw_interface_type_wifi)
            cellAvailable = satisfied && nw_path_uses_interface_type(path, nw_interface_type_cellular)
            isNetworkConnected.map(wifiAvailable || cellAvailable)
        }
        nw_path_monitor_start(monitor)
    }

    override fun release() {
        nw_path_monitor_cancel(monitor)
    }
}
