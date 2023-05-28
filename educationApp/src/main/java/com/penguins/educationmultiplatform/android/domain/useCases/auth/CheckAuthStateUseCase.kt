package com.penguins.educationmultiplatform.android.domain.usecases.auth

import com.penguins.educationmultiplatform.android.authScreen.data.AuthStatus
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository

class CheckAuthStateUseCase(
    private val localStorage: LocalUserDataRepository
) {
    fun invoke() = AuthStatus(
        skippedAuth = localStorage.getSkippedAuthorization(),
        tokens = localStorage.getTokens()
    )
}