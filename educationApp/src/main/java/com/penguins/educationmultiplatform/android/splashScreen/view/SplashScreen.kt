package com.penguins.educationmultiplatform.android.splashScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.penguins.educationmultiplatform.android.data.splashScreenEffect
import com.penguins.educationmultiplatform.android.navigation.graps.MainNavGraph
import com.penguins.educationmultiplatform.android.services.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.splashScreen.viewModel.SplashScreenViewModel
import com.penguins.educationmultiplatform.android.ui.gradientSplashScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

@MainNavGraph(start = true)
@Destination
@Composable
fun SplashScreenView(
    navigator: DestinationsNavigator,
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
