package com.penguins.educationmultiplatform.android.ui.images

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.ui.primaryGray

@Composable
fun UserImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null
) {
    Card(
        modifier = modifier,
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(id = R.drawable.photo_teacher),
            modifier = Modifier
                .background(
                    color = primaryGray,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileUserImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null
) {
    Box {
        Card(
            modifier = modifier,
            shape = CircleShape,
            border = BorderStroke(2.dp, Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.photo_teacher),
                modifier = Modifier
                    .background(
                        color = primaryGray,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = modifier,
            contentAlignment = Alignment.BottomEnd
        ) {
            Icon(
                modifier = Modifier.size(24.dp)
                    .padding(end = 4.dp, bottom = 4.dp),//fixed
                painter = painterResource(id = R.drawable.ic_search),//fixed
                contentDescription = "Profile settings"
            )
        }
    }

}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun ImagePreview() {
    ProfileUserImage(
        modifier = Modifier.size(96.dp)
    )
}