package com.penguins.educationmultiplatform.android.mainScreen.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.penguins.educationmultiplatform.android.navigation.graps.BottomFlowGraph

abstract class Screens(){

}
@Composable
fun MainScreenView() {

//    Scaffold(
//        modifier = Modifier
//            .fillMaxSize()
//            .statusBarsPadding()
//            .navigationBarsPadding(),
//        bottomBar = { BottomBar(navController = navController) }) {
//        it
//        BottomFlowGraph(navController = navController)
//    }

}
//
//@Composable
//fun BottomBar(navController: NavHostController) {
//    val screens = listOf(
//        MainScreenTabRoute.HomeTab,
//        MainScreenTabRoute.LikeTab,
//        MainScreenTabRoute.ProfileTab,
//        MainScreenTabRoute.OrderTab,
//
//        )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
//
//    val animateHome = animateDpAsState(
//        targetValue = if (currentDestination?.hierarchy?.any {
//                it.route == MainScreenTabRoute.HomeTab.route
//            } == true) 40.dp else 0.dp,
//        animationSpec = tween(
//            durationMillis = 300,
//            easing = LinearEasing
//        )
//    )
//    val animateLike = animateDpAsState(
//        targetValue = if (currentDestination?.hierarchy?.any {
//                it.route == MainScreenTabRoute.LikeTab.route
//            } == true) 40.dp else 0.dp,
//        animationSpec = tween(
//            durationMillis = 300,
//            easing = LinearEasing
//        )
//    )
//    val animateProfile = animateDpAsState(
//        targetValue = if (currentDestination?.hierarchy?.any {
//                it.route == MainScreenTabRoute.ProfileTab.route
//            } == true) 40.dp else 0.dp,
//        animationSpec = tween(
//            durationMillis = 300,
//            easing = LinearEasing
//        )
//    )
//    val animateOrder = animateDpAsState(
//        targetValue = if (currentDestination?.hierarchy?.any {
//                it.route == MainScreenTabRoute.OrderTab.route
//            } == true) 40.dp else 0.dp,
//        animationSpec = tween(
//            durationMillis = 300,
//            easing = LinearEasing
//        )
//    )
//
//
//    if (bottomBarDestination) {
//        BoxWithConstraints(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp),
//            contentAlignment = Alignment.BottomStart
//        ) {
//            val sizeItem = maxWidth / 4
//
//            Row(verticalAlignment = Alignment.Bottom) {
//                SelectedOval(width = sizeItem, animateHeight = animateHome.value)
//                SelectedOval(width = sizeItem, animateHeight = animateLike.value)
//                SelectedOval(width = sizeItem, animateHeight = animateProfile.value)
//                SelectedOval(width = sizeItem, animateHeight = animateOrder.value)
//            }
//            BottomNavigation(
//                modifier = Modifier, backgroundColor = Color.Transparent, elevation = 0.dp
//            ) {
//                screens.forEach { screen ->
//                    AddItem(
//                        screen = screen,
//                        currentDestination = currentDestination,
//                        navController = navController
//                    )
//                }
//
//            }
//
//
//        }
//    }
//}
//
//@Composable
//fun RowScope.AddItem(
//    screen: MainScreenTabRoute,
//    currentDestination: NavDestination?,
//    navController: NavHostController
//) {
//
//
//    BottomNavigationItem(modifier = Modifier
//        .background(color = backgroundFieldColor),
//        label = {
//            Text(text = screen.title, color = activeContentColor)
//        },
//        alwaysShowLabel = false,
//        icon = {
//            Icon(
//                imageVector = ImageVector.vectorResource(id = screen.icon),
//                contentDescription = "Navigation Icon",
//                tint = if (currentDestination?.hierarchy?.any {
//                        it.route == screen.route
//                    } == true) activeContentColor else inactiveContentColor
//            )
//        },
//        selected = currentDestination?.hierarchy?.any {
//            it.route == screen.route
//        } == true,
//        unselectedContentColor = inactiveContentColor,
//        selectedContentColor = backgroundFieldColor,
//        onClick = {
//            navController.navigate(screen.route) {
//                popUpTo(navController.graph.findStartDestination().id)
//                launchSingleTop = true
//            }
//        })
//
//}