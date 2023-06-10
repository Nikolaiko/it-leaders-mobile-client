package com.penguins.educationmultiplatform.android.domain.useCases.auth

import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository

class LogoutUseCase(
    private val localUserDataRepository: LocalUserDataRepository
) {
    suspend fun invoke() {
        localUserDataRepository.logoutUser()
    }
}