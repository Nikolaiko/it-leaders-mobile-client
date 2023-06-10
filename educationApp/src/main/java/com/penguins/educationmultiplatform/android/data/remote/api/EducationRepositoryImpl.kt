package com.penguins.educationmultiplatform.android.data.remote.api

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.consts.HttpRoutes
import com.penguins.educationmultiplatform.android.data.model.dto.auth.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dto.auth.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dto.auth.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.dto.profile.InterestCategoriesList
import com.penguins.educationmultiplatform.android.data.model.dto.profile.LocalUserData
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*

class EducationRepositoryImpl(private val client: HttpClient) : EducationRepository {
    override suspend fun authUser(loginData: AuthRequest): ActionResult<AuthResponse, AppError> {
        return try {
            val response = client.post<AuthResponse> {
                url(HttpRoutes.LOGIN_EMAIL)
                contentType(ContentType.Application.Json)
                body = loginData
            }
            ActionResult.Success(response)
        } catch (e: RedirectResponseException) {
            ActionResult.Fail(AppError.UnknownResponse)
        } catch (e: ClientRequestException) {
            when(e.response.status) {
                HttpStatusCode.NotFound -> ActionResult.Fail(AppError.UserNotFound)
                else -> ActionResult.Fail(AppError.UnknownResponse)
            }
        } catch (e: ServerResponseException) {
            processInternalError(e.response)
        } catch (e: Exception) {
            ActionResult.Fail(AppError.UnknownResponse)
        }
    }

    override suspend fun registerUser(registerData: RegisterRequest): ActionResult<AuthResponse, AppError> {
        return try {
            val response = client.post<AuthResponse> {
                url(HttpRoutes.REGISTER)
                contentType(ContentType.Application.Json)
                body = registerData
            }
            ActionResult.Success(response)
        } catch (e: RedirectResponseException) {
            ActionResult.Fail(AppError.UnknownResponse)
        } catch (e: ClientRequestException) {
            when(e.response.status) {
                HttpStatusCode.Conflict -> ActionResult.Fail(AppError.UserAlreadyExist)
                else -> ActionResult.Fail(AppError.UnknownResponse)
            }
        } catch (e: ServerResponseException) {
            processInternalError(e.response)
        } catch (e: Exception) {
            ActionResult.Fail(AppError.UnknownResponse)
        }
    }

    override suspend fun registerUserVK(registerData: RegisterRequest): ActionResult<AuthResponse, AppError> {
        return try {
            val response = client.post<AuthResponse> {
                url(HttpRoutes.REGISTER_VK)
                contentType(ContentType.Application.Json)
                body = registerData
            }
            ActionResult.Success<AuthResponse>(response)
        } catch (e: RedirectResponseException) {
            ActionResult.Fail(AppError.UnknownResponse)
        } catch (e: ClientRequestException) {
            when(e.response.status) {
                HttpStatusCode.Conflict -> ActionResult.Fail(AppError.UserAlreadyExist)
                else -> ActionResult.Fail(AppError.UnknownResponse)
            }
        } catch (e: ServerResponseException) {
            processInternalError(e.response)
        } catch (e: Exception) {
            ActionResult.Fail(AppError.UnknownResponse)
        }
    }

    override suspend fun getUserData(token: String): ActionResult<LocalUserData, AppError> {
        return try {
            val response = client.get<LocalUserData> {
                url(HttpRoutes.GET_USER_DATA)
                contentType(ContentType.Application.Json)
                header(HttpRoutes.authHeaderName, "Bearer $token")
            }
            ActionResult.Success<LocalUserData>(response)
        } catch (e: RedirectResponseException) {
            ActionResult.Fail(AppError.UnknownResponse)
        } catch (e: ClientRequestException) {
            when(e.response.status) {
                HttpStatusCode.NotFound -> ActionResult.Fail(AppError.UserNotFound)
                HttpStatusCode.UnprocessableEntity -> ActionResult.Fail(AppError.WrongToken)
                else -> ActionResult.Fail(AppError.UnknownResponse)
            }
        } catch (e: ServerResponseException) {
            processInternalError(e.response)
        } catch (e: Exception) {
            ActionResult.Fail(AppError.UnknownResponse)
        }
    }

    override suspend fun updateUserInterests(
        token: String,
        interests: InterestCategoriesList
    ): ActionResult<LocalUserData, AppError> {
        return try {
            val response = client.post<LocalUserData> {
                url(HttpRoutes.UPDATE_USER_INTERESTS)
                contentType(ContentType.Application.Json)
                header(HttpRoutes.authHeaderName, "Bearer $token")
                body = interests
            }
            ActionResult.Success<LocalUserData>(response)
        } catch (e: RedirectResponseException) {
            ActionResult.Fail(AppError.UnknownResponse)
        } catch (e: ClientRequestException) {
            when(e.response.status) {
                HttpStatusCode.NotFound -> ActionResult.Fail(AppError.UserNotFound)
                HttpStatusCode.UnprocessableEntity -> ActionResult.Fail(AppError.WrongToken)
                else -> ActionResult.Fail(AppError.UnknownResponse)
            }
        } catch (e: ServerResponseException) {
            processInternalError(e.response)
        } catch (e: Exception) {
            ActionResult.Fail(AppError.UnknownResponse)
        }
    }

    private fun <T>processInternalError(response: HttpResponse): ActionResult<T, AppError> {
        return when(response.status) {
            HttpStatusCode.InternalServerError -> ActionResult.Fail(AppError.InternalServerError)
            else -> ActionResult.Fail(AppError.UnknownResponse)
        }
    }
}