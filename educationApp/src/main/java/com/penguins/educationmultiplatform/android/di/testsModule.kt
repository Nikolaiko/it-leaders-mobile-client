package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.testsScreen.categories.viewModel.TestCategoriesViewModel
import com.penguins.educationmultiplatform.android.testsScreen.initial.viewModel.InitialTestsScreenViewModel
import com.penguins.educationmultiplatform.android.testsScreen.viewModel.TestCaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testsModule = module {
    viewModel { InitialTestsScreenViewModel(get(), get(), get()) }
    viewModel { TestCategoriesViewModel(get()) }
    viewModel { TestCaseViewModel(get(), get()) }
}