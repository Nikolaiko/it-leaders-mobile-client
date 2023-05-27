package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

sealed class SearchNewsEvents {
    class SetSearchingText(val text: String) : SearchNewsEvents()
    class SetSearchingNews(val news: List<News>) : SearchNewsEvents()
    object ClearSearch : SearchNewsEvents()
    object BackButton : SearchNewsEvents()
    object SearchButton : SearchNewsEvents()
    object FilterButton: SearchNewsEvents()
    class OpenNews(val news: News) : SearchNewsEvents()
}
