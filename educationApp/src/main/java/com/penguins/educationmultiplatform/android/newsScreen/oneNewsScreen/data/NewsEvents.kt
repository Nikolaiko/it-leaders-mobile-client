package com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.News

sealed class NewsEvents {
    class SetNews(val news: News) : NewsEvents()
    object BackButton : NewsEvents()
}
