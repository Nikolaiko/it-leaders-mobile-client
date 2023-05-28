package com.penguins.educationmultiplatform.android.newsScreen.common.components.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.newsScreen.common.components.image.NewsCardImage
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HorizontalNewsCard(
    news: News,
    onClick: (News) -> Unit
) {
    Card(
        modifier = Modifier
            .size(200.dp)
            .padding(end = 16.dp),
        shape = RoundedCornerShape(15.dp),
        onClick = { onClick(news) }
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.CenterStart,
        ) {
            NewsCardImage(
                imageId = news.imageId ?: DEFAULT_IMAGE_NEWS,
                contentScale = ContentScale.Crop,
                isBackgroundVisible = true
            )
            NewsCardTexts(
                heading = news.heading ?: EMPTY_STRING,
                title = news.title ?: EMPTY_STRING
            )
        }
    }
}

@Composable
fun NewsCardTexts(
    heading: String = EMPTY_STRING,
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(bottom = 10.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
           if (heading.isNotEmpty()) {
               HeadingText(heading)
           }
            Text(
                text = title,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun HeadingText(heading: String) {
    Box(
        modifier = Modifier
            .alpha(0.8f)
            .background(
                Color.White,
                RoundedCornerShape(15.dp)
            )
            .padding(horizontal = 10.dp, vertical = 2.dp)
    ) {
        Text(
            text = heading,
            color = Color.DarkGray,
            fontSize = 12.sp
        )
    }
}

@SuppressLint("NonConstantResourceId")
const val DEFAULT_IMAGE_NEWS = R.drawable.png_debug_news
