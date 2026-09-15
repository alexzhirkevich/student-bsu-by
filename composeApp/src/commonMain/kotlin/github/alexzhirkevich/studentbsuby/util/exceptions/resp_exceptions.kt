package github.alexzhirkevich.studentbsuby.util.exceptions

import kotlinx.io.IOException


open class NetworkResponseException : IOException()

class FailResponseException(val code : Int) : NetworkResponseException()

class EmptyResponseException : NetworkResponseException()

class IncorrectResponseException : NetworkResponseException()

class SessionExpiredException : NetworkResponseException()
