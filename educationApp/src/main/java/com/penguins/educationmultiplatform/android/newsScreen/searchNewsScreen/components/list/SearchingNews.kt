package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.components.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.VerticalNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data.SearchNewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.viewModel.SearchNewsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchingNews(
    viewModel: SearchNewsViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
    ) {
        items(state.value.findingNews) {
            VerticalNewsCard(
                news = it,
                onClick = { news -> viewModel.onEvent(SearchNewsEvents.OpenNews(news)) }
            )
        }
    }
}
