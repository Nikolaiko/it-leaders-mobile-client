package com.penguins.educationmultiplatform.android.data.extensions.network

import com.model.dto.auth.AuthRequestDTO
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthRequest

fun AuthRequest.toAuthRequestDTO() = AuthRequestDTO(
    email = email,
    password = password
)
