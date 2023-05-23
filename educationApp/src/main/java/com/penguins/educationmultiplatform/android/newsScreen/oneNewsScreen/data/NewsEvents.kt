package com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

sealed class NewsEvents {
    class SetNews(val news: News) : NewsEvents()
    object BackButton : NewsEvents()
}
