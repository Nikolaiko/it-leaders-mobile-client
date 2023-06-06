package com.penguins.educationmultiplatform.android.testsScreen.main.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.penguins.educationmultiplatform.android.navigation.graps.TestsNavHost

@Composable
fun MainTestsScreen(
    navController: NavHostController = rememberNavController()
) {
    TestsNavHost(navController = navController)
}