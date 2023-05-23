package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

sealed class CategoryEvents {
    class SetLastNews(val news: News) : CategoryEvents()
    class SetHeadingNewsList(val list: List<Pair<String, List<News>>>) : CategoryEvents()
    class SetSchool(val schools: List<String>) : CategoryEvents()
    object BackButton : CategoryEvents()
    object SearchButton : CategoryEvents()
    class OpenNews(val news: News) : CategoryEvents()
    class OpenNewsList(val heading: String) : CategoryEvents()
}
