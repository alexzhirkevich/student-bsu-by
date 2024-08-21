package github.alexzhirkevich.studentbsuby.util.communication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_NOT_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.suspendCancellableCoroutine


open class BroadcastReceiverCommunication<T>(
    private val context: Context,
    override val action: String,
    private val serializer: Serializer<T>
) : BroadcastCommunication<T>, Serializer<T> by serializer {

    @Suppress("UNCHECKED_CAST")
    override suspend fun collect(collector: suspend (T) -> Unit): Unit = supervisorScope {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.extras
                    ?.getBundle(extraKey)
                    ?.let(serializer::deserialize)
                    ?.let {
                        launch {
                            collector.invoke(it)
                        }
                    }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, IntentFilter(action), RECEIVER_NOT_EXPORTED)
        } else {
            context.registerReceiver(receiver, IntentFilter(action))

        }

        suspendCancellableCoroutine<T> {
            it.invokeOnCancellation {
                context.unregisterReceiver(receiver)
            }
        }
    }
}