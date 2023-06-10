package com.penguins.educationmultiplatform.android.navigation.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.penguins.educationmultiplatform.android.navigation.routeObject.TestsScreens

class TestsNavigation {
    private var navController: NavHostController? = null

    fun setNavigator(controller: NavHostController) {
        navController = controller
    }

    fun back() = navController?.popBackStack()

    fun backToExactScreen(target: TestsScreens, inclusive: Boolean = false) {
        navController?.popBackStack(target.route, inclusive)
    }

    fun navigateTo(target: TestsScreens, navOptions: NavOptions? = null) {
        navController?.navigate(target.route, navOptions)
    }
}