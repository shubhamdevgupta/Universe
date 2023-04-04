package com.example.alankituniverse.data.response

import com.google.gson.annotations.SerializedName

data class LeaveDataResponse(
    @SerializedName("respcode")
    val status: Int,
    @SerializedName("Msg")
    val message: String,
    @SerializedName("Department")
    val department: String,
    @SerializedName("UEmail")
    val userEmail: String,
    @SerializedName("HODEmail")
    val hodEmail: String,
    @SerializedName("BalanceCL")
    val casualLeave: String,
    @SerializedName("BalanceML")
    val medicalLeave: String,
    @SerializedName("BalanceEL")
    val earnedLeave: String,
    @SerializedName("Category")
    val category: String,  /// category means 1 for employee 2 for hod
    @SerializedName("MaxDaysL")
    val maxDaysL: String
/*
    @SerializedName("Category")
    val category: String,
    @SerializedName("Category")
    val category: String,
    @SerializedName("Category")
    val category: String,
*/


)