package com.penguins.educationmultiplatform.android.profileScreen.components.tabs.ratingTabs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.profileScreen.data.User
import com.penguins.educationmultiplatform.android.profileScreen.data.debug.getRatingUsers
import com.penguins.educationmultiplatform.android.ui.body2RegularTextStyle
import com.penguins.educationmultiplatform.android.ui.heading3BoldTextStyle
import com.penguins.educationmultiplatform.android.ui.images.UserImage
import com.penguins.educationmultiplatform.android.ui.neutral900

@Composable
fun Rating(item: List<User> = getRatingUsers()) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 24.dp)
    ) {
        items(item) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        UserImage(modifier = Modifier.size(48.dp))
                        UserNameText(it.fullName)
                    }
                    UserRatingText(rating = it.rating)
                }
            }
        }
    }
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

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun RatingListPreview() {
    Rating()
}


