package com.penguins.educationmultiplatform.android.newsScreen.components.list

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.data.News
import com.penguins.educationmultiplatform.android.ui.list.NewsListIntoCategory

@Composable
fun CategoryCardNewsList(news: List<News>, clickNews: () -> Unit) {
    NewsListIntoCategory(
        list = news,
        modifier = Modifier
            .padding(top = 32.dp),
        onItemClick = clickNews
    )
}
