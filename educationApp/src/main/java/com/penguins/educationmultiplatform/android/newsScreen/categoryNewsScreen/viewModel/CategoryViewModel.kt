package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.listOfCategories
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.CategoryEvents
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.CategoryUiState
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.listOfNewByHeading
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.common.debugData.getDebugLastNews
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryViewModel : ViewModel() {

    private val _state = MutableStateFlow(CategoryUiState())
    val state = _state.asStateFlow()

    init {
        _state.value = _state.value.copy(
            category = Category.MUSIC,
            lastNews = getDebugLastNews(),
            headingNews = listOfNewByHeading()
        )
    }

    fun onEvent(event: CategoryEvents) {
        when (event) {
            is CategoryEvents.SetLastNews -> _state.tryEmit(
                _state.value.copy(lastNews = event.news)
            )
            is CategoryEvents.SetHeadingNewsList -> _state.tryEmit(
                _state.value.copy(headingNews = event.list)
            )
            is CategoryEvents.SetSchool -> Unit
            is CategoryEvents.OpenNews -> Unit
            is CategoryEvents.OpenNewsList -> Unit
            CategoryEvents.BackButton -> Unit
            CategoryEvents.SearchButton -> Unit
        }
    }

}
