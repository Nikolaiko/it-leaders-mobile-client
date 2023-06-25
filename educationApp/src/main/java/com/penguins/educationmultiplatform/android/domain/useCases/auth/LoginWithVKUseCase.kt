package com.penguins.educationmultiplatform.android.domain.useCases.auth

import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.VKProfile
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.network.EducationRepository

class LoginWithVKUseCase(
    private val network: EducationRepository
) {
    suspend fun invoke(
        token: String,
        email: String,
        profile: VKProfile
    ): AppActionResult<AuthResponse, AppError> {
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