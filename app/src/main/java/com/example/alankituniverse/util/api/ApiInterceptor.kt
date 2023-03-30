package com.example.alankituniverse.util.api

import com.example.alankituniverse.data.local.AppPreference
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class ApiInterceptor @Inject constructor(
    private val appPreference: AppPreference,
    private val networkConnection: NetworkConnection
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //Checking is network available
        networkInterceptor()

        //Header Interceptor
        //for Injecting authorization bearer token
        //at runtime  etc...
        val request = headerInterceptor(chain)

        //Intercepting response and checking errors
        val mainResponse: Response = chain.proceed(request)
        when (mainResponse.code) {
            401, 403 ->
                throw Exceptions.UnAuthorizedException("User UnAuthorized - token expired or may you have logged " +
                        "in another device. code ${mainResponse.code}")
            500 -> throw Exceptions.InternalServerError("Internal server error, it will resolve soon")
            else -> return mainResponse
        }
    }

    private fun headerInterceptor(chain: Interceptor.Chain): Request {
        var request = chain.request()
        request = request.newBuilder()
            .build()
        return request
    }

    private fun networkInterceptor() {
        if (!networkConnection.isInternetAvailable())
            throw Exceptions.NoInternetException("No Internet connection available")
    }
}




