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
import com.penguins.educationmultiplatform.android.profileScreen.data.User
import com.penguins.educationmultiplatform.android.profileScreen.data.debug.getRatingUsers
import com.penguins.educationmultiplatform.android.ui.body2RegularTextStyle
import com.penguins.educationmultiplatform.android.ui.heading3BoldTextStyle
import com.penguins.educationmultiplatform.android.ui.images.UserImage
import com.penguins.educationmultiplatform.android.ui.neutral900

@Composable
fun FriendsList(items: List<User> = getRatingUsers()) {
    Column {
        for (item in items) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    FriendCard(
                        modifier = Modifier.weight(1f),
                        user = item
                    )
                }
            }
        }
    }
}

@Composable
fun FriendCard(
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
    UserRatingText(rating = user.rating)
}

@Composable
fun UserNameText(fullName: String?) {
    Text(
        text = fullName ?: "",
        modifier = Modifier.padding(start = 14.dp),
        style = body2RegularTextStyle,
        color = neutral900
    )
}

@Composable
fun UserRatingText(rating: String?) {
    Text(
        text = rating ?: "",
        modifier = Modifier.padding(end = 16.dp),
        style = heading3BoldTextStyle
    )
}
