package com.penguins.educationmultiplatform.android.data.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.AppScreens

class DestinationController : AppNavigation {
    private var navController: NavHostController? = null

    override fun setNavigator(controller: NavHostController) {
        navController = controller
    }

    override fun navigateTo(route: String, options: NavOptions?) {
        navController?.navigate(route, options)
    }

    override fun navigateTo(destination: AppScreens) {
        navController?.navigate(destination.route, destination.options)
    }

    override fun popBackStack() {
        navController?.popBackStack()
    }

    override fun popBackStack(route: String, inclusive: Boolean, saveState: Boolean) {
        navController?.popBackStack(route, inclusive, saveState)
    }
}