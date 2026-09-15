package github.alexzhirkevich.studentbsuby.services.firebase

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal suspend fun <T> Task<T>.await() : T = suspendCancellableCoroutine { cont ->
    addOnCanceledListener {
        cont.cancel()
    }.addOnSuccessListener {
        if (it != null)
            cont.resume(it)
        else cont.cancel()
    }.addOnFailureListener {
        cont.resumeWithException(it)
    }
}
