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
import org.koin.android.ext.koin.androidContext

import com.penguins.educationmultiplatform.android.categoryScreen.viewModel.CategoryViewModel
import com.penguins.educationmultiplatform.android.newsScreen.viewModel.NewsViewModel
import com.penguins.educationmultiplatform.android.services.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.services.navigation.DestinationController
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
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
    single <NewsViewModel> { NewsViewModel() }
    single { CategoryViewModel() }

    //location
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single <LocationTracker>{ DefaultLocationTracker(get(),androidContext()) }

    //useCases
    single { GetSchoolsFromRepository() }
    single <AppNavigation> { DestinationController() }
}