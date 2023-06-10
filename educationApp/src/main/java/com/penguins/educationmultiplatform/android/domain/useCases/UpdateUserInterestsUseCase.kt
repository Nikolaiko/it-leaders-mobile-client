package com.penguins.educationmultiplatform.android.domain.useCases

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.dto.profile.InterestCategoriesList
import com.penguins.educationmultiplatform.android.data.model.dto.profile.InterestCategory
import com.penguins.educationmultiplatform.android.data.model.dto.profile.LocalUserData
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository

class UpdateUserInterestsUseCase(
    private val networkService: EducationRepository,
    private val localUserDataRepository: LocalUserDataRepository
) {
    suspend fun invoke(interests: List<InterestCategory>): ActionResult<LocalUserData, AppError> {
        val interestsList = InterestCategoriesList(interests = interests)
        return when (val tokens = localUserDataRepository.getTokens()) {
            null -> ActionResult.Fail(AppError.UnauthorizedAccess)
            else -> networkService.updateUserInterests(tokens.accessToken, interestsList)
        }
    }
}