package com.penguins.educationmultiplatform.android.navigation.graps

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.penguins.educationmultiplatform.android.authScreen.data.AuthDisplayMode
import com.penguins.educationmultiplatform.android.authScreen.view.AuthScreen
import com.penguins.educationmultiplatform.android.authScreen.view.RegisterScreen
import com.penguins.educationmultiplatform.android.authScreen.viewModel.AuthViewModel
import com.penguins.educationmultiplatform.android.authScreen.viewModel.RegisterViewModel
import com.penguins.educationmultiplatform.android.navigation.routeObject.AppScreens
import com.penguins.educationmultiplatform.android.navigation.routeObject.authDisplayModeParameterName
import com.penguins.educationmultiplatform.android.navigation.routeObject.nonLoggedUserGraph
import org.koin.androidx.compose.koinViewModel


@ExperimentalComposeUiApi
fun NavGraphBuilder.authGraph() {
    navigation(
        startDestination = AppScreens.AuthScreenRoute.route,
        route = "$nonLoggedUserGraph/{$authDisplayModeParameterName}",
        arguments = listOf(
            navArgument(authDisplayModeParameterName) {
                type = NavType.StringType
                defaultValue = AuthDisplayMode.independent.name
            }
        )
    ) {
        composable(
            route = AppScreens.AuthScreenRoute.route,
            arguments = listOf(
                navArgument(authDisplayModeParameterName) {
                    type = NavType.StringType
                    defaultValue = AuthDisplayMode.independent.name
                }
            )
        ) {
            val argName = it.arguments?.getString(authDisplayModeParameterName)
                ?: AuthDisplayMode.independent.name
            val displayMode = AuthDisplayMode.valueOf(argName)
            val viewModel: AuthViewModel = koinViewModel()
            viewModel.setAuthMode(displayMode)

            AuthScreen(viewModel = viewModel)
        }
        composable(
            route = AppScreens.RegisterScreenRoute.route,
            arguments = listOf(
                navArgument(authDisplayModeParameterName) {
                    type = NavType.StringType
                    defaultValue = AuthDisplayMode.independent.name
                }
            )
        ) {
            val argName = it.arguments?.getString(authDisplayModeParameterName)
                ?: AuthDisplayMode.independent.name
            val displayMode = AuthDisplayMode.valueOf(argName)
            val viewModel: RegisterViewModel = koinViewModel()
            viewModel.setAuthMode(displayMode)

            RegisterScreen(viewModel = viewModel)
        }
    }
}