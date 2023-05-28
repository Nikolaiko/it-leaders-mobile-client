package com.penguins.educationmultiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.penguins.educationmultiplatform.android.mainScreen.view.MainScreenView
import com.penguins.educationmultiplatform.android.navigation.graps.authGraph
import com.penguins.educationmultiplatform.android.navigation.routeObject.AppScreens
import com.penguins.educationmultiplatform.android.navigation.routeObject.rootGraph
import com.penguins.educationmultiplatform.android.splashScreen.view.SplashScreenView
import com.penguins.educationmultiplatform.android.testsScreen.view.TestCaseView


class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier
                    .fillMaxSize()) {
                    NavHost(
                        navController = navController,
                        route = rootGraph,
                        startDestination = AppScreens.SplashScreenRoute.route
                    ) {
                        composable(route = AppScreens.SplashScreenRoute.route){
                            SplashScreenView(navigator = navController)
                        }
                        authGraph()
                        composable(route = AppScreens.MainAppScreen.route) {
                            MainScreenView()
                        }
                        composable(route = AppScreens.TestCaseScreen.route) {
                            TestCaseView()
                        }
                    }
                }

            }
        }
    }
}
