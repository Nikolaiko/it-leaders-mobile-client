package com.penguins.educationmultiplatform.android.newsScreen.common.data

data class CategoryNewsList(
    val category: Category? = null,
    val news: List<News> = emptyList()
)
