package com.penguins.educationmultiplatform.android.profileScreen.components.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.profileScreen.components.chips.ProfileChips
import com.penguins.educationmultiplatform.android.profileScreen.data.model.ProfileEvent
import com.penguins.educationmultiplatform.android.profileScreen.viewModel.ProfileViewModel
import com.penguins.educationmultiplatform.android.ui.body1RegularTextStyle
import com.penguins.educationmultiplatform.android.ui.heading2TextStyle
import com.penguins.educationmultiplatform.android.ui.images.ProfileUserImage
import com.penguins.educationmultiplatform.android.ui.images.UserImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileCard(viewModel: ProfileViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp)
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min),
        ) {
            UserInfo(
                modifier = Modifier.weight(1f),
                fullName = state.value.userName,
                age = state.value.age,
                score = state.value.rating
            )
            ProfileUserImage(
                modifier = Modifier.size(96.dp),
                imageUrl = state.value.imageUrl
            )
        }
        ProfileChips(
            categories = state.value.categories,
            onClick = { viewModel.onEvent(ProfileEvent.ClickCategory(it)) }
        )
    }
}

@Composable
fun UserInfo(
    modifier: Modifier,
    fullName: String,
    age: String,
    score: String
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = fullName,
                    style = heading2TextStyle
                )
                Text(
                    text = age,
                    style = body1RegularTextStyle
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                modifier = Modifier,
                text = score,
                style = heading2TextStyle
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun ProfilePreview() {
    ProfileCard()
}