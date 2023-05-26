package com.penguins.educationmultiplatform.android.navigation.graps

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.penguins.educationmultiplatform.android.navigation.routeObject.CATEGORY_ARGUMENTS
import com.penguins.educationmultiplatform.android.navigation.routeObject.CATEGORY_NEWS_ROUTE
import com.penguins.educationmultiplatform.android.navigation.routeObject.NEWS_ARGUMENT
import com.penguins.educationmultiplatform.android.navigation.routeObject.NewsScreens
import com.penguins.educationmultiplatform.android.navigation.routeObject.ONE_NEWS_ROUTE
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.view.NewsListScreen
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.view.CategoryScreen
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.view.NewsScreen
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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
        composable(
            route = ONE_NEWS_ROUTE,
            arguments = listOf(navArgument(NEWS_ARGUMENT) { type = NavType.StringType })
        ) {
            val json = it.arguments?.getString(NEWS_ARGUMENT) ?: EMPTY_STRING
            val news = Json.decodeFromString<News>(json)
            NewsScreen(news)
        }
        composable(
            route = CATEGORY_NEWS_ROUTE,
            arguments = listOf(navArgument(CATEGORY_ARGUMENTS) { type = NavType.StringType })
        ) {
            val category = it.arguments?.getString(CATEGORY_ARGUMENTS)
            CategoryScreen(category)
        }
    }
}