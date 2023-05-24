package com.penguins.educationmultiplatform.android.navigation.graps


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.penguins.educationmultiplatform.android.navigation.routeObject.MainScreenTabRoute
import com.penguins.educationmultiplatform.android.navigation.routeObject.mainGraph

@Composable
fun BottomFlowGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = mainGraph,
        startDestination = MainScreenTabRoute.NewsTab.route
    ){
        composable(route = MainScreenTabRoute.NewsTab.route) {

        }
        composable(route = MainScreenTabRoute.CoursesTab.route) {

        }
        composable(route = MainScreenTabRoute.TestsTab.route) {

        }
        composable(route = MainScreenTabRoute.MapTab.route) {
        }
        composable(route = MainScreenTabRoute.ProfileTab.route) {

        }
    }

}