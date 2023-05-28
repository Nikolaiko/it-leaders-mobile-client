package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data

import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

sealed class SearchNewsEvents {
    class SetSearchingText(val text: String) : SearchNewsEvents()
    class SetSearchingNews(val news: List<News>) : SearchNewsEvents()
    class SetCategory(val category: String?) : SearchNewsEvents()
    class SetCategories(val categories: List<Category>) : SearchNewsEvents()//???
    class CategoryChecked(val category: Category) : SearchNewsEvents()
    object AllCategoriesChecked : SearchNewsEvents()
    object ClearSearch : SearchNewsEvents()
    object BackButton : SearchNewsEvents()
    object SearchButton : SearchNewsEvents()
    object FilterButton: SearchNewsEvents()
    class OpenNews(val news: News) : SearchNewsEvents()
}
