package github.alexzhirkevich.studentbsuby.util.dispatchers

internal expect fun <K, V> concurrentMutableMap(): MutableMap<K, V>
