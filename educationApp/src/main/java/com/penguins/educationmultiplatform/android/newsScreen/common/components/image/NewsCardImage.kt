package com.penguins.educationmultiplatform.android.newsScreen.common.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.SubcomposeAsyncImage
import com.penguins.educationmultiplatform.android.R

@Composable
fun NewsCardImage(
    imageUrl: String? = null,
    isBackgroundVisible: Boolean,
    contentScale: ContentScale
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = IMAGE_CARD_DESCRIPTION,
        contentScale = contentScale,
        loading = {
            CircularProgressIndicator()
        },
        modifier = Modifier
            .fillMaxWidth(),
    )
    if (isBackgroundVisible) {
        Image(
            painter = painterResource(id = R.drawable.ic_news_background),
            contentDescription = IMAGE_CARD_DESCRIPTION,
            contentScale = contentScale,
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

const val IMAGE_CARD_DESCRIPTION = "Image of news."
