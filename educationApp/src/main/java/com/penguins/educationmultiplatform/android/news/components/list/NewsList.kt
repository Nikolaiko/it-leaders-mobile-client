package com.penguins.educationmultiplatform.android.news.components.list

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.news.debug.News
import com.penguins.educationmultiplatform.android.ui.list.NewsListIntoCategory

@Composable
fun CategoryCardNewsList(news: List<News>) {
    NewsListIntoCategory(
        list = news,
        modifier = Modifier
            .padding(top = 32.dp)
    )
}
