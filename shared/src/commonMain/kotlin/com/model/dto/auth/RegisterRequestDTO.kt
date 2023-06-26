package com.model.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDTO(
    val email: String,
    val password: String?,
    val name: String,
    val vkToken: String?,
    val birthDate: String?
)
