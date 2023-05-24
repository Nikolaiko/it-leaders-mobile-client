package com.penguins.educationmultiplatform.android.navigation.graps

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.penguins.educationmultiplatform.android.authScreen.view.AuthScreen
import com.penguins.educationmultiplatform.android.authScreen.view.RegisterScreen
import com.penguins.educationmultiplatform.android.navigation.routeObject.AppScreens
import com.penguins.educationmultiplatform.android.navigation.routeObject.nonLoggedUserGraph


@ExperimentalComposeUiApi
fun NavGraphBuilder.authGraph() {
    navigation(startDestination = AppScreens.AuthScreenRoute.route, route = nonLoggedUserGraph) {
        composable(route = AppScreens.AuthScreenRoute.route) {
            AuthScreen()
        }
        composable(route = AppScreens.RegisterScreenRoute.route) {
            RegisterScreen()
        }
    }
}