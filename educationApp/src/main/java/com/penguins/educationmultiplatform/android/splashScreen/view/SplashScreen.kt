package com.penguins.educationmultiplatform.android.splashScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.penguins.educationmultiplatform.android.data.model.consts.splashScreenEffect
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.splashScreen.viewModel.SplashScreenViewModel
import com.penguins.educationmultiplatform.android.ui.gradientSplashScreen
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel


@Composable
fun SplashScreenView(
    navigator: NavHostController,
    viewModel: SplashScreenViewModel = koinViewModel()
) {
    val service = get<AppNavigation>()
    LaunchedEffect(key1 = splashScreenEffect) {
        service.setNavigator(navigator)
        viewModel.checkAuthState()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientSplashScreen)
    ) {}
}
