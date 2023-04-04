package com.example.alankituniverse.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.alankituniverse.util.helper.AppConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
// TODO: use parcelize annotation for their correct name 
data class User(
    @SerializedName("SSN")
    val empID: String = AppConstants.EMPTY,
    @SerializedName("NAME")
    val userName: String = AppConstants.EMPTY,
    @SerializedName("SalMsg")
    val loginMessage: String = AppConstants.EMPTY,
    @SerializedName("LastLogin")
    val lastLogin: String = AppConstants.EMPTY
) : Parcelable