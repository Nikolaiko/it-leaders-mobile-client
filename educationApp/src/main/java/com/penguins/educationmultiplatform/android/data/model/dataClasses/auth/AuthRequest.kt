package com.penguins.educationmultiplatform.android.data.model.dataClasses.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val email: String,
    val password: String
)
