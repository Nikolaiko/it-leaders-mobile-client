package com.model.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequestDTO(
    val email: String,
    val password: String
)
