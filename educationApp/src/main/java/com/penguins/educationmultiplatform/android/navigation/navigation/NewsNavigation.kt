package com.penguins.educationmultiplatform.android.navigation.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavHostController
import com.penguins.educationmultiplatform.android.navigation.routeObject.CATEGORY_ARGUMENTS
import com.penguins.educationmultiplatform.android.navigation.routeObject.HEADING_ARGUMENT
import com.penguins.educationmultiplatform.android.navigation.routeObject.NewsScreens
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class NewsNavigation {
    @SuppressLint("StaticFieldLeak")
    private var navController: NavHostController? = null

    fun setNavigator(controller: NavHostController) {
        navController = controller
    }

    fun back() = navController?.popBackStack()

    fun navigateTo(screen: NewsScreens) = when (screen) {

        NewsScreens.AllNewsScreen -> navController?.navigate(screen.route)

        is NewsScreens.CategoryNewsScreen -> {
            navController?.navigate("${screen.route}/${screen.category}")
        }

        is NewsScreens.OneNewsScreen -> {
            val encodedUrl = URLEncoder.encode(screen.news.imageUrl, StandardCharsets.UTF_8.toString())
            val json = Json.encodeToString(screen.news.copy(imageUrl = encodedUrl))
            navController?.navigate("${screen.route}/$json")
        }

        is NewsScreens.HeadingNewsScreen -> {
            navController?.navigate(
                "${screen.route}?$CATEGORY_ARGUMENTS=${screen.category}&$HEADING_ARGUMENT=${screen.heading}"
            )
        }

        is NewsScreens.SearchNewsScreen -> {
            navController?.navigate("${screen.route}/${screen.category}")
        }
    }
}