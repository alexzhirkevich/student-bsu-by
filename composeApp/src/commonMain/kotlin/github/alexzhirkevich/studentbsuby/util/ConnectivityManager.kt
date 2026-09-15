package github.alexzhirkevich.studentbsuby.util

import github.alexzhirkevich.studentbsuby.util.communication.Releasable
import github.alexzhirkevich.studentbsuby.util.communication.StateCommunication

interface ConnectivityManager : Releasable {

    val isNetworkConnected: StateCommunication<Boolean>
}
