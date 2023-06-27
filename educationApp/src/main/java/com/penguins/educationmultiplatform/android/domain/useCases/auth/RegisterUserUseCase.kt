package com.penguins.educationmultiplatform.android.domain.useCases.auth

import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.network.EducationRepository

class RegisterUserUseCase(
    private val networkRepository: EducationRepository
) {
    suspend fun invoke(registerData: RegisterRequest): AppActionResult<AuthResponse, AppError>
        = networkRepository.registerUser(registerData)
}