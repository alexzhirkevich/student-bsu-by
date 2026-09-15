package github.alexzhirkevich.studentbsuby.util.dispatchers

import platform.Foundation.NSRecursiveLock

internal actual fun <K, V> concurrentMutableMap(): MutableMap<K, V> = LockedMutableMap(HashMap())

private class LockedMutableMap<K, V>(
    private val delegate: MutableMap<K, V>
) : MutableMap<K, V> {

    private val lock = NSRecursiveLock()

    private inline fun <T> locked(block: () -> T): T {
        lock.lock()
        try {
            return block()
        } finally {
            lock.unlock()
        }
    }

    override val size: Int get() = locked { delegate.size }

    override fun isEmpty(): Boolean = locked { delegate.isEmpty() }

    override fun containsKey(key: K): Boolean = locked { delegate.containsKey(key) }

    override fun containsValue(value: V): Boolean = locked { delegate.containsValue(value) }

    override fun get(key: K): V? = locked { delegate[key] }

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = locked { HashMap(delegate).entries }

    override val keys: MutableSet<K>
        get() = locked { HashMap(delegate).keys }

    override val values: MutableCollection<V>
        get() = locked { HashMap(delegate).values }

    override fun clear() = locked { delegate.clear() }

    override fun put(key: K, value: V): V? = locked { delegate.put(key, value) }

    override fun putAll(from: Map<out K, V>) = locked { delegate.putAll(from) }

    override fun remove(key: K): V? = locked { delegate.remove(key) }
}
