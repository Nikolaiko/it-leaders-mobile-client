package com.penguins.educationmultiplatform.android.domain.remoteRepository

import com.penguins.educationmultiplatform.android.data.remote.dto.AuthRequest
import com.penguins.educationmultiplatform.android.data.remote.dto.AuthResponse

interface EducationRepository {
    suspend fun authUser(loginData: AuthRequest): AuthResponse?
    suspend fun registerUser(registerData: AuthRequest): AuthResponse?
}