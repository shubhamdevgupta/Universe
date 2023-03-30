package com.example.alankituniverse.data.response

import android.os.Parcelable
import com.example.alankituniverse.data.model.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(val Status: Int, val Message: String, val Data: User) : Parcelable