package com.penguins.educationmultiplatform.android.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.penguins.educationmultiplatform.android.news.components.cards.CategoryCard
import com.penguins.educationmultiplatform.android.news.components.cards.NewsCard
import com.penguins.educationmultiplatform.android.news.debug.News

@Composable
fun CategoryList(
    list: List<Pair<String, List<News>>>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(list) { item ->
            CategoryCard(item.first, item.second)
        }
    }
}

@Composable
fun NewsListIntoCategory(
    list: List<News>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        items(list) { news ->
            NewsCard(news = news)
        }
    }
}
