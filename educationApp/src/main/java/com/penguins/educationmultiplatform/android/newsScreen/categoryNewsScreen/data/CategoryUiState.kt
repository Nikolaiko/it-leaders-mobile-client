package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

data class CategoryUiState(
    val category: Category? = null,
    val lastNews: News? = null,
    val headingNews: List<Pair<String, List<News>>> = emptyList()
)
