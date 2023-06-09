package com.penguins.educationmultiplatform.android.profileScreen.components.tabs.ratingTabs

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.profileScreen.components.list.FriendsList
import com.penguins.educationmultiplatform.android.profileScreen.components.list.InvitationList
import com.penguins.educationmultiplatform.android.profileScreen.components.list.InvitationTitle

@Composable
fun Rating() {
    LazyColumn(
        modifier = Modifier
            .padding(top = 24.dp)
    ) {
        item { FriendsList() }
        item {
            InvitationTitle()
            InvitationList()
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun RatingListPreview() {
    Rating()
}


