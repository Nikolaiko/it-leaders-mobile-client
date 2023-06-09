package com.penguins.educationmultiplatform.android.navigation.routeObject

const val testsGraphName = "testsGraphRoute"

const val initialTestsRoute = "initialTestsScreen"
const val categoriesTestsRoute = "categoriesTestsScreen"
const val testsRoute = "testsScreen"

sealed class TestsScreens(val route: String) {
    object InitialTestsScreen: TestsScreens(route = "$testsTab/$initialTestsRoute")
    object TestsCategoriesScreen: TestsScreens(route = "$testsTab/$categoriesTestsRoute")
    object UserTestsScreen: TestsScreens(route = "$testsTab/$testsRoute")
}