package com.penguins.educationmultiplatform.android.navigation.graps

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.penguins.educationmultiplatform.android.navigation.routeObject.TestsScreens
import com.penguins.educationmultiplatform.android.navigation.routeObject.testsGraphName
import com.penguins.educationmultiplatform.android.testsScreen.categories.view.TestCategoriesView
import com.penguins.educationmultiplatform.android.testsScreen.initial.view.InitialTestsScreen
import com.penguins.educationmultiplatform.android.testsScreen.tests.view.TestCaseView

@Composable
fun TestsNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = testsGraphName,
        startDestination = TestsScreens.InitialTestsScreen.route
    ) {
        composable(TestsScreens.InitialTestsScreen.route) {
            InitialTestsScreen()
        }
        composable(TestsScreens.TestsCategoriesScreen.route) {
            TestCategoriesView()
        }
        composable(TestsScreens.UserTestsScreen.route) {
            TestCaseView()
        }
    }
}