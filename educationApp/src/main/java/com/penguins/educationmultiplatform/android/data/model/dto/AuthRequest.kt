package com.penguins.educationmultiplatform.android.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val email: String,
    val password: String
)
