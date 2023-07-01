package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.data.localUserDataRepository.SharedPreferencesRepository
import com.penguins.educationmultiplatform.android.data.model.consts.HttpRoutes
import com.penguins.educationmultiplatform.android.data.network.repositories.EducationKtorRepository
import com.penguins.educationmultiplatform.android.data.network.repositories.NewsKtorRepository
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.network.EducationRepository
import com.penguins.educationmultiplatform.android.domain.network.NewsRepository
import com.services.network.KtorNetworkLayer
import com.services.storage.TokenStorage
import org.koin.dsl.binds
import org.koin.dsl.module

val repositoriesModule = module {

    single { KtorNetworkLayer(baseAddress = HttpRoutes.BASE_URL, tokenStorage = get()) }


    single { SharedPreferencesRepository(get()) }
        .binds(
            listOf(
                LocalUserDataRepository::class, TokenStorage::class
            ).toTypedArray()
        )

    single<EducationRepository> { EducationKtorRepository(get()) }
    single<NewsRepository> { NewsKtorRepository(get()) }
}