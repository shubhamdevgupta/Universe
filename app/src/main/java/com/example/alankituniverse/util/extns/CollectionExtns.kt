package com.example.alankituniverse.util.extns

import com.google.gson.Gson
import com.google.gson.JsonObject

//toGsonJsonObject
fun HashMap<String, String>.toGsonJsonObject(): JsonObject {
    val gsonStringData = Gson().toJson(this).toString()
    val jsonObject = JsonObject()
    // TODO: change the parameter here when send the request to the server 
    jsonObject.addProperty("anderpata", gsonStringData)
    return jsonObject
}