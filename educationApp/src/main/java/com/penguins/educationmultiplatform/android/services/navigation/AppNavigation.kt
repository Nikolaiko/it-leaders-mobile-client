package com.penguins.educationmultiplatform.android.services.navigation

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction

interface AppNavigation {
    fun setNavigator(controller: DestinationsNavigator)
    fun navigateTo(destination: Direction)
    fun popBackStack()
}