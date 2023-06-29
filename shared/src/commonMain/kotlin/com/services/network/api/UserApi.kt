package com.services.network.api

import com.model.ActionResult
import com.model.consts.getUserDataPath
import com.model.consts.updateUserInterestsPath
import com.model.dto.interests.InterestsListDTO
import com.model.dto.user.UserDataDTO
import com.model.network.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

internal class UserApi(
    private val baseAddress: String,
    private val client: HttpClient
): BaseApi() {

    suspend fun getUserData(): ActionResult<UserDataDTO, NetworkError> {
        return try {
            val response = client.get {
                url("$baseAddress/$getUserDataPath")
                contentType(ContentType.Application.Json)
            }.body<UserDataDTO>()
            ActionResult.Success<UserDataDTO>(response)
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

    suspend fun updateUserInterests(
        interests: InterestsListDTO
    ): ActionResult<UserDataDTO, NetworkError> {
        return try {
            val response = client.post {
                url("$baseAddress/$updateUserInterestsPath")
                contentType(ContentType.Application.Json)
                setBody(interests)
            }.body<UserDataDTO>()
            ActionResult.Success<UserDataDTO>(response)
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
}