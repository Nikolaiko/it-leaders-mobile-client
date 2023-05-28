package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.data.localUserDataRepository.SharedPreferencesRepository
import com.penguins.educationmultiplatform.android.data.remote.api.EducationRepositoryImpl
import com.penguins.educationmultiplatform.android.data.remote.api.NewsRepositoryImpl
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository
import com.penguins.educationmultiplatform.android.domain.remoteRepository.NewsRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single <EducationRepository> { EducationRepositoryImpl(get()) }
    single <LocalUserDataRepository> { SharedPreferencesRepository(get()) }
    single <NewsRepository> { NewsRepositoryImpl(get()) }
}