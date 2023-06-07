package com.penguins.educationmultiplatform.android.profileScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.authScreen.components.EducationButton
import com.penguins.educationmultiplatform.android.profileScreen.components.card.ProfileCard
import com.penguins.educationmultiplatform.android.profileScreen.components.tabs.ProfileTabs
import com.penguins.educationmultiplatform.android.profileScreen.components.toolbar.ProfileToolbar
import com.penguins.educationmultiplatform.android.profileScreen.subViews.UserNameAgeView
import com.penguins.educationmultiplatform.android.profileScreen.viewModel.ProfileScreenViewModel
import com.penguins.educationmultiplatform.android.ui.danceVerticalGradientBackground
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreenView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = danceVerticalGradientBackground)
            .padding(top = 32.dp)
    ) {
        ProfileToolbar()
        ProfileCard()
        ProfileTabs()
    }
}

@Composable
fun OldVersion(
    viewModel: ProfileScreenViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        UserNameAgeView(name = state.value.userName, age = state.value.age)
        EducationButton(
            text = "Выйти"
        ) { viewModel.logout() }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun ProfilePreview() {
    ProfileScreenView()
}
