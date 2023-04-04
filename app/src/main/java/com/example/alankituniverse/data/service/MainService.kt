package com.example.alankituniverse.data.service

import com.example.alankituniverse.data.response.GatePassResponse
import com.example.alankituniverse.data.response.LeaveDataResponse
import com.example.alankituniverse.data.response.LoginResponse
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainService {
    // TODO:  login url will be here
    @POST("api/login/Checklogin")
    suspend fun appLogin(@Body req: LoginData): Response<LoginResponse>

    @POST("api/Leavedetail/Leavecheck")
    suspend fun getLeaveData(@Body request: LeaveData): Response<LeaveDataResponse>

    @POST("api/Gatepass/GatepassCheck")
    suspend fun getGatePassData(@Body request: GatePassData): Response<GatePassResponse>

}

class LoginData internal constructor(
    @SerializedName("Ssn")
    val empId: String,
    @SerializedName("Pass")
    val pass: String
)

class GatePassData internal constructor(
    @SerializedName("Ssn")
    val empId: String,
    @SerializedName("Date")
    val date: String
)

class LeaveData internal constructor(
    @SerializedName("Ssn")
    val empId: String,
    @SerializedName("NAME")
    val name: String
)



