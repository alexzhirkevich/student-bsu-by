package github.alexzhirkevich.studentbsuby.util.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal actual val IoDispatcher: CoroutineDispatcher = Dispatchers.IO
