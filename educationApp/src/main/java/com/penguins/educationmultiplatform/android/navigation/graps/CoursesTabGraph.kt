package com.penguins.educationmultiplatform.android.navigation.graps

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.view.CoursesScreen
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.view.DetailCourseScreen
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.view.MapWithCategoryScreen
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.view.VideoItemScreen
import com.penguins.educationmultiplatform.android.navigation.routeObject.*

@Composable
fun CoursesScreens(navController: NavHostController = rememberNavController()){
    CoursesTabScreen(navController = navController)
}

@Composable
fun CoursesTabScreen(navController: NavHostController) {


    NavHost(
        navController = navController,
        route = coursesGraphRoute,
        startDestination = CoursesTapScreens.CoursesScreenRoute.route
    ) {

        composable(route = CoursesTapScreens.CoursesScreenRoute.route) {

            CoursesScreen(navController = navController)

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
            DetailCourseScreen(id = id, navController = navController)

        }

        composable(route = CoursesTapScreens.MapScreenRoute.route, arguments = listOf(
            navArgument(
                typeId
            ) {
                type = NavType.IntType
            }
        )) {
            val id = it.arguments?.getInt(
                typeId
            )
            MapWithCategoryScreen(id, navController)

        }
        composable(route = CoursesTapScreens.DetailCourseListScreenRoute.route, arguments = listOf(
            navArgument(
                lessonId
            ) {
                type = NavType.IntType
            }
        )) {
            val id = it.arguments?.getInt(
                lessonId
            )
            VideoItemScreen(id = id, navController = navController)

        }

    }
}