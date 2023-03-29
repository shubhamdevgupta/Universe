package com.example.alankituniverse.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alankituniverse.R

class ErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
    }

    companion object {
        const val TYPE: String = "type"
        const val TITLE: String = "title"
        const val DESCRIPTION: String = "description"
        const val ACTION: String = "action"

        const val NETWORK_EXCEPTION = "network_exception"
        const val NETWORK_EXCEPTION_TYPE = "network_exception_type"

        const val NAVIGATE_TO_lOGIN = "navigate_to_login"
    }

    class NetworkExceptions() {
        companion object {
            const val INTERNAL_SERVER_ERROR = ""
            const val TIME_OUT_EXCEPTION = ""
            const val UNAUTHORIZED = ""
            const val NO_INTERNET = ""
            const val OTHER = ""
        }
    }

}