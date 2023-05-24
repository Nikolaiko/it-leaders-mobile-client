package com.penguins.educationmultiplatform.android.newsScreen.common.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.penguins.educationmultiplatform.android.R

@Composable
fun NewsCardImage(
    imageId: Int,
    isBackgroundVisible: Boolean,
    contentScale: ContentScale
) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = IMAGE_CARD_DESCRIPTION,
        contentScale = contentScale,
        modifier = Modifier.fillMaxWidth()
    )
    if (isBackgroundVisible) {
        Image(
            painter = painterResource(id = R.drawable.ic_news_background),
            contentDescription = IMAGE_CARD_DESCRIPTION,
            contentScale = contentScale,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

const val IMAGE_CARD_DESCRIPTION = "Image of news."
