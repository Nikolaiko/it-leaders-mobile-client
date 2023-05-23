package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.categoryScreen.viewModel.CategoryViewModel
import com.penguins.educationmultiplatform.android.newsScreen.viewModel.NewsViewModel
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.data.navigation.DestinationController
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
            expectSuccess = true
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    single { CategoryViewModel() }
    single <AppNavigation> { DestinationController() }
    single <NewsViewModel> { NewsViewModel() }
}