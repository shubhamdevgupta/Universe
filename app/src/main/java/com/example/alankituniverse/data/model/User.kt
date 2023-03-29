package com.example.alankituniverse.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.example.alankituniverse.util.helper.AppConstants
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
// TODO: use parcelize annotation for their correct name 
data class User(
    val userId: Int = 0,
    val name: String = AppConstants.EMPTY,
    val profilePic: String = AppConstants.EMPTY,
    val eLeave: String = AppConstants.EMPTY,
    val mLeave: String = AppConstants.EMPTY,
    val cLeave: String = AppConstants.EMPTY
) : Parcelable