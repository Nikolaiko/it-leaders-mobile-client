package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

sealed class SearchNewsEvents {
    class SetSearchingText(val text: String) : SearchNewsEvents()
    class SetSearchingNews(val news: List<News>) : SearchNewsEvents()
    class SetCategory(val category: String?) : SearchNewsEvents()
    class CategoryChecked(val category: Category) : SearchNewsEvents()
    class AllCategoriesChecked(val isChecked: Boolean) : SearchNewsEvents()
    object ClearSearch : SearchNewsEvents()
    object BackButton : SearchNewsEvents()
    object SearchButton : SearchNewsEvents()
    class OpenNews(val news: News) : SearchNewsEvents()
}
