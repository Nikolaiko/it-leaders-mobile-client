package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING

data class SearchNewsUiState(
    val searchingText: String = EMPTY_STRING,
    val findingNews: List<News> = emptyList()
)
