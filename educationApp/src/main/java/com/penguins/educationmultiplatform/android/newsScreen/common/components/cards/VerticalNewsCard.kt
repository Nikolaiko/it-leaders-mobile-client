package com.penguins.educationmultiplatform.android.newsScreen.common.components.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.News
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.DEFAULT_IMAGE_NEWS
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.NewsCardImage
import com.penguins.educationmultiplatform.android.ui.textGrayColor
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING

@Composable
fun VerticalNewsCard(
    news: News,
    onClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val size = screenWidth * 0.27

    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .height(size.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {
            NewsCardImage(
                imageId = news.imageId ?: DEFAULT_IMAGE_NEWS,
                contentScale = ContentScale.Crop,
                isBackgroundVisible = false
            )
        }

        Column (
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxHeight()
        ) {
            Text(
                text = news.title ?: EMPTY_STRING,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = news.text ?: EMPTY_STRING,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxHeight(),
                overflow = TextOverflow.Ellipsis,
                color = textGrayColor
            )
        }
    }

}