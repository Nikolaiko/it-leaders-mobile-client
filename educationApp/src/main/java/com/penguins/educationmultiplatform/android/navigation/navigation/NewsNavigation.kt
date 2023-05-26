package com.penguins.educationmultiplatform.android.navigation.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavHostController
import com.penguins.educationmultiplatform.android.navigation.routeObject.NewsScreens
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class NewsNavigation {
    @SuppressLint("StaticFieldLeak")
    private var navController: NavHostController? = null

    fun setNavigator(controller: NavHostController) {
        navController = controller
    }

    fun back() = navController?.popBackStack()

    fun navigateTo(screen: NewsScreens) = when (screen) {
        NewsScreens.AllNewsScreen -> navController?.navigate(screen.route)
        is NewsScreens.CategoryNewsScreen -> {
            navController?.navigate("${screen.route}")///${screen.category}
        }
        is NewsScreens.OneNewsScreen -> {
            val json = Json.encodeToString(screen.news)
            navController?.navigate("${screen.route}")
        }
    }
}