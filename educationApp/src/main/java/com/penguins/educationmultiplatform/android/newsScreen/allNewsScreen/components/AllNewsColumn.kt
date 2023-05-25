package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.NewsListEvents
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.viewModel.NewsListViewModel
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.LastNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.common.components.list.HorizontalListWithTitle
import com.penguins.educationmultiplatform.android.newsScreen.common.components.list.VerticalListWithTitle
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllNewsColumn(
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .padding(bottom = 64.dp)
            .padding(horizontal = 16.dp)
    ) {
        item {
            LastNewsCard(news = state.value.list.last().second.last()) {
                viewModel.onEvent(NewsListEvents.OpenNews)
            }
        }

        itemsIndexed(state.value.list) { index, item ->

            when (index % 2) {
                0 -> VerticalListWithTitle(
                    title = item.first,
                    news = item.second,
                    onClickSeeAll = { viewModel.onEvent(NewsListEvents.OpenCategory) },
                    onClickNews = { viewModel.onEvent(NewsListEvents.OpenNews) }
                )
                1 -> HorizontalListWithTitle(
                    title = item.first,
                    news = item.second,
                    onClickSeeAll = { viewModel.onEvent(NewsListEvents.OpenCategory) },
                    onClickNews = { viewModel.onEvent(NewsListEvents.OpenNews) }
                )
            }
        }
    }
}