package com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.commonViews.AppBackToolbar
import com.penguins.educationmultiplatform.android.newsScreen.common.components.image.NewsCardImage
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.data.NewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.oneNewsScreen.viewModel.NewsViewModel
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsScreen(
    news: News? = null,
    viewModel: NewsViewModel = koinViewModel()
) {
    news?.let { viewModel.onEvent(NewsEvents.SetNews(it)) }
    val state = viewModel.state.collectAsState()

    Column {
        Column(
            modifier = Modifier
                .padding(bottom = 64.dp)
                .verticalScroll(rememberScrollState())
                .weight(1f, false)
        ) {
            AppBackToolbar(
                onClick = { viewModel.onEvent(NewsEvents.BackButton) }
            )
            NewsImage(
                image = state.value.news?.imageId ?: R.drawable.png_debug_news
            )
            NewsText(
                title = state.value.news?.title ?: EMPTY_STRING,
                text = state.value.news?.text ?: EMPTY_STRING
            )
        }
    }
}

@Composable
fun NewsImage(image: Int) {
    Box(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
    ) {
        NewsCardImage(
            imageId = image,
            isBackgroundVisible = false,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun NewsText(
    title: String,
    text: String
) {
    Column(
        modifier = Modifier
            .padding(top = 32.dp, end = 16.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        Text(
            text = text,
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
