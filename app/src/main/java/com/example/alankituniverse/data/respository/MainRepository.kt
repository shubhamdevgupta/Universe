package com.example.alankituniverse.data.respository

import com.example.alankituniverse.data.service.GatePassData
import com.example.alankituniverse.data.service.LeaveData
import com.example.alankituniverse.data.service.LoginData
import com.example.alankituniverse.data.service.MainService
import javax.inject.Inject

class MainRepository @Inject constructor(private val mainService: MainService) {
    suspend fun appLogin(data: LoginData) = mainService.appLogin(data)
    suspend fun getLeaveData(data: LeaveData) = mainService.getLeaveData(data)
    suspend fun getGatePass(data: GatePassData) = mainService.getGatePassData(data)

}