package com.penguins.educationmultiplatform.android.domain.useCases

import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.LocalUserData
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.network.EducationRepository

class GetUserDataUseCase constructor(
    private val localUserDataRepository: LocalUserDataRepository,
    private val networkLayer: EducationRepository
) {
    suspend fun invoke(): AppActionResult<LocalUserData, AppError> {
        return when (val savedUserData = localUserDataRepository.getUserData()) {
            null -> loadUserDataFromServer()
            else -> AppActionResult.Success(savedUserData)
        }
    }

    private suspend fun loadUserDataFromServer(): AppActionResult<LocalUserData, AppError> {
        return when(val tokens = localUserDataRepository.getTokens()) {
            null -> AppActionResult.Fail(AppError.UnauthorizedAccess)
            else -> networkLayer.getUserData(tokens.accessToken)
        }
    }
}