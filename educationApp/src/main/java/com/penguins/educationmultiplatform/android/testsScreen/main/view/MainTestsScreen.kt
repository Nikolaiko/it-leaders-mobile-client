package com.penguins.educationmultiplatform.android.testsScreen.main.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.penguins.educationmultiplatform.android.navigation.graps.TestsNavHost
import com.penguins.educationmultiplatform.android.navigation.navigation.TestsNavigation
import org.koin.androidx.compose.get

@Composable
fun MainTestsScreen(
    navigation: TestsNavigation = get(),
    navController: NavHostController = rememberNavController()
) {
    TestsNavHost(navController = navController)
    navigation.setNavigator(navController)
}