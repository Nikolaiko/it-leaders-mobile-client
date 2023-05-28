package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.column

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

        val lastNews = state.value.lastNews
        if (lastNews != null) {
            item {
                LastNewsCard(news = lastNews) {
                    viewModel.onEvent(NewsListEvents.OpenNews(lastNews))
                }
            }
        }

        itemsIndexed(state.value.list) { index, item ->

            when (index % 2) {
                0 -> VerticalListWithTitle(
                    title = item.first,
                    news = item.second,
                    onClickSeeAll = { category ->
                        viewModel.onEvent(NewsListEvents.OpenCategory(category))
                    },
                    onClickNews = { news -> viewModel.onEvent(NewsListEvents.OpenNews(news)) }
                )
                1 -> HorizontalListWithTitle(
                    title = item.first,
                    news = item.second,
                    isHeadingVisible = true,
                    onClickSeeAll = { category ->
                        viewModel.onEvent(NewsListEvents.OpenCategory(category))
                    },
                    onClickNews = { news -> viewModel.onEvent(NewsListEvents.OpenNews(news)) }
                )
            }
        }
    }
}