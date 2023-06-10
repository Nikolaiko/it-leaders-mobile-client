package com.penguins.educationmultiplatform.android.testsScreen.tests.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.commonViews.GenericErrorView
import com.penguins.educationmultiplatform.android.data.model.consts.testCaseScreenEffect
import com.penguins.educationmultiplatform.android.testsScreen.data.EducationTest
import com.penguins.educationmultiplatform.android.testsScreen.tests.subViews.TestCaseTopView
import com.penguins.educationmultiplatform.android.testsScreen.tests.subViews.TextTestResultView
import com.penguins.educationmultiplatform.android.testsScreen.tests.subViews.TextTestView
import com.penguins.educationmultiplatform.android.testsScreen.tests.viewModel.TestCaseViewModel
import com.penguins.educationmultiplatform.android.ui.allNewsGradientBackground
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestCaseView(
    viewModel: TestCaseViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = testCaseScreenEffect) {
        viewModel.loadTestCase()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(allNewsGradientBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.music_tests_background),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (state.value.currentTest != null) {
                TestCaseTopView(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp),
                    score = state.value.userScore,
                    state.value.currentTest!!.category,
                    state.value.currentTest!!.difficulty
                )
                when(state.value.currentTest) {
                    is EducationTest.TextTest -> {
                        if (state.value.currentTestState.confirmedAnswer) {
                            TextTestResultView(
                                state.value.currentTest as EducationTest.TextTest,
                                state.value.currentTestState,
                                viewModel::moveToNextTest
                            )
                        } else {
                            TextTestView(
                                state.value.currentTest as EducationTest.TextTest,
                                firstVariantCallback = viewModel::firstVariantCallback,
                                secondVariantCallback = viewModel::secondVariantCallback,
                                thirdVariantCallback = viewModel::thirdVariantCallback,
                                fourthVariantCallback = viewModel::fourthVariantCallback,
                                checkCallback = viewModel::checkCallback,
                                state.value.currentTestState
                            )
                        }
                    }
                    else -> GenericErrorView()
                }
            }
        }
    }
}