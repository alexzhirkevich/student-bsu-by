package github.alexzhirkevich.studentbsuby.util.communication

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

interface Communication<T>{

    suspend fun collect(collector : suspend (T) -> Unit)
}

fun <T> Flow<T>.toCommunication() = object : Communication<T> {
    override suspend fun collect(collector: suspend (T) -> Unit) {
        this@toCommunication.collect {
            collector(it)
        }
    }
}