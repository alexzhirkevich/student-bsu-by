package github.alexzhirkevich.studentbsuby.util.communication

class BroadcastReceiverMapper<T> (
    override val action: String,
    private val serializer: Serializer<T>

)  : BroadcastMapper<T>, Serializer<T> by serializer {
    override fun map(data: T) {
        BroadcastBus.send(action, mapOf(extraKey to serialize(data)))
    }
}
