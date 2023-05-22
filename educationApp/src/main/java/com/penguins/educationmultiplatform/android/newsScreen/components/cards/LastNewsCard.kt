package com.penguins.educationmultiplatform.android.newsScreen.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.penguins.educationmultiplatform.android.R

@Composable
fun LastNewsCardImage(imageId: Int) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = IMAGE_CARD_DESCRIPTION,
        contentScale = ContentScale.Crop,
        modifier = Modifier
    )
    Image(
        painter = painterResource(id = R.drawable.ic_news_background),
        contentDescription = IMAGE_CARD_DESCRIPTION,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier
            .fillMaxWidth()
    )
}
