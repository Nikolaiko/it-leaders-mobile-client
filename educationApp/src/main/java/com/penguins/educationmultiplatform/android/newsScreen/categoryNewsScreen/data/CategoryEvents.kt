package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

sealed class CategoryEvents {
    class SetCategoryAndNews(val category: String) : CategoryEvents()
    class SetLastNews(val news: News) : CategoryEvents()
    object BackButton : CategoryEvents()
    object SearchButton : CategoryEvents()
    class OpenNews(val news: News) : CategoryEvents()
    class OpenNewsListByHeading(val heading: String) : CategoryEvents()
}
