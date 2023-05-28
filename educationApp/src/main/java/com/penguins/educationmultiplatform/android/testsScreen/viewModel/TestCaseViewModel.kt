package com.penguins.educationmultiplatform.android.testsScreen.viewModel

import androidx.lifecycle.ViewModel
import com.penguins.educationmultiplatform.android.domain.navigation.AppNavigation
import com.penguins.educationmultiplatform.android.domain.usecases.tests.GetTestCaseUseCase
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.data.NewsUiState
import com.penguins.educationmultiplatform.android.testsScreen.data.TestCaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TestCaseViewModel(
    private val appNavigation: AppNavigation,
    private val loadTestCaseUseCase: GetTestCaseUseCase
): ViewModel() {

    private val _state = MutableStateFlow(TestCaseState(emptyList(), 0))
    val state = _state.asStateFlow()

    fun loadTestCase() {
        val testCase = loadTestCaseUseCase.invoke(Category.MUSIC)
        _state.tryEmit(
            TestCaseState(testCase.tests, 0)
        )
    }

    fun backToCategories() {
        appNavigation.popBackStack()
    }
}