package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.data.localUserDataRepository.SharedPreferencesRepository
import com.penguins.educationmultiplatform.android.data.remote.api.EducationRepositoryImpl
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.domain.remoteRepository.EducationRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single <EducationRepository> { EducationRepositoryImpl(get()) }
    single <LocalUserDataRepository> { SharedPreferencesRepository(get()) }
}