package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.authScreen.viewModel.AuthViewModel
import com.penguins.educationmultiplatform.android.authScreen.viewModel.RegisterViewModel
import com.penguins.educationmultiplatform.android.domain.usecases.CheckAuthStateUseCase
import com.penguins.educationmultiplatform.android.splashScreen.viewModel.SplashScreenViewModel
import org.koin.dsl.module

val authModule = module {
    single { CheckAuthStateUseCase(get()) }

    single { SplashScreenViewModel(get(), get()) }
    single { AuthViewModel() }
    single { RegisterViewModel() }
}