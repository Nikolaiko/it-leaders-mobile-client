package com.penguins.educationmultiplatform.android.navigation.routeObject

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

const val ALL_NEWS_SCREEN = "all_news_screen"
const val ONE_NEWS_SCREEN = "one_news_screen"
const val CATEGORY_NEWS_SCREEN = "category_news_screen"

sealed class NewsScreens (val route: String) {
    object AllNewsScreen : NewsScreens("$newsTab/$ALL_NEWS_SCREEN")
    class OneNewsScreen(val news: News) : NewsScreens("$newsTab/$ONE_NEWS_SCREEN")
    class CategoryNewsScreen(val category: String) : NewsScreens("$newsTab/$CATEGORY_NEWS_SCREEN")
}
