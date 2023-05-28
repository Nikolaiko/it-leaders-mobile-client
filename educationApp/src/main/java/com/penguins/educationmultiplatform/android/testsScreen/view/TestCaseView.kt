package com.penguins.educationmultiplatform.android.testsScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.penguins.educationmultiplatform.android.commonViews.AppBackToolbar
import com.penguins.educationmultiplatform.android.commonViews.GenericErrorView
import com.penguins.educationmultiplatform.android.data.model.consts.testCaseScreenEffect
import com.penguins.educationmultiplatform.android.testsScreen.data.EducationTest
import com.penguins.educationmultiplatform.android.testsScreen.viewModel.TestCaseViewModel
import com.penguins.educationmultiplatform.android.ui.neutral0
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestCaseView(
    viewModel: TestCaseViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = testCaseScreenEffect) {
        viewModel.loadTestCase()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(neutral0)
    ) {
        AppBackToolbar { viewModel.backToCategories() }
        if (state.value.tests.isNotEmpty()) {
            when(val currentTest = state.value.tests[state.value.currentIndex]) {
                is EducationTest.TextTest -> TextTestView(currentTest)
                else -> GenericErrorView()
            }
        }
    }
}