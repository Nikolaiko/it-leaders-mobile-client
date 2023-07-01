package com.services.network.api

import com.model.ActionResult
import com.model.consts.basicLogin
import com.model.consts.basicPassword
import com.model.consts.emailLoginPath
import com.model.consts.emailRegisterPath
import com.model.consts.requestTimeOut
import com.model.consts.vkRegisterPath
import com.model.dto.auth.AuthRequestDTO
import com.model.dto.auth.AuthResponseDTO
import com.model.dto.auth.RegisterRequestDTO
import com.model.network.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.timeout
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class AuthApi(
    private val baseAddress: String,
    private val client: HttpClient
): BaseApi() {

    suspend fun loginUser(
        loginData: AuthRequestDTO
    ): ActionResult<AuthResponseDTO, NetworkError> {
        return try {
            val response = client.post {
                url("$baseAddress/$emailLoginPath")
                contentType(ContentType.Application.Json)
                setBody(loginData)
                timeout { requestTimeoutMillis = requestTimeOut }
            }.body<AuthResponseDTO>()
            ActionResult.Success(response)
        } catch (e: RedirectResponseException) {
            ActionResult.Fail(NetworkError.UnknownResponse)
        } catch (e: ClientRequestException) {
            when(e.response.status) {
                HttpStatusCode.NotFound -> ActionResult.Fail(NetworkError.UserNotFound)
                HttpStatusCode.UnprocessableEntity -> ActionResult.Fail(NetworkError.UnprocessableEntry)
                else -> ActionResult.Fail(NetworkError.UnknownResponse)
            }
        } catch (e: ServerResponseException) {
            processInternalError(e.response)
        } catch (e: Exception) {
            ActionResult.Fail(NetworkError.UnknownResponse)
        }
    }

    suspend fun registerUserByEmail(
        registerData: RegisterRequestDTO
    ): ActionResult<AuthResponseDTO, NetworkError> {
        return try {
            val response = client.post {
                url("$baseAddress/$emailRegisterPath")
                contentType(ContentType.Application.Json)
                setBody(registerData)
                timeout { requestTimeoutMillis = requestTimeOut }
            }.body<AuthResponseDTO>()
            ActionResult.Success(response)
        } catch (e: RedirectResponseException) {
            ActionResult.Fail(NetworkError.UnknownResponse)
        } catch (e: ClientRequestException) {
            when(e.response.status) {
                HttpStatusCode.Conflict -> ActionResult.Fail(NetworkError.UserAlreadyExist)
                HttpStatusCode.UnprocessableEntity -> ActionResult.Fail(NetworkError.UnprocessableEntry)
                else -> ActionResult.Fail(NetworkError.UnknownResponse)
            }
        } catch (e: ServerResponseException) {
            processInternalError(e.response)
        } catch (e: Exception) {
            ActionResult.Fail(NetworkError.UnknownResponse)
        }
    }

    suspend fun registerUserVK(
        registerData: RegisterRequestDTO
    ): ActionResult<AuthResponseDTO, NetworkError> {
        return try {
            val response = client.post {
                url("$baseAddress/$vkRegisterPath")
                contentType(ContentType.Application.Json)
                setBody(registerData)
                timeout { requestTimeoutMillis = requestTimeOut }
            }.body<AuthResponseDTO>()
            ActionResult.Success<AuthResponseDTO>(response)
        } catch (e: RedirectResponseException) {
            ActionResult.Fail(NetworkError.UnknownResponse)
        } catch (e: ClientRequestException) {
            when(e.response.status) {
                HttpStatusCode.Conflict -> ActionResult.Fail(NetworkError.UserAlreadyExist)
                HttpStatusCode.UnprocessableEntity -> ActionResult.Fail(NetworkError.UnprocessableEntry)
                else -> ActionResult.Fail(NetworkError.UnknownResponse)
            }
        } catch (e: ServerResponseException) {
            processInternalError(e.response)
        } catch (e: Exception) {
            ActionResult.Fail(NetworkError.UnknownResponse)
        }
    }
}