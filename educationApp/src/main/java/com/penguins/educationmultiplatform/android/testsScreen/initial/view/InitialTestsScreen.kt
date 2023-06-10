package com.penguins.educationmultiplatform.android.testsScreen.initial.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.penguins.educationmultiplatform.android.commonViews.NotLoggedInView
import com.penguins.educationmultiplatform.android.data.model.consts.testCaseScreenEffect
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

    when(state.value.isLoggedIn) {
        true -> Text(text = "Logged in")
        false -> NotLoggedInView(authCallback = viewModel::authUser)
    }
}