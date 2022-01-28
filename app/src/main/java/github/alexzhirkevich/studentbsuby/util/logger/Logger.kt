package github.alexzhirkevich.studentbsuby.util.logger

interface Logger {

    enum class LogLevel{
        Warning,
        Error,
    }

    fun log(
        msg : String,
        tag : String = javaClass.simpleName,
        logLevel: LogLevel = LogLevel.Warning,
        cause: Throwable?=null,
    )
}