package com.penguins.educationmultiplatform.android.newsScreen.common.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.HorizontalNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.VerticalNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.NewsListEvents
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.viewModel.NewsListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(state.value.list) { item ->
            HorizontalListWithTitle(
                category = item.first,
                news = item.second,
                clickCategory = { viewModel.onEvent(NewsListEvents.OpenCategory) },
                clickNews = { viewModel.onEvent(NewsListEvents.OpenNews) }
            )
        }
    }
}

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
