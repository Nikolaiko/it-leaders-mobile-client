package com.penguins.educationmultiplatform.android.testsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.AppScreens

class TestCategoriesViewModel(
    private val appNavigation: AppNavigation,
): ViewModel() {
    fun navigateToMusic() {
        appNavigation.navigateTo(AppScreens.TestCaseScreen)
    }
}