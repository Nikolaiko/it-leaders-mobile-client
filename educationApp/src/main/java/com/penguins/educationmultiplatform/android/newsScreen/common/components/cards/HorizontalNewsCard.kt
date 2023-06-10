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
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.newsScreen.common.components.image.NewsCardImage
import com.penguins.educationmultiplatform.android.ui.headingTextStyle
import com.penguins.educationmultiplatform.android.ui.neutral900Size12Weight400Style
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HorizontalNewsCard(
    news: News,
    onClick: (News) -> Unit,
    isHeadingVisible: Boolean = false
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
                imageUrl = news.imageUrl,
                contentScale = ContentScale.Crop,
                isBackgroundVisible = true
            )
            NewsCardTexts(
                heading = when (isHeadingVisible) {
                    true -> news.heading ?: EMPTY_STRING
                    false -> EMPTY_STRING
                },
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
                modifier = Modifier.padding(top = 8.dp),
                style = headingTextStyle
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
            style = neutral900Size12Weight400Style
        )
    }
}
