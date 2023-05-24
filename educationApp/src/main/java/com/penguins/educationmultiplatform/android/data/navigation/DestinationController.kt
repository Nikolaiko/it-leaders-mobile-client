package com.penguins.educationmultiplatform.android.data.navigation

import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
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