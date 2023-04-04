package com.example.alankituniverse.data.response

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.alankituniverse.util.helper.AppConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class GatePassResponse(
    @SerializedName("respcode")
    val status: Int,
    @SerializedName("Status")
    val msg: String,
    @SerializedName("gatepassStatus")
    val data: List<GatePass>
) : Parcelable

@Keep
@Parcelize
data class GatePass(
    @SerializedName("empCode")
    val empID: String = AppConstants.EMPTY,
    @SerializedName("name")
    val userName: String = AppConstants.EMPTY,
    @SerializedName("gatePassNo")
    val gatePassNo: String = AppConstants.EMPTY,
    @SerializedName("purpose")
    val purpose: String = AppConstants.EMPTY,
    @SerializedName("location")
    val location: String = AppConstants.EMPTY,
    @SerializedName("gatePassDate")
    val dateTime: String = AppConstants.EMPTY,
    @SerializedName("gpStatus")
    val gatePassStatus: String = AppConstants.EMPTY,
    @SerializedName("isCancelled")
    val isCancelled: Boolean = false
) : Parcelable