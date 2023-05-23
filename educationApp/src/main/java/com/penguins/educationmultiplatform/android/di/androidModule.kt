package com.penguins.educationmultiplatform.android.di

import com.google.android.gms.location.LocationServices
import com.penguins.educationmultiplatform.android.authScreen.viewModel.AuthViewModel
import com.penguins.educationmultiplatform.android.authScreen.viewModel.RegisterViewModel
import com.penguins.educationmultiplatform.android.data.location.DefaultLocationTracker
import com.penguins.educationmultiplatform.android.data.remote.api.EducationRepositoryImpl
import com.penguins.educationmultiplatform.android.domain.location.LocationTracker
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository
import com.penguins.educationmultiplatform.android.domain.useCases.GetSchoolsFromRepository
import com.penguins.educationmultiplatform.android.mapScreen.viewModel.YandexMapViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.android.ext.koin.androidContext

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

    //viewModels
    single { AuthViewModel() }
    single { RegisterViewModel() }
    single { YandexMapViewModel(get(), get()) }

    //location
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single <LocationTracker>{ DefaultLocationTracker(get(),androidContext()) }

    //useCases
    single { GetSchoolsFromRepository() }
}