package com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.data.ProfileTabsEnum.*
import com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.data.ProfileTabsEvents
import com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.viewModel.ProfileTabsViewModel
import com.penguins.educationmultiplatform.android.profileScreen.components.tabs.myCoursesTabs.MyCourses
import com.penguins.educationmultiplatform.android.profileScreen.components.tabs.ratingTabs.Rating
import com.penguins.educationmultiplatform.android.ui.body1RegularTextStyle
import com.penguins.educationmultiplatform.android.ui.primary700
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileTabs(viewModel: ProfileTabsViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    TabRow(
        modifier = Modifier
            .padding(top = 32.dp),
        selectedTabIndex = state.value.selectedTab.code,
        backgroundColor = Color.Transparent
    ) {
       ProfileTab(
           title = MY_COURSES.title,
           isSelected = state.value.selectedTab == MY_COURSES,
           onClick = { viewModel.onEvent(ProfileTabsEvents.ClickTab(MY_COURSES)) }
       )
        ProfileTab(
            title = FRIENDS.title,
            isSelected = state.value.selectedTab == FRIENDS,
            onClick = { viewModel.onEvent(ProfileTabsEvents.ClickTab(FRIENDS)) }
        )
    }
    ProfileTabsScreen(state.value.selectedTab.code)
}

@Composable
fun ProfileTab(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Tab(
        selected = isSelected,
        onClick = onClick
    ) {
        TabTitle(text = title)
    }
}

@Composable
fun TabTitle(text: String) {
    Text(
        modifier = Modifier.padding(bottom = 8.dp),
        text = text,
        style = body1RegularTextStyle,
        color = primary700
    )
}

@Composable
fun ProfileTabsScreen(selectedTabIndex: Int) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(bottom = 64.dp)
    ) {
        when (selectedTabIndex) {
            MY_COURSES.code -> MyCourses()
            else -> Rating()
        }
    }
}
