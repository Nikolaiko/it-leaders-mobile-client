package com.penguins.educationmultiplatform.android.profileScreen.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.mapScreen.ui.dancingSchoolColor
import com.penguins.educationmultiplatform.android.profileScreen.data.User
import com.penguins.educationmultiplatform.android.profileScreen.data.debug.getRatingUsers
import com.penguins.educationmultiplatform.android.ui.body2RegularTextStyle
import com.penguins.educationmultiplatform.android.ui.heading3BoldTextStyle
import com.penguins.educationmultiplatform.android.ui.images.UserImage

@Composable
fun InvitationList(items: List<User> = getRatingUsers()) {
    Column {
        for (item in items) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FriendInvitationCard(
                        modifier = Modifier.weight(1f),
                        user = item
                    )
                }
            }
        }
    }
}

@Composable
fun FriendInvitationCard(
    modifier: Modifier,
    user: User
) {
    Row(
        modifier = modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserImage(modifier = Modifier.size(48.dp))
        UserNameText(user.fullName)
    }
    Text(//fixed
        text = "Пригласить",
        color = dancingSchoolColor,
        style = body2RegularTextStyle,
        modifier = Modifier.padding(end = 16.dp)
    )
}

@Composable
fun InvitationTitle() {//fixed
    Text(
        text = "Title",
        style = heading3BoldTextStyle,
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
    )
}
