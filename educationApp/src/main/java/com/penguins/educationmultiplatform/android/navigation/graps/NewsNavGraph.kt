package com.penguins.educationmultiplatform.android.navigation.graps

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.navigation.routeObject.ALL_NEWS_SCREEN
import com.penguins.educationmultiplatform.android.navigation.routeObject.NewsScreens
import com.penguins.educationmultiplatform.android.navigation.routeObject.ONE_NEWS_SCREEN
import com.penguins.educationmultiplatform.android.navigation.routeObject.newsTab
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.view.NewsListScreen
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.view.NewsScreen

@Composable
fun NewsNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = "newsGraph",
        startDestination = NewsScreens.AllNewsScreen.route
    ) {
        composable(route = NewsScreens.AllNewsScreen.route) {
            NewsListScreen()
        }
        composable(route = "$newsTab/$ONE_NEWS_SCREEN") {
            NewsScreen(
                onBackClick = navController::popBackStack
            )
        }
    }
}