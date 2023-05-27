package com.penguins.educationmultiplatform.android.navigation.routeObject

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

sealed class NewsScreens (val route: String) {
    object AllNewsScreen : NewsScreens("$newsTab/$ALL_NEWS_SCREEN")
    class OneNewsScreen(val news: News) : NewsScreens("$newsTab/$ONE_NEWS_SCREEN")
    class CategoryNewsScreen(val category: String) : NewsScreens("$newsTab/$CATEGORY_NEWS_SCREEN")
    object SearchNewsScreen : NewsScreens("$newsTab/$SEARCH_NEWS_SCREEN")
    class HeadingNewsScreen(
        val category: String,
        val heading: String
    ) : NewsScreens("$newsTab/$HEADING_NEWS_SCREEN")
}

const val ALL_NEWS_SCREEN = "all_news_screen"
const val ONE_NEWS_SCREEN = "one_news_screen"
const val CATEGORY_NEWS_SCREEN = "category_news_screen"
const val SEARCH_NEWS_SCREEN = "search_news_screen"
const val HEADING_NEWS_SCREEN = "heading_news_screen"

const val CATEGORY_ARGUMENTS = "category"
const val NEWS_ARGUMENT = "news"
const val HEADING_ARGUMENT = "heading"

const val CATEGORY_NEWS_ROUTE = "$newsTab/$CATEGORY_NEWS_SCREEN/{$CATEGORY_ARGUMENTS}"
const val ONE_NEWS_ROUTE = "$newsTab/$ONE_NEWS_SCREEN/{$NEWS_ARGUMENT}"
const val HEADING_NEWS_ROUTE = "$newsTab/$HEADING_NEWS_SCREEN?$CATEGORY_ARGUMENTS={$CATEGORY_ARGUMENTS}&$HEADING_ARGUMENT={$HEADING_ARGUMENT}"
