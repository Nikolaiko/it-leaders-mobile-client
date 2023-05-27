package com.penguins.educationmultiplatform.android.navigation.graps


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.penguins.educationmultiplatform.android.mapScreen.view.YandexMapScreen
import com.penguins.educationmultiplatform.android.navigation.routeObject.MainScreenTabRoute
import com.penguins.educationmultiplatform.android.navigation.routeObject.mainGraph
import com.penguins.educationmultiplatform.android.newsScreen.main.view.MainNewsScreen

@Composable
fun BottomFlowGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = mainGraph,
        startDestination = MainScreenTabRoute.NewsTab.route
    ){
        composable(route = MainScreenTabRoute.NewsTab.route) {
            MainNewsScreen()
        }
        composable(route = MainScreenTabRoute.CoursesTab.route) {

        }
        composable(route = MainScreenTabRoute.TestsTab.route) {
        }
        composable(route = MainScreenTabRoute.MapTab.route) {
            YandexMapScreen()
        }
        composable(route = MainScreenTabRoute.ProfileTab.route) {

        }
    }

}