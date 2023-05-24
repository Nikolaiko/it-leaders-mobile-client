package com.penguins.educationmultiplatform.android.domain.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.penguins.educationmultiplatform.android.navigation.routeObject.AppScreens

interface AppNavigation {
    fun setNavigator(controller: NavHostController)
    fun navigateTo(route: String, options:NavOptions? = null)
    fun navigateTo(destination:AppScreens)
    fun popBackStack()
}