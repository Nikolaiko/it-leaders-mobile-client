package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.testsScreen.initial.viewModel.InitialTestsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testsModule = module {
    viewModel { InitialTestsScreenViewModel(get(), get()) }

}