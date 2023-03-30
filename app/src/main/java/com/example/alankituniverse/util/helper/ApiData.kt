package com.example.alankituniverse.util.helper

import android.content.Context
import com.example.alankituniverse.data.local.AppPreference
import com.example.alankituniverse.util.extns.toGsonJsonObject
import com.google.gson.JsonObject

class ApiData constructor(
    context: Context,
    private val appPreference: AppPreference
) {

    var deviceId = ""

    init {
        val testingDeviceId = "5e6acbe01acbc591"
        //  deviceId = testingDeviceId
        deviceId = AppUtil.getDeviceID(context)
    }

    // TODO: change the parameter 
    fun data(dataMap: HashMap<String, String>? = null): JsonObject {

        return if (dataMap != null) {

            dataMap["empId"] = appPreference.employeeID
            dataMap["password"] = appPreference.password
            dataMap["deviceid"] = deviceId
            dataMap.toGsonJsonObject()
        } else {
            hashMapOf(
                "loginid" to appPreference.employeeID,
                "password" to appPreference.password,
                "deviceid" to deviceId
            ).toGsonJsonObject()
        }
    }
}
