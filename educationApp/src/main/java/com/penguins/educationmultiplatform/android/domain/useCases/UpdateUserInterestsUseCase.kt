package com.penguins.educationmultiplatform.android.domain.useCases

import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.InterestCategoriesList
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.InterestCategory
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.LocalUserData
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.network.EducationRepository

class UpdateUserInterestsUseCase(
    private val networkService: EducationRepository,
    private val localUserDataRepository: LocalUserDataRepository
) {
    suspend fun invoke(interests: List<InterestCategory>): AppActionResult<LocalUserData, AppError> {
        val interestsList = InterestCategoriesList(interests = interests)
        return when (val tokens = localUserDataRepository.getTokens()) {
            null -> AppActionResult.Fail(AppError.UnauthorizedAccess)
            else -> networkService.updateUserInterests(tokens.accessToken, interestsList)
        }
    }
}