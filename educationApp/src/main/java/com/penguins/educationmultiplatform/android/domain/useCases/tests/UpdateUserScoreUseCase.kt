package com.penguins.educationmultiplatform.android.domain.useCases.tests

import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository

class UpdateUserScoreUseCase constructor(
    private val localUserDataRepository: LocalUserDataRepository
) {
    fun invoke(addScoreValue: Int): Int {
        val oldScore = localUserDataRepository.getUserScore()
        val newScore = oldScore + addScoreValue
        localUserDataRepository.saveUserScore(newScore)
        return newScore
    }
}