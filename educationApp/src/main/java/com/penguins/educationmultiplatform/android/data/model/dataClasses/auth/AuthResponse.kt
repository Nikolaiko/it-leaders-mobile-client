package com.penguins.educationmultiplatform.android.data.model.dataClasses.auth

import kotlinx.serialization.Serializable
@Serializable
data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)
