package com.penguins.educationmultiplatform.android.navigation.routeObject

import androidx.navigation.NavOptions
import com.penguins.educationmultiplatform.android.authScreen.data.AuthDisplayMode

const val authDisplayModeParameterName = "authDisplayMode"

const val mainScreenRoute = "mainScreen"

const val popRouteName = "popRoute"

const val loggedUserGraph = "loggedGraph"

const val nonLoggedUserGraph = "nonLoggedUserGraph"
val nonLoggedIndependentUserGraph = "$nonLoggedUserGraph/${AuthDisplayMode.independent.name}"
val nonLoggedAsChildUserGraph = "$nonLoggedUserGraph/${AuthDisplayMode.asChild.name}"

const val authSignUpRoute = "authScreen"

const val registerRoute = "registerScreen"

const val splashScreenRoute = "splashScreenRoute"

const val testCaseScreenRoute = "testCaseRoute"

const val rootGraph = "rootGraph"

const val mainGraph = "mainGraph"



sealed class AppScreens(
    var route: String,
    val options: NavOptions? = null,
    val inclusive: Boolean = false,
    val saveState: Boolean = false,
    val popTargetRoute: String = ""
){


    object SplashScreenRoute: AppScreens(
        route = splashScreenRoute,
        options = NavOptions.Builder().setPopUpTo(rootGraph, true).build()
    )
    
    object AuthScreenRoute: AppScreens(
        route = authSignUpRoute,
        options = NavOptions.Builder().setPopUpTo(rootGraph, true).build()
    )

    object RegisterScreenRoute: AppScreens(
        route = registerRoute,
        options = NavOptions.Builder().setPopUpTo(0, false).build()
    )

    object MainAppScreen: AppScreens(
        route = mainScreenRoute,
        options = NavOptions.Builder().setPopUpTo(rootGraph, true).build()
    )

    object TestCaseScreen: AppScreens(route = testCaseScreenRoute)

    object PopBackToMain: AppScreens(
        route = popRouteName,
        popTargetRoute = mainScreenRoute
    )

    object PopBackStack: AppScreens(popRouteName)

}
