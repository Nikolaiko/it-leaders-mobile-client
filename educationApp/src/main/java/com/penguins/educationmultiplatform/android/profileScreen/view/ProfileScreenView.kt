package com.penguins.educationmultiplatform.android.profileScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.profileScreen.components.card.ProfileCard
import com.penguins.educationmultiplatform.android.profileScreen.components.tabs.mainTabs.ProfileTabs
import com.penguins.educationmultiplatform.android.profileScreen.components.toolbar.ProfileToolbar
import com.penguins.educationmultiplatform.android.ui.danceVerticalGradientBackground

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

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun ProfilePreview() {
    ProfileScreenView()
}
