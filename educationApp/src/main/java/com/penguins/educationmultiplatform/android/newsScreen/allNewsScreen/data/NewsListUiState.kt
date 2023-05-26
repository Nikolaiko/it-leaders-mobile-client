package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.newsScreen.common.debugData.getDebugLastNews
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING

data class NewsListUiState(
    val list: List<Pair<String, List<News>>> = listOfCategories(),
    val lastNews: News? = getDebugLastNews(),
    val searchingNews: String = EMPTY_STRING
)
