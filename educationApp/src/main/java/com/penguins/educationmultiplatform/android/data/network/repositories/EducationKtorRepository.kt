package com.penguins.educationmultiplatform.android.data.network.repositories

import com.model.ActionResult
import com.penguins.educationmultiplatform.android.data.extensions.network.toAppError
import com.penguins.educationmultiplatform.android.data.extensions.network.toAuthRequestDTO
import com.penguins.educationmultiplatform.android.data.extensions.network.toAuthResponse
import com.penguins.educationmultiplatform.android.data.extensions.network.toDTO
import com.penguins.educationmultiplatform.android.data.extensions.network.toInterestsCategoriesListDTO
import com.penguins.educationmultiplatform.android.data.extensions.network.toLocalUserData
import com.penguins.educationmultiplatform.android.data.extensions.network.toNewsListResponse
import com.penguins.educationmultiplatform.android.data.model.AppActionResult
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dataClasses.auth.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.dataClasses.news.NewsListResponse
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.InterestCategoriesList
import com.penguins.educationmultiplatform.android.data.model.dataClasses.profile.LocalUserData
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.network.EducationRepository
import com.penguins.educationmultiplatform.android.domain.network.NewsRepository
import com.services.network.KtorNetworkLayer
import com.services.storage.TokenStorage

class EducationKtorRepository(
    private val ktorLayer: KtorNetworkLayer
): EducationRepository {

    override suspend fun authUser(
        loginData: AuthRequest
    ): AppActionResult<AuthResponse, AppError> {
        val response = ktorLayer.authUser(authRequestDTO = loginData.toAuthRequestDTO())
        return when(response) {
            is ActionResult.Success -> AppActionResult.Success(response.result.toAuthResponse())
            is ActionResult.Fail -> AppActionResult.Fail(response.failure.toAppError())
        }
    }

    override suspend fun registerUser(
        registerData: RegisterRequest
    ): AppActionResult<AuthResponse, AppError> {
        val response = ktorLayer.registerUserViaEmail(registerData.toDTO())
        return when(response) {
            is ActionResult.Success -> AppActionResult.Success(response.result.toAuthResponse())
            is ActionResult.Fail -> AppActionResult.Fail(response.failure.toAppError())
        }
    }

    override suspend fun registerUserVK(registerData: RegisterRequest): AppActionResult<AuthResponse, AppError> {
        val response = ktorLayer.registerUserViaVK(registerData.toDTO())
        return when(response) {
            is ActionResult.Success -> AppActionResult.Success(response.result.toAuthResponse())
            is ActionResult.Fail -> AppActionResult.Fail(response.failure.toAppError())
        }
    }

    override suspend fun getUserData(token: String): AppActionResult<LocalUserData, AppError> {
        val response = ktorLayer.getUserData()
        return when(response) {
            is ActionResult.Success -> AppActionResult.Success(response.result.toLocalUserData())
            is ActionResult.Fail -> AppActionResult.Fail(response.failure.toAppError())
        }
    }

    override suspend fun updateUserInterests(
        token: String,
        interests: InterestCategoriesList
    ): AppActionResult<LocalUserData, AppError> {
        val response = ktorLayer.updateUserInterests(interests.toInterestsCategoriesListDTO())
        return when(response) {
            is ActionResult.Success -> AppActionResult.Success(response.result.toLocalUserData())
            is ActionResult.Fail -> AppActionResult.Fail(response.failure.toAppError())
        }
    }
}
