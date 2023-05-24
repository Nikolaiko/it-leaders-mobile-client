package com.penguins.educationmultiplatform.android.data.remote.api

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.consts.HttpRoutes
import com.penguins.educationmultiplatform.android.data.model.dto.AuthRequest
import com.penguins.educationmultiplatform.android.data.model.dto.AuthResponse
import com.penguins.educationmultiplatform.android.data.model.dto.RegisterRequest
import com.penguins.educationmultiplatform.android.data.model.dto.ServerErrorMessage
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.client.statement.readText
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class EducationRepositoryImpl(private val client: HttpClient) : EducationRepository {
    override suspend fun authUser(loginData: AuthRequest): ActionResult<AuthResponse, AppError> {
        return try {
            val response = client.post<AuthResponse> {
                url(HttpRoutes.LOGIN_EMAIL)
                contentType(ContentType.Application.Json)
                body = loginData
            }
            ActionResult.Success<AuthResponse>(response)
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

    private fun processInternalError(response: HttpResponse): ActionResult<AuthResponse, AppError> {
        return when(response.status) {
            HttpStatusCode.InternalServerError -> ActionResult.Fail(AppError.InternalServerError)
            else -> ActionResult.Fail(AppError.UnknownResponse)
        }
    }
}