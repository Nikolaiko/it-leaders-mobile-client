package com.model.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDTO(
    val accessToken: String,
    val refreshToken: String
)
