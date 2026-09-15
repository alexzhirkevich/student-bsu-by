package github.alexzhirkevich.studentbsuby.util.communication

import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope


open class BroadcastReceiverCommunication<T>(
    override val action: String,
    private val serializer: Serializer<T>
) : BroadcastCommunication<T>, Serializer<T> by serializer {

    @Suppress("UNCHECKED_CAST")
    override suspend fun collect(collector: suspend (T) -> Unit): Unit = supervisorScope {
        BroadcastBus.events(action).collect { extras ->
            (extras[extraKey] as? Map<String, Any?>)
                ?.let(serializer::deserialize)
                ?.let {
                    launch {
                        collector.invoke(it)
                    }
                }
        }
    }
}
