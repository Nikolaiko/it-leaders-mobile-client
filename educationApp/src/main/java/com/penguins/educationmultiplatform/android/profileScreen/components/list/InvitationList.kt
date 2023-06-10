package com.penguins.educationmultiplatform.android.profileScreen.components.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.profileScreen.data.model.User
import com.penguins.educationmultiplatform.android.profileScreen.data.debug.getUsersForInvitation
import com.penguins.educationmultiplatform.android.ui.heading3BoldTextStyle
import com.penguins.educationmultiplatform.android.ui.images.UserImage
import com.penguins.educationmultiplatform.android.ui.linksBoldTextStyle
import com.penguins.educationmultiplatform.android.ui.primary500

@Composable
fun InvitationList(items: List<User> = getUsersForInvitation()) {
    Column {
        for (item in items) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .padding(top = 14.dp)
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
        UserImage(
            modifier = Modifier.size(48.dp),
            imageId = user.imageId
        )
        UserNameText(user.fullName)
    }
    Text(//fixed
        text = "Пригласить",
        color = primary500,
        style = linksBoldTextStyle,
        modifier = Modifier.padding(end = 16.dp)
    )
}

@Composable
fun InvitationTitle() {//fixed
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Box(
            modifier = Modifier,
//                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Пригласить к нам",
                style = heading3BoldTextStyle,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 16.dp)
            )
        }
//        Box(
//            modifier = Modifier.fillMaxHeight(),
//            contentAlignment = Alignment.Center
//        ) {
//            Divider(
//                modifier = Modifier
//                    .padding(top = 24.dp),
//                color = Color.Gray,//fixed
//                thickness = 1.dp
//            )
//        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun InvitationListPreview() {
    InvitationTitle()
}
