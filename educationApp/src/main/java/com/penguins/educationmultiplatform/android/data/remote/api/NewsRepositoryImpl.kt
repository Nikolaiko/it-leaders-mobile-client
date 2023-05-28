package com.penguins.educationmultiplatform.android.data.remote.api

import com.penguins.educationmultiplatform.android.data.model.ActionResult
import com.penguins.educationmultiplatform.android.data.model.consts.HttpRoutes
import com.penguins.educationmultiplatform.android.data.model.dto.news.NewsListResponse
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.domain.remoteRepository.NewsRepository
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class NewsRepositoryImpl(private val client: HttpClient) : NewsRepository {

    override suspend fun getNewsByCategory(category: String): ActionResult<NewsListResponse, AppError> {
        return try {
            val response = client.get<NewsListResponse> {
                url(HttpRoutes.GET_NEWS)
                contentType(ContentType.Application.Json)
                this.parameter("category", category)
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

    private fun processInternalError(response: HttpResponse): ActionResult<NewsListResponse, AppError> {
        return when(response.status) {
            HttpStatusCode.InternalServerError -> ActionResult.Fail(AppError.InternalServerError)
            else -> ActionResult.Fail(AppError.UnknownResponse)
        }
    }

}
