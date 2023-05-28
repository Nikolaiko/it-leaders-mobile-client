package com.penguins.educationmultiplatform.android.domain.usecases.auth

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.dto.auth.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dto.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository

class LoginWithEmailUseCase(
    private val network: EducationRepository
) {
    suspend fun invoke(request: AuthRequest): ActionResult<AuthResponse, AppError>
        = network.authUser(request)
}