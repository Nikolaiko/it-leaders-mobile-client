package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

sealed class CategoryEvents {
    class SetCategory(val category: String) : CategoryEvents()
    class SetLastNews(val news: News) : CategoryEvents()
    class SetHeadingNewsList(val list: List<Pair<String, List<News>>>) : CategoryEvents()
    object BackButton : CategoryEvents()
    object SearchButton : CategoryEvents()
    class OpenNews(val news: News) : CategoryEvents()
    class OpenNewsList(val heading: String) : CategoryEvents()
}
