package com.penguins.educationmultiplatform.android.data.extensions.network

import com.model.dto.auth.RegisterRequestDTO
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.RegisterRequest

fun RegisterRequest.toDTO() = RegisterRequestDTO(
    email = email,
    password = password,
    vkToken = vkToken,
    birthDate = birthDate,
    name = name
)