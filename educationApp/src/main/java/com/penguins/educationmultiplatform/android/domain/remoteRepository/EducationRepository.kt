package com.penguins.educationmultiplatform.android.domain.remoteRepository

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.dto.auth.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dto.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dto.auth.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.dto.profile.LocalUserData
import com.penguins.educationmultiplatform.android.data.model.error.AppError

interface EducationRepository {
    suspend fun authUser(loginData: AuthRequest): ActionResult<AuthResponse, AppError>
    suspend fun registerUser(registerData: RegisterRequest): ActionResult<AuthResponse, AppError>
    suspend fun registerUserVK(registerData: RegisterRequest): ActionResult<AuthResponse, AppError>
    suspend fun getUserData(token: String): ActionResult<LocalUserData, AppError>
}