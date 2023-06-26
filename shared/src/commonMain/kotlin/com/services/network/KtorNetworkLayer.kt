package com.services.network

import com.model.ActionResult
import com.model.dto.auth.AuthRequestDTO
import com.model.dto.auth.AuthResponseDTO
import com.model.dto.auth.RegisterRequestDTO
import com.model.dto.interests.InterestsListDTO
import com.model.dto.news.NewsResponseListDTO
import com.model.dto.user.UserDataDTO
import com.model.network.NetworkError
import com.services.network.api.AuthApi
import com.services.network.api.NewsApi
import com.services.network.api.UserApi
import com.services.network.clients.basicAuthClient
import com.services.network.clients.buildBearerAuthClient
import com.services.storage.TokenStorage

class KtorNetworkLayer(
    baseAddress: String,
    private val tokenStorage: TokenStorage
) {
    private val authApi = AuthApi(baseAddress, basicAuthClient)
    private val userApi = UserApi(baseAddress, buildBearerAuthClient(baseAddress, tokenStorage))
    private val newsApi = NewsApi(baseAddress, basicAuthClient)

    suspend fun authUser(
        authRequestDTO: AuthRequestDTO
    ): ActionResult<AuthResponseDTO, NetworkError> {
        return when(val result = authApi.loginUser(authRequestDTO)) {
            is ActionResult.Success -> {
                tokenStorage.updateTokens(
                    accessToken = result.result.accessToken,
                    refreshToken = result.result.refreshToken
                )
                result
            }
            else -> result
        }
    }

    suspend fun registerUserViaVK(
        registerRequestDTO: RegisterRequestDTO
    ): ActionResult<AuthResponseDTO, NetworkError> {
        return when(val result = authApi.registerUserVK(registerRequestDTO)) {
            is ActionResult.Success -> {
                tokenStorage.updateTokens(
                    accessToken = result.result.accessToken,
                    refreshToken = result.result.refreshToken
                )
                result
            }
            else -> result
        }
    }

    suspend fun registerUserViaEmail(
        registerRequestDTO: RegisterRequestDTO
    ): ActionResult<AuthResponseDTO, NetworkError> {
        return when(val result = authApi.registerUserByEmail(registerRequestDTO)) {
            is ActionResult.Success -> {
                tokenStorage.updateTokens(
                    accessToken = result.result.accessToken,
                    refreshToken = result.result.refreshToken
                )
                result
            }
            else -> result
        }
    }

    suspend fun getUserData(): ActionResult<UserDataDTO, NetworkError> {
        return userApi.getUserData()
    }

    suspend fun updateUserInterests(
        interestsListDTO: InterestsListDTO
    ): ActionResult<UserDataDTO, NetworkError> {
        return userApi.updateUserInterests(interestsListDTO)
    }

    suspend fun getNewsByCategory(
        categoryName: String
    ): ActionResult<NewsResponseListDTO, NetworkError> {
        return newsApi.getNewsByCategory(categoryName)
    }
}