package com.penguins.educationmultiplatform.android.newsScreen.common.components.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.newsScreen.common.components.image.NewsCardImage
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LastNewsCard(
    news: News,
    onClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenHeightDp
    val height = screenWidth * 0.18

    Card(
        modifier = Modifier
            .padding(top = 24.dp)
            .heightIn(min = 154.dp)
            .height(height.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        onClick = onClick,
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.CenterStart,
        ) {
            NewsCardImage(
                imageUrl = news.imageUrl,
                contentScale = ContentScale.FillWidth,
                isBackgroundVisible = true
            )
            NewsCardTexts(
                title = news.title ?: EMPTY_STRING
            )
        }
    }
}
