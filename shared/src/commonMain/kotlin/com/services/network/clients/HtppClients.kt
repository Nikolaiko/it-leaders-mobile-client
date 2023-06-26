package com.services.network.clients

import com.model.consts.basicLogin
import com.model.consts.basicPassword
import com.model.consts.refreshTokenPath
import com.model.dto.auth.AuthResponseDTO
import com.model.dto.auth.RefreshRequestDTO
import com.services.storage.TokenStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val basicAuthClient = HttpClient {
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

fun buildBearerAuthClient(baseAddress: String, tokenStorage: TokenStorage) = HttpClient {
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
        bearer {
            loadTokens {
                val tokens = tokenStorage.getLastTokens()
                BearerTokens(
                    accessToken = tokens.first,
                    refreshToken = tokens.second
                )
            }
            refreshTokens {
                val tokensResponse = client.post {
                    url("$baseAddress/$refreshTokenPath")
                    contentType(ContentType.Application.Json)
                    setBody(RefreshRequestDTO(oldTokens?.refreshToken ?: ""))
                }.body<AuthResponseDTO>()
                tokenStorage.updateTokens(tokensResponse.accessToken, tokensResponse.refreshToken)
                val tokens = tokenStorage.getLastTokens()
                BearerTokens(
                    accessToken = tokens.first,
                    refreshToken = tokens.second
                )
            }
        }
    }
}