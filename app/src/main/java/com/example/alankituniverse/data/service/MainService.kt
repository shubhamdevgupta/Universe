package com.example.alankituniverse.data.service

import com.example.alankituniverse.data.response.LoginResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainService {
    // TODO:  login url will be here
    @POST("Login")
    suspend fun appLogin(@Body param: JsonObject): Response<LoginResponse>

}