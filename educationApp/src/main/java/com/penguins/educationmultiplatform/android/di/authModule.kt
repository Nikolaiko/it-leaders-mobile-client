package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.authScreen.viewModel.AuthViewModel
import com.penguins.educationmultiplatform.android.authScreen.viewModel.RegisterViewModel
import com.penguins.educationmultiplatform.android.domain.useCases.CheckAuthStateUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.LoginWithEmailUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.RegisterUserUseCase
import com.penguins.educationmultiplatform.android.splashScreen.viewModel.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { CheckAuthStateUseCase(get()) }
    single { RegisterUserUseCase( get()) }
    single { LoginWithEmailUseCase( get()) }

    viewModel { SplashScreenViewModel(get(), get()) }
    viewModel { AuthViewModel( get(), get(), get() ) }
    viewModel { RegisterViewModel( get(), get(), get() ) }
}