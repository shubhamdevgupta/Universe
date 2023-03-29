package com.example.alankituniverse.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.alankituniverse.util.api.Exceptions
import org.json.JSONObject
import retrofit2.Response

open class BaseViewModel : ViewModel() {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody().toString()
            val message = StringBuilder()
            try {
                message.append(JSONObject(error).getString("message"))
            } catch (e: Exception) {

            }
            message.append("Error Code: ${response.code()}")
            throw Exceptions.ApiException(message.toString())
        }
    }
}