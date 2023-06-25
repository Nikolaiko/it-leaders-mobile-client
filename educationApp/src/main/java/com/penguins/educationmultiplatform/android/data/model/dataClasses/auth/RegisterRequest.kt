package com.penguins.educationmultiplatform.android.data.model.dataClasses.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String?,
    val name: String,
    val vkToken: String?,
    val birthDate: String?
)
