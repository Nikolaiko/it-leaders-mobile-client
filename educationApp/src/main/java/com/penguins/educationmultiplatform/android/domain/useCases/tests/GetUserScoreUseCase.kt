package com.penguins.educationmultiplatform.android.domain.useCases.tests

import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository

class GetUserScoreUseCase constructor(
    private val localUserDataRepository: LocalUserDataRepository
) {
    fun invoke() = localUserDataRepository.getUserScore()
}