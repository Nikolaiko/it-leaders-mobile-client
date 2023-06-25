package com.penguins.educationmultiplatform.android.domain.useCases.auth

import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.network.EducationRepository

class LoginWithEmailUseCase(
    private val network: EducationRepository
) {
    suspend fun invoke(request: AuthRequest): AppActionResult<AuthResponse, AppError>
        = network.authUser(request)
}