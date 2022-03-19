package github.alexzhirkevich.studentbsuby.util

import kotlinx.coroutines.CancellationException

suspend fun <T> suspendRunCatching(block : suspend () -> T) = runCatching{ block() }
    .onFailure { if (it is CancellationException) throw it }