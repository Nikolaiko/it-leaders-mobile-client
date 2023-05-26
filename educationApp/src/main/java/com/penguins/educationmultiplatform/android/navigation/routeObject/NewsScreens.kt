package com.penguins.educationmultiplatform.android.navigation.routeObject

const val ALL_NEWS_SCREEN = "all_news_screen"
const val ONE_NEWS_SCREEN = "one_news_screen"
const val CATEGORY_NEWS_SCREEN = "category_news_screen"

sealed class NewsScreens (val route: String) {
    object AllNewsScreen : NewsScreens(ALL_NEWS_SCREEN)
    object OneNewsScreen: NewsScreens(ONE_NEWS_SCREEN)
    object CategoryNewsScreen: NewsScreens(CATEGORY_NEWS_SCREEN)
}
