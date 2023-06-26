package com.penguins.educationmultiplatform.android.data.extensions.network

import com.model.dto.auth.AuthResponseDTO
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthResponse

fun AuthResponseDTO.toAuthResponse() = AuthResponse(
    accessToken = accessToken,
    refreshToken = refreshToken
)