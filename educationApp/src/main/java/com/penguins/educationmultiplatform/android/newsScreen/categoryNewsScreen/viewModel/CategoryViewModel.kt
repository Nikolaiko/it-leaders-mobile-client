package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.CategoryEvents
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.CategoryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CategoryViewModel : ViewModel() {

    private val _state = MutableStateFlow(CategoryUiState())
    val state = _state.asStateFlow()

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
