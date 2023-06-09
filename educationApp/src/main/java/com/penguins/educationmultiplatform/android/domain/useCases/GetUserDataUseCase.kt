package com.penguins.educationmultiplatform.android.domain.useCases

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.dto.profile.LocalUserData
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository

class GetUserDataUseCase constructor(
    private val localUserDataRepository: LocalUserDataRepository,
    private val networkLayer: EducationRepository
) {
    suspend fun invoke(): ActionResult<LocalUserData, AppError> {
        return when (val savedUserData = localUserDataRepository.getUserData()) {
            null -> loadUserDataFromServer()
            else -> ActionResult.Success(savedUserData)
        }
    }

    private suspend fun loadUserDataFromServer(): ActionResult<LocalUserData, AppError> {
        return when(val tokens = localUserDataRepository.getTokens()) {
            null -> ActionResult.Fail(AppError.UnauthorizedAccess)
            else -> networkLayer.getUserData(tokens.accessToken)
        }
    }
}