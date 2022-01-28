package github.alexzhirkevich.studentbsuby.util

import android.service.autofill.Dataset
import androidx.annotation.StringRes

sealed interface DataState<out T> {

    object Empty : DataState<Nothing>

    object Loading : DataState<Nothing>

    data class Success<out T>(val value : T) : DataState<T>

    data class Error(@StringRes val message : Int, val error : Throwable? = null)
        : DataState<Nothing>
}

fun <T> DataState<T>.valueOrNull() = (this as? DataState.Success)?.value