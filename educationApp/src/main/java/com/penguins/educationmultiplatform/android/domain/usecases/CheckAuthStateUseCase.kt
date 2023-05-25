package com.penguins.educationmultiplatform.android.domain.useCases

import com.penguins.educationmultiplatform.android.data.model.auth.AuthStatus
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository

class CheckAuthStateUseCase(
    private val localStorage: LocalUserDataRepository
) {
    fun invoke() = AuthStatus(
        skippedAuth = localStorage.getSkippedAuthorization(),
        tokens = localStorage.getTokens()
    )
}