package com.penguins.educationmultiplatform.android.domain.useCases

import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.LocalUserData
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository

class SaveUserDataUseCase constructor(
    private val localUserDataRepository: LocalUserDataRepository
) {
    fun invoke(userData: LocalUserData) {
        localUserDataRepository.saveUserData(userData)
    }
}