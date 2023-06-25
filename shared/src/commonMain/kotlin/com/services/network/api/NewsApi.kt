package com.services.network.api

import com.model.ActionResult
import com.model.consts.basicLogin
import com.model.consts.basicPassword
import com.model.consts.getNewsPath
import com.model.consts.getUserDataPath
import com.model.dto.news.NewsResponseListDTO
import com.model.dto.user.UserDataDTO
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
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class NewsApi(
    private val baseAddress: String
): BaseApi() {
    private val client = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
        install(HttpTimeout)
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(
                        username = basicLogin,
                        password = basicPassword
                    )
                }
            }
        }
    }

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
                HttpStatusCode.UnprocessableEntity -> ActionResult.Fail(NetworkError.WrongToken)
                else -> ActionResult.Fail(NetworkError.UnknownResponse)
            }
        } catch (e: ServerResponseException) {
            processInternalError(e.response)
        } catch (e: Exception) {
            ActionResult.Fail(NetworkError.UnknownResponse)
        }
    }
}