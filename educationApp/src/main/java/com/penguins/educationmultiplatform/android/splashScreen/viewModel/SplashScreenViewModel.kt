package com.penguins.educationmultiplatform.android.splashScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.useCases.CheckAuthStateUseCase
import com.penguins.educationmultiplatform.android.navigation.routeObject.AppScreens
import com.penguins.educationmultiplatform.android.navigation.routeObject.nonLoggedUserGraph
import com.penguins.educationmultiplatform.android.navigation.routeObject.rootGraph

class SplashScreenViewModel(
    private val navigation: AppNavigation,
    private val authStateUseCase: CheckAuthStateUseCase
): ViewModel() {
    fun checkAuthState() {
        val authState = authStateUseCase.invoke()
        when {
            authState.tokens != null -> navigation.navigateTo(AppScreens.MainAppScreen)
            authState.skippedAuth -> navigation.navigateTo(AppScreens.MainAppScreen)
            else -> navigation.navigateTo(nonLoggedUserGraph, NavOptions.Builder().setPopUpTo(
                rootGraph, true).build())
        }
    }
}