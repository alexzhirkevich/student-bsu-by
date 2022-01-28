package github.alexzhirkevich.studentbsuby.util

import androidx.compose.runtime.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface Updatable {

    val isUpdating : State<Boolean>

    fun update()
}

suspend fun setState(block : () -> Unit) = withContext(Dispatchers.Main){block()}