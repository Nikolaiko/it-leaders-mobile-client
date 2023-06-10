package com.penguins.educationmultiplatform.android.testsScreen.initial.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.penguins.educationmultiplatform.android.commonViews.GenericErrorView
import com.penguins.educationmultiplatform.android.commonViews.LoadingView
import com.penguins.educationmultiplatform.android.commonViews.NotLoggedInView
import com.penguins.educationmultiplatform.android.data.model.consts.testCaseScreenEffect
import com.penguins.educationmultiplatform.android.testsScreen.data.TestsStatus
import com.penguins.educationmultiplatform.android.testsScreen.initial.viewModel.InitialTestsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun InitialTestsScreen(
    viewModel: InitialTestsScreenViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = testCaseScreenEffect) {
        viewModel.checkAuthState()
    }

    when(state.value.testsCheckStatus) {
        TestsStatus.NotLoggedIn -> NotLoggedInView(authCallback = viewModel::authUser)
        TestsStatus.FailedWithError -> GenericErrorView(buttonCallback = viewModel::checkAuthState)
        else -> LoadingView()
    }
}