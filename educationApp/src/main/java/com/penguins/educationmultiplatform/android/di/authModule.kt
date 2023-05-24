package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.authScreen.viewModel.AuthViewModel
import com.penguins.educationmultiplatform.android.authScreen.viewModel.RegisterViewModel
import com.penguins.educationmultiplatform.android.domain.useCases.CheckAuthStateUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.LoginWithEmailUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.RegisterUserUseCase
import com.penguins.educationmultiplatform.android.splashScreen.viewModel.SplashScreenViewModel
import org.koin.dsl.module

val authModule = module {
    single { CheckAuthStateUseCase(get()) }
    single { RegisterUserUseCase( get()) }
    single { LoginWithEmailUseCase( get()) }

    single { SplashScreenViewModel(get(), get()) }
    single { AuthViewModel( get(), get(), get() ) }
    single { RegisterViewModel( get(), get(), get() ) }
}