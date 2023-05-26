package com.penguins.educationmultiplatform.android.navigation.graps

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.view.CoursesScreen
import com.penguins.educationmultiplatform.android.navigation.routeObject.CoursesTapScreens
import com.penguins.educationmultiplatform.android.navigation.routeObject.MainScreenTabRoute
import com.penguins.educationmultiplatform.android.navigation.routeObject.courseId
import com.penguins.educationmultiplatform.android.navigation.routeObject.coursesGraphRoute

@Composable
fun CoursesTabScreen(navController: NavHostController = rememberNavController()) {


    NavHost(
        navController = navController,
        route = coursesGraphRoute,
        startDestination = CoursesTapScreens.CoursesScreenRoute.route
    ) {

        composable(route = CoursesTapScreens.CoursesScreenRoute.route) {

            CoursesScreen()

        }

        composable(route = CoursesTapScreens.DetailCourseListScreenRoute.route) {


        }
        composable(
            route = CoursesTapScreens.DetailCourseScreenRoute.route, arguments = listOf(
                navArgument(
                    courseId
                ) {
                    type = NavType.IntType
                }
            )
        ) {
            val id = it.arguments?.getInt(
                courseId
            )

        }

        composable(route = CoursesTapScreens.MapScreenRoute.route) {


        }

    }
}