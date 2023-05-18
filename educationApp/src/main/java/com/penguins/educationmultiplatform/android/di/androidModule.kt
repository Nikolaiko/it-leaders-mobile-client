package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.data.remote.api.EducationRepositoryImpl
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

import org.koin.dsl.module

val androidModule = module {
    single  {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }
    single <EducationRepository> { EducationRepositoryImpl(get()) }
}