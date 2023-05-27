package com.penguins.educationmultiplatform.android.newsScreen.headingNews.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

data class HeadingNewsUiState(
    val title: String? = null,
    val category: Category? = null,
    val news: List<News> = emptyList()
)
