package com.penguins.educationmultiplatform.android.newsScreen.common.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.HorizontalNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.VerticalNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

@Composable
fun HorizontalNewsList(
    list: List<News>,
    modifier: Modifier = Modifier,
    onItemClick: (News) -> Unit
) {
    LazyRow(
        modifier = modifier
    ) {
        items(list) { news ->
            HorizontalNewsCard(news = news, onClick = onItemClick)
        }
    }
}

@Composable
fun VerticalNewsList(
    list: List<News>,
    modifier: Modifier = Modifier,
    onItemClick: (News) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        for (news in list) {
            VerticalNewsCard(news = news, onClick = onItemClick)
        }
    }
}
