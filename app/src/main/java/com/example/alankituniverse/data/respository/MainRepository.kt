package com.example.alankituniverse.data.respository

import com.example.alankituniverse.data.service.MainService
import com.google.gson.JsonObject
import org.json.JSONObject
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainService: MainService) {
    suspend fun appLogin(data: JsonObject) = mainService.appLogin(data)
}