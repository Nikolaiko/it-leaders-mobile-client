package com.penguins.educationmultiplatform.android.domain.useCases.auth

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.dto.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dto.auth.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.dto.profile.VKProfile
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository

class LoginWithVKUseCase(
    private val network: EducationRepository
) {
    suspend fun invoke(
        token: String,
        email: String,
        profile: VKProfile
    ): ActionResult<AuthResponse, AppError> {
        return network.registerUserVK(
            RegisterRequest(
                email = email,
                name = profile.response.first_name,
                vkToken = token,
                password = token,
                birthDate = profile.formatBirthdate()
            )
        )
    }
}