package com.example.alankituniverse.data.respository

import com.example.alankituniverse.data.response.LoginResponse
import com.example.alankituniverse.data.service.MainService
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainService: MainService) {
    suspend fun appLogin(data: JsonObject) = mainService.appLogin(data)
}