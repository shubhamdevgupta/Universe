package com.example.alankituniverse.data.response

import android.os.Parcelable
import com.example.alankituniverse.data.model.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(val status: Int, val message: String, val user: User) : Parcelable