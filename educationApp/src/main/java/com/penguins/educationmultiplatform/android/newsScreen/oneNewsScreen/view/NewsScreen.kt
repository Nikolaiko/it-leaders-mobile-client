package com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.newsScreen.common.components.image.NewsCardImage
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton

@Composable
fun NewsScreen() {
    Column(
        modifier = Modifier
    ) {
        NewsToolbar()
        NewsImage()
        NewsText()
    }
}

@Composable
fun NewsToolbar(
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(top = 56.dp, start = 32.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        ImageButton(
            onClick = onClick,
            imageId = R.drawable.ic_back
        )
    }
}

@Composable
fun NewsImage() {
    Box(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    ) {
        NewsCardImage(
            imageId = R.drawable.png_debug_last_news,
            isBackgroundVisible = false,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun NewsText() {
    Column(
        modifier = Modifier
            .padding(top = 32.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "title",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        Text(
            text = "text",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    NewsScreen()
}
