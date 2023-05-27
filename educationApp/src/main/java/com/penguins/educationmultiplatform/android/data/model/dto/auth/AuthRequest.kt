package com.penguins.educationmultiplatform.android.data.model.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val email: String,
    val password: String
)
