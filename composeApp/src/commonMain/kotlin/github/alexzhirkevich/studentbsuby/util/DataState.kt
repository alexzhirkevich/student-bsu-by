package github.alexzhirkevich.studentbsuby.util

import org.jetbrains.compose.resources.StringResource

sealed interface DataState<out T> {

    object Empty : DataState<Nothing>

    object Loading : DataState<Nothing>

    data class Success<out T>(val value : T) : DataState<T>

    data class Error(val message : StringResource, val error : Throwable? = null)
        : DataState<Nothing>
}

fun <T> DataState<T>.valueOrNull() = (this as? DataState.Success)?.value
