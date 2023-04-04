package com.example.alankituniverse.util.extns

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.alankituniverse.R
import com.example.alankituniverse.ui.activity.ErrorActivity
import com.example.alankituniverse.util.api.Exceptions
import java.net.SocketTimeoutException

fun Context.launchActivity(
    clazz: Class<*>, bundle: Bundle? = null, forgotAll: Boolean = false
) {
    val intent: Intent = Intent(this, clazz)
    bundle?.let {
        intent.putExtras(it)
    }
    if (forgotAll) intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    startActivity(intent)
}

fun Context.handleNetworkFailure(e: Exception, goBack: Boolean = true) {
    when (e) {
        is Exceptions.NoInternetException -> launchActivity(
            ErrorActivity::class.java, bundleOf(
                ErrorActivity.TYPE to ErrorActivity.NETWORK_EXCEPTION,
                ErrorActivity.TITLE to e.message,
                ErrorActivity.DESCRIPTION to resources.getString(R.string.no_internet_message),
                ErrorActivity.NetworkExceptions.NETWORK_EXCEPTION_TYPE to ErrorActivity.NetworkExceptions.NO_INTERNET
            )
        )
        is Exceptions.UnAuthorizedException -> launchActivity(
            ErrorActivity::class.java, bundleOf(
                ErrorActivity.TYPE to ErrorActivity.NETWORK_EXCEPTION,
                ErrorActivity.ACTION to ErrorActivity.NAVIGATE_TO_LOGIN,
                ErrorActivity.TITLE to "User Unauthorized",
                ErrorActivity.DESCRIPTION to e.message,
                ErrorActivity.NetworkExceptions.NETWORK_EXCEPTION_TYPE to ErrorActivity.NetworkExceptions.UNAUTHORIZED
            )
        )
        is Exceptions.InternalServerError -> launchActivity(
            ErrorActivity::class.java, bundleOf(
                ErrorActivity.TYPE to ErrorActivity.NETWORK_EXCEPTION,
                ErrorActivity.TITLE to "Internal Server Error",
                ErrorActivity.DESCRIPTION to e.message,
                ErrorActivity.NetworkExceptions.NETWORK_EXCEPTION_TYPE to ErrorActivity.NetworkExceptions.INTERNAL_SERVER_ERROR
            )
        )
        is SocketTimeoutException ->
            launchActivity(
                ErrorActivity::class.java, bundleOf(
                    ErrorActivity.TYPE to ErrorActivity.NETWORK_EXCEPTION,
                    ErrorActivity.TITLE to "Timeout",
                    ErrorActivity.DESCRIPTION to "Please try again!!",
                    ErrorActivity.NetworkExceptions.NETWORK_EXCEPTION_TYPE to ErrorActivity.NetworkExceptions.TIME_OUT_EXCEPTION
                )
            )
        else -> launchActivity(
            ErrorActivity::class.java, bundleOf(
                ErrorActivity.TYPE to ErrorActivity.NETWORK_EXCEPTION,
                ErrorActivity.TITLE to "Something went wrong! Please try again",
                ErrorActivity.DESCRIPTION to e.message,
                ErrorActivity.NetworkExceptions.NETWORK_EXCEPTION_TYPE to ErrorActivity.NetworkExceptions.OTHER
            )
        )
    }
}
