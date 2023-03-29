package com.example.alankituniverse.data.local

import android.content.SharedPreferences
import com.example.alankituniverse.data.model.User
import com.example.alankituniverse.util.helper.AppConstants
import com.google.gson.Gson
import javax.inject.Inject


class AppPreference @Inject constructor(private val sharedPreferences: SharedPreferences) {
    var deviceId: String
        get() = getString(DEVICE_ID)
        set(value) = setString(value, DEVICE_ID)
    var employeeID: String
        get() = getString(EMPLOYEE_ID)
        set(value) = setString(value, EMPLOYEE_ID)
    var password: String
        get() = getString(PASSWORD)
        set(value) = setString(value, PASSWORD)

    var user: User
        get() {
            val strUser = getString(USER)
            return Gson().fromJson(strUser, User::class.java)
        }
        set(value) {
            val strUser = Gson().toJson(value).toString()
            setString(strUser, USER)
        }


    private fun setString(value: String, tag: String) =
        sharedPreferences.edit().putString(tag, value).apply()

    private fun getString(tag: String) = sharedPreferences.getString(tag, AppConstants.EMPTY) ?: ""


    companion object {

        const val USER = "user"
        const val DEVICE_ID = "deviceId"
        const val EMPLOYEE_ID = "employee_ID"
        const val PASSWORD = "password"
    }


}