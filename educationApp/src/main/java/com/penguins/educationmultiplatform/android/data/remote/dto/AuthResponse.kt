package com.penguins.educationmultiplatform.android.data.remote.dto

import kotlinx.serialization.Serializable
@Serializable
data class AuthResponse(
    val token:String,
    val refreshToken:String
)
