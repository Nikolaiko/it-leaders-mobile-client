package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.authScreen.viewModel.AuthViewModel
import com.penguins.educationmultiplatform.android.authScreen.viewModel.RegisterViewModel
import com.penguins.educationmultiplatform.android.data.validation.AppValuesValidator
import com.penguins.educationmultiplatform.android.domain.usecases.auth.CheckAuthStateUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.auth.LoginWithEmailUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.auth.LoginWithVKUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.auth.LogoutUseCase
import com.penguins.educationmultiplatform.android.domain.usecases.auth.RegisterUserUseCase
import com.penguins.educationmultiplatform.android.domain.validation.ValuesValidator
import com.penguins.educationmultiplatform.android.splashScreen.viewModel.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { CheckAuthStateUseCase(get()) }
    single { RegisterUserUseCase( get()) }
    single { LoginWithEmailUseCase( get()) }
    single { LogoutUseCase( get()) }
    single { LoginWithVKUseCase( get()) }

    viewModel { SplashScreenViewModel(get(), get()) }
    viewModel { AuthViewModel( get(), get(), get(), get(), get() ) }
    viewModel { RegisterViewModel( get(), get(), get(), get() ) }

    single<ValuesValidator> { AppValuesValidator() }
}