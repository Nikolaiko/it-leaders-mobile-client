package com.penguins.educationmultiplatform.android.newsScreen.headingNews.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.VerticalNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

@Composable
fun HeadingList() {
    Column(
        modifier = Modifier
//            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
    ) {
        emptyList<News>().forEach { _ ->//(state.value.findingNews) {
            VerticalNewsCard(
                news = News(),//it,
                onClick = {},//{ news -> viewModel.onEvent(SearchNewsEvents.OpenNews(news)) }
            )
        }
    }

}