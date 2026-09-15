package github.alexzhirkevich.studentbsuby.util.communication

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

interface Broadcast<T> : Serializer<T> {
    val action: String

    val extraKey: String get() = action + "_value"

    companion object {
    }
}

interface Serializer<T> {
    fun serialize(value : T): Map<String, Any?>

    fun deserialize(bundle : Map<String, Any?>) : T
}

@Suppress("UNCHECKED_CAST")
class PrimitiveSerializer<T> : Serializer<T> {
    override fun serialize(value: T): Map<String, Any?> {
        return mapOf("value" to value)
    }

    override fun deserialize(bundle: Map<String, Any?>): T {
        return bundle["value"] as T
    }

}

internal object BroadcastBus {

    private val flow = MutableSharedFlow<Pair<String, Map<String, Any?>>>(
        extraBufferCapacity = 64,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun send(action: String, extras: Map<String, Any?>) {
        flow.tryEmit(action to extras)
    }

    fun events(action: String): Flow<Map<String, Any?>> = flow
        .filter { it.first == action }
        .map { it.second }
}
