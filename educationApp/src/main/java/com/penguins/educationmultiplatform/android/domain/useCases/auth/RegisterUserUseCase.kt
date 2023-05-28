package com.penguins.educationmultiplatform.android.domain.usecases.auth

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.dto.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dto.auth.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository

class RegisterUserUseCase(
    private val networkRepository: EducationRepository
) {
    suspend fun invoke(registerData: RegisterRequest): ActionResult<AuthResponse, AppError>
        = networkRepository.registerUser(registerData)
}