package github.alexzhirkevich.studentbsuby.util.exceptions

import okio.IOException

open class NetworkResponseException : IOException()
class FailResponseException : NetworkResponseException()
class EmptyResponseException : NetworkResponseException()
class IncorrectResponseException : NetworkResponseException()