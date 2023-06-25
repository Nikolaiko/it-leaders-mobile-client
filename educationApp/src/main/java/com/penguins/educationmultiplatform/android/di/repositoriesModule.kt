package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.data.localUserDataRepository.SharedPreferencesRepository
import com.penguins.educationmultiplatform.android.data.model.consts.HttpRoutes
import com.penguins.educationmultiplatform.android.data.network.KtorRepository
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.network.EducationRepository
import com.penguins.educationmultiplatform.android.domain.network.NewsRepository
import com.services.storage.TokenStorage
import org.koin.dsl.binds
import org.koin.dsl.module

val repositoriesModule = module {

    single { SharedPreferencesRepository(get()) }
        .binds(
            listOf(
                LocalUserDataRepository::class, TokenStorage::class
            ).toTypedArray()
        )

    single {
        KtorRepository(
            baseAddress = HttpRoutes.BASE_URL,
            tokenStorage = get()
        )
    }.binds(
        listOf(
            EducationRepository::class,
            NewsRepository::class
        ).toTypedArray()
    )
}