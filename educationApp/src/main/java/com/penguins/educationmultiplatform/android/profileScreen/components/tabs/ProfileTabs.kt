package com.penguins.educationmultiplatform.android.profileScreen.components.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.ui.body1RegularTextStyle
import com.penguins.educationmultiplatform.android.ui.primary700

@Composable
fun ProfileTabs(
    selectedTabIndex: Int = 0
) {
    TabRow(
        modifier = Modifier
            .padding(top = 32.dp),
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent
    ) {
        Tab(
            selected = true,
            onClick = { /*TODO*/ }
        ) {
            TabTitle(text = "Мои курсы")
        }
        Tab(selected = false, onClick = { /*TODO*/ }) {
            TabTitle(text = "Рейтинг")
        }
    }
    ProfileTabsScreen(selectedTabIndex)
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
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
    ) {
        when (selectedTabIndex) {
            0 -> MyCourses()
            else -> Rating()
        }
    }
}
