package com.penguins.educationmultiplatform.android.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.LocationServices
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.CoursesViewModel
import com.penguins.educationmultiplatform.android.data.location.DefaultLocationTracker
import com.penguins.educationmultiplatform.android.data.navigation.DestinationController
import com.penguins.educationmultiplatform.android.domain.location.LocationTracker
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.useCases.GetSchoolsFromRepository
import com.penguins.educationmultiplatform.android.mapScreen.viewModel.YandexMapViewModel
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.viewModel.NewsListViewModel
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.viewModel.CategoryViewModel
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.viewModel.NewsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single  {
        HttpClient(Android) {
            expectSuccess = true
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    //viewModels
    viewModel { YandexMapViewModel(get(), get()) }
    viewModel { CategoryViewModel() }
    viewModel { NewsListViewModel() }
    viewModel { NewsViewModel() }
    viewModel {CoursesViewModel()}

    //location
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
    single <LocationTracker>{ DefaultLocationTracker(get(),androidContext()) }

    //useCases
    single { GetSchoolsFromRepository() }
    single <AppNavigation> { DestinationController() }
}