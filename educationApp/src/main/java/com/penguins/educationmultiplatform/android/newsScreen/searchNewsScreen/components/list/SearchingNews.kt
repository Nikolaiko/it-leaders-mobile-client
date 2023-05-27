package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.components.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.VerticalNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

@Composable
fun SearchingNews(
    list: List<News>,
    modifier: Modifier = Modifier,
    onItemClick: (News) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(list) {
            VerticalNewsCard(news = it, onClick = onItemClick)
        }
    }
}
