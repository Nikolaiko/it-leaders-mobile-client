package com.penguins.educationmultiplatform.android.di

import com.penguins.educationmultiplatform.android.navigation.navigation.TestsNavigation
import com.penguins.educationmultiplatform.android.testsScreen.categories.viewModel.TestCategoriesViewModel
import com.penguins.educationmultiplatform.android.testsScreen.initial.viewModel.InitialTestsScreenViewModel
import com.penguins.educationmultiplatform.android.testsScreen.tests.viewModel.TestCaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testsModule = module {
    viewModel { InitialTestsScreenViewModel(get(), get(), get(), get(), get()) }
    viewModel { TestCategoriesViewModel(get(), get(), get(), get()) }
    viewModel { TestCaseViewModel(get()) }

    single<TestsNavigation> { TestsNavigation() }
}