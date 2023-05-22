package com.penguins.educationmultiplatform.android.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.components.cards.CategoryCard
import com.penguins.educationmultiplatform.android.newsScreen.components.cards.NewsCard
import com.penguins.educationmultiplatform.android.newsScreen.data.News
import com.penguins.educationmultiplatform.android.newsScreen.data.NewsListEvents
import com.penguins.educationmultiplatform.android.newsScreen.viewModel.NewsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(state.value.list) { item ->
            CategoryCard(
                category = item.first,
                news = item.second,
                clickCategory = { viewModel.onEvent(NewsListEvents.OpenCategory) },
                clickNews = { viewModel.onEvent(NewsListEvents.OpenNews) }
            )
        }
    }
}

@Composable
fun NewsListIntoCategory(
    list: List<News>,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    LazyRow(
        modifier = modifier
    ) {
        items(list) { news ->
            NewsCard(news = news, onClick = onItemClick)
        }
    }
}
