package github.alexzhirkevich.studentbsuby.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.reflect.KProperty

sealed interface DataSource {
    object Local : DataSource
    object Remote : DataSource
    object All : DataSource
}

sealed interface Repository<out T> {
    fun get(
        dataSource: DataSource = DataSource.All,
        replaceCacheIf: (cached: T?, new: T) -> Boolean = { cached, new -> cached != new }
    ): Flow<T>
}

operator fun <T> Repository<T>.getValue(thisObj: Any?, property: KProperty<*>): Flow<T> {
    return get()
}

sealed class CacheWebRepository<T> : Repository<T>{
    override fun get(
        dataSource: DataSource,
        replaceCacheIf: (cached: T?, new: T) -> Boolean
    ) : Flow<T> = flow {
        val cached = getFromCache()?.also {
            if (dataSource is DataSource.All || dataSource is DataSource.Local) {
                emit(it)
            }
        }
        if (dataSource is DataSource.All || dataSource is DataSource.Remote) {
            getFromWeb()?.also {
                emit(it)
                if (replaceCacheIf(cached,it))
                    saveToCache(it)
            }
        }
    }

    abstract suspend fun getFromCache() : T?

    abstract suspend fun getFromWeb() : T?

    abstract suspend fun saveToCache(value : T)
}