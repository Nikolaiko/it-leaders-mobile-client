package com.penguins.educationmultiplatform.android.navigation.routeObject

const val testsGraphName = "testsGraphRoute"

const val initialTestsRoute = "initialTestsScreen"

sealed class TestsScreens(val route: String) {
    object InitialTestsScreen: TestsScreens(route = "$testsTab/$initialTestsRoute")
}