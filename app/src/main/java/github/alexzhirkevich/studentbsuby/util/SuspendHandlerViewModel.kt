package github.alexzhirkevich.studentbsuby.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

open class SuspendHandlerViewModel<T : Event>(
    private val dispatchers: Dispatchers,
    private val suspendEventHandler: SuspendEventHandler<T>,
    private val errorHandler: ErrorHandler
) : ViewModel(), EventHandler<T> {

    init {
        dispatchers.launchIO(
            viewModelScope,
            exceptionHandler = errorHandler.toCoroutineExceptionHandler(),
        ) {
            suspendEventHandler.launch()
        }
    }

    final override fun handle(event: T) {
        dispatchers.launchIO(
            viewModelScope,
            exceptionHandler = errorHandler.toCoroutineExceptionHandler()
        ) {
            suspendEventHandler.handle(event)
        }
    }

    override fun onCleared() {
        super.onCleared()
        suspendEventHandler.release()
    }
}