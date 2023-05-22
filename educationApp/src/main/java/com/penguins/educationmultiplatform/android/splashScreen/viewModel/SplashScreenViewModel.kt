package com.penguins.educationmultiplatform.android.splashScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.destinations.AuthScreenDestination
import com.penguins.educationmultiplatform.android.destinations.MainScreenViewDestination
import com.penguins.educationmultiplatform.android.domain.usecases.CheckAuthStateUseCase
import com.penguins.educationmultiplatform.android.services.navigation.AppNavigation

class SplashScreenViewModel(
    private val navigation: AppNavigation,
    private val authStateUseCase: CheckAuthStateUseCase
): ViewModel() {
    fun checkAuthState() {
        val authState = authStateUseCase.invoke()
        when {
            authState.tokens != null -> navigation.navigateTo(MainScreenViewDestination)
            authState.skippedAuth -> navigation.navigateTo(MainScreenViewDestination)
            else -> navigation.navigateTo(AuthScreenDestination)
        }
    }
}