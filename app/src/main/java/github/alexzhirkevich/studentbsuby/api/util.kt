package github.alexzhirkevich.studentbsuby.api

import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

typealias FormUrlEncodedBody = Map<String,String>

suspend fun Call.await() = suspendCoroutine<Response> {
    enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            it.resumeWithException(e)
        }

        override fun onResponse(call: Call, response: Response) {
            it.resume(response)
        }
    })
}