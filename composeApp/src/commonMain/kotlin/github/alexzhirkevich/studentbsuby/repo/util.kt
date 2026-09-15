package github.alexzhirkevich.studentbsuby.repo

import github.alexzhirkevich.studentbsuby.api.isSessionExpired
import github.alexzhirkevich.studentbsuby.util.exceptions.EmptyResponseException
import github.alexzhirkevich.studentbsuby.util.exceptions.FailResponseException
import github.alexzhirkevich.studentbsuby.util.exceptions.SessionExpiredException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readRawBytes
import io.ktor.http.isSuccess
import kotlin.coroutines.cancellation.CancellationException

@Throws(
    FailResponseException::class,
    SessionExpiredException::class,
    EmptyResponseException::class,
    CancellationException::class
)
suspend fun HttpResponse.html() : String {
    if (!status.isSuccess())
        throw FailResponseException(status.value)

    val text = bodyAsText()

    if (text.isSessionExpired())
        throw SessionExpiredException()

    return text
}

@Throws(
    FailResponseException::class,
    SessionExpiredException::class,
    EmptyResponseException::class,
    CancellationException::class
)
suspend fun HttpResponse.bytes() : ByteArray{
    if (!status.isSuccess())
        throw FailResponseException(status.value)

    val text = bodyAsText()

    if (text.isSessionExpired())
        throw SessionExpiredException()

    return readRawBytes()
}
