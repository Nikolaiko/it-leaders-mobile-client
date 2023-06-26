package com.penguins.educationmultiplatform.android.domain.network

import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.InterestCategoriesList
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.LocalUserData
import com.penguins.educationmultiplatform.android.data.model.error.AppError

interface EducationRepository {
    suspend fun authUser(loginData: AuthRequest): AppActionResult<AuthResponse, AppError>
    suspend fun registerUser(registerData: RegisterRequest): AppActionResult<AuthResponse, AppError>
    suspend fun registerUserVK(registerData: RegisterRequest): AppActionResult<AuthResponse, AppError>
    suspend fun getUserData(token: String): AppActionResult<LocalUserData, AppError>
    suspend fun updateUserInterests(token: String, interests: InterestCategoriesList): AppActionResult<LocalUserData, AppError>
}