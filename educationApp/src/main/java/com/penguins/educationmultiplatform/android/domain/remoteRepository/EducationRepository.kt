package com.penguins.educationmultiplatform.android.domain.remoteRepository

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.dto.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dto.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dto.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.error.AppError

interface EducationRepository {
    suspend fun authUser(loginData: AuthRequest): ActionResult<AuthResponse, AppError>
    suspend fun registerUser(registerData: RegisterRequest): ActionResult<AuthResponse, AppError>
}