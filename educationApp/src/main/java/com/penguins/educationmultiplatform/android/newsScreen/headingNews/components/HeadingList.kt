package com.penguins.educationmultiplatform.android.newsScreen.headingNews.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.VerticalNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

@Composable
fun HeadingList(
    news: List<News>,
    onClickNews: (News) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxHeight()
    ) {
        news.forEach {
            VerticalNewsCard(
                news = it,
                onClick = { news -> onClickNews(news) }
            )
        }
    }

}