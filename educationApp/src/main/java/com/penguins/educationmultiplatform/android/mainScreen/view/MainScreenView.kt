package com.penguins.educationmultiplatform.android.mainScreen.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.navigation.graps.BottomFlowGraph
import com.penguins.educationmultiplatform.android.navigation.routeObject.MainScreenTabRoute
import com.penguins.educationmultiplatform.android.ui.*

@Composable
fun MainScreenView(navController: NavHostController = rememberNavController()) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = { BottomBar(navController = navController) }) {
        it
        BottomFlowGraph(navController = navController)
    }

}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        MainScreenTabRoute.NewsTab,
        MainScreenTabRoute.CoursesTab,
        MainScreenTabRoute.TestsTab,
        MainScreenTabRoute.MapTab,
        MainScreenTabRoute.ProfileTab,

        )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            val sizeItem = maxWidth / 5

            Card(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                elevation = 0.dp
            ) {
                BottomNavigation(
                    modifier = Modifier.height(60.dp),
                    backgroundColor = primaryWhite,
                    elevation = 0.dp,
                ) {
                    screens.forEach { screen ->
                        AddItem(
                            screen = screen,
                            currentDestination = currentDestination,
                            navController = navController
                        )
                    }

                }
                Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.Bottom) {
                    BottomSelector(
                        width = sizeItem,
                        selected = currentDestination?.hierarchy?.any {
                            it.route == MainScreenTabRoute.NewsTab.route
                        } == true)
                    BottomSelector(
                        width = sizeItem,
                        selected = currentDestination?.hierarchy?.any {
                            it.route == MainScreenTabRoute.CoursesTab.route
                        } == true)
                    BottomSelector(
                        width = sizeItem,
                        selected = currentDestination?.hierarchy?.any {
                            it.route == MainScreenTabRoute.TestsTab.route
                        } == true)
                    BottomSelector(
                        width = sizeItem,
                        selected = currentDestination?.hierarchy?.any {
                            it.route == MainScreenTabRoute.MapTab.route
                        } == true)
                    BottomSelector(
                        width = sizeItem,
                        selected = currentDestination?.hierarchy?.any {
                            it.route == MainScreenTabRoute.ProfileTab.route
                        } == true)

                }

            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: MainScreenTabRoute,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val animatePadding = animateDpAsState(
        targetValue = if (currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true) 4.dp else 0.dp
    )
    BottomNavigationItem(modifier = Modifier
        .padding(bottom = animatePadding.value)
        .background(color = primaryWhite),
        label = {
            Text(
                text = screen.title,
                style = if (currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true) textActiveBottomItem else textInactiveBottomItem,
            )
        },
        alwaysShowLabel = true,
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = if (currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true) screen.activeIcon else screen.inActiveIcon),
                contentDescription = "Navigation Icon",
                tint = Color.Unspecified
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = primaryGray,
        selectedContentColor = primaryWhite,
        onClick = {
            if (currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true) {

            } else {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        })
}

@Composable
fun BottomSelector(width: Dp, selected: Boolean) {

    Box(
        modifier = Modifier
            .width(width)
            .background(primaryWhite)
    ) {
        AnimatedVisibility(
            modifier = Modifier.width(width),
            visible = selected,
            enter = expandVertically(
                expandFrom = Alignment.Bottom,
                animationSpec = tween(300)
            ),
            exit = shrinkVertically(
                shrinkTowards = Alignment.Bottom,
                animationSpec = tween(300)
            )
        ) {
            Box(
                modifier = Modifier
                    .background(primaryWhite)
                    .fillMaxWidth()
                    .wrapContentHeight(), contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_bottom_selector),
                    contentDescription = null,
                    tint = primaryGreen
                )
            }
        }
    }
}