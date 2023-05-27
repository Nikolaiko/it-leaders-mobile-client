package com.penguins.educationmultiplatform.android.newsScreen.main.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.penguins.educationmultiplatform.android.navigation.graps.NewsNavHost
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import org.koin.androidx.compose.get

@Composable
fun MainNewsScreen(
    navigation: NewsNavigation = get(),
    navController: NavHostController = rememberNavController()
) {
    NewsNavHost(navController)
    navigation.setNavigator(navController)
}
