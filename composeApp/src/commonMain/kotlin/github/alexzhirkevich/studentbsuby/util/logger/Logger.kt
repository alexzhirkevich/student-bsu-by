package github.alexzhirkevich.studentbsuby.util.logger

class TaggedException(
    tag : String,
    cause: Throwable?
) : Exception(tag,cause)

interface Logger {

    enum class LogLevel{
        Warning,
        Error,
    }

    fun log(
        msg : String,
        tag : String = this::class.simpleName.orEmpty(),
        logLevel: LogLevel = LogLevel.Warning,
        cause: Throwable?=null,
    )

    fun share()
}
