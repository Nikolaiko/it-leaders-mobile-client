package com.penguins.educationmultiplatform.android.data.remote.api

import com.penguins.educationmultiplatform.android.data.remote.HttpRoutes
import com.penguins.educationmultiplatform.android.data.remote.dto.AuthRequest
import com.penguins.educationmultiplatform.android.data.remote.dto.AuthResponse
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class EducationRepositoryImpl(private val client: HttpClient) : EducationRepository {
    override suspend fun authUser(loginData: AuthRequest): AuthResponse? {
        return try {
            client.post<AuthResponse> {
                url(HttpRoutes.LOGIN)
                contentType(ContentType.Application.Json)
                body = loginData
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    override suspend fun registerUser(registerData: AuthRequest): AuthResponse? {
        return try {
            client.post<AuthResponse> {
                url(HttpRoutes.REGISTER)
                contentType(ContentType.Application.Json)
                body = registerData
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

}