package com.penguins.educationmultiplatform.android.domain.usecases

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.dto.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dto.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository

class LoginWithEmailUseCase(
    private val network: EducationRepository
) {
    suspend fun invoke(request: AuthRequest): ActionResult<AuthResponse, AppError>
        = network.authUser(request)
}