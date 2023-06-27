package com.services.network.api

import com.model.ActionResult
import com.model.consts.getNewsPath
import com.model.dto.news.NewsResponseListDTO
import com.model.network.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

internal class NewsApi(
    private val baseAddress: String,
    private val client: HttpClient
): BaseApi() {

    suspend fun getNewsByCategory(categoryName: String): ActionResult<NewsResponseListDTO, NetworkError> {
        return try {
            val response = client.get {
                url("$baseAddress/$getNewsPath")
                contentType(ContentType.Application.Json)
                parameter("category", categoryName)
            }.body<NewsResponseListDTO>()
            ActionResult.Success<NewsResponseListDTO>(response)
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