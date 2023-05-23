package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data

import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING

data class NewsListUiState(
    val list: List<Pair<String, List<News>>> = listOfCategories(),
    val searchingNews: String = EMPTY_STRING
)
