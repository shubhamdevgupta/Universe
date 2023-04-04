package com.example.alankituniverse.util.extns

import com.google.gson.Gson
import org.json.JSONObject

//toGsonJsonObject
fun HashMap<String, String>.toGsonJsonObject(): JSONObject {
    val gsonStringData = Gson().toJson(this).toString()
    val jsonObject = JSONObject(gsonStringData)
    // TODO: change the parameter here when send the request to the server

    return jsonObject
}