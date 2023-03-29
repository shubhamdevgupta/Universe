package com.example.alankituniverse.util.api

import java.io.IOException

object Exceptions {
    class ApiException(message: String) : IOException(message)
    class NoInternetException(message: String) : IOException(message)
    class UnAuthorizedException(message: String) : IOException(message)
    class InternalServerError(message: String) : IOException(message)

}