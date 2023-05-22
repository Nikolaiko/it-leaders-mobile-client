package com.penguins.educationmultiplatform.android.services.navigation

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction

class DestinationController: AppNavigation {
    private var navController: DestinationsNavigator? = null

    override fun setNavigator(controller: DestinationsNavigator) {
        navController = controller
    }

    override fun navigateTo(destination: Direction) {
        navController?.navigate(destination)
    }

    override fun popBackStack() {
        navController?.popBackStack()
    }
}