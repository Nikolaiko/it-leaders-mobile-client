package com.penguins.educationmultiplatform.android.newsScreen.headingNews.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

sealed class HeadingNewsEvents {
    class SetTitleAndNews(val title: String?, val category: String?) : HeadingNewsEvents()
    object BackButton : HeadingNewsEvents()
    object SearchButton : HeadingNewsEvents()
    class OpenNews(val news: News) : HeadingNewsEvents()
}
