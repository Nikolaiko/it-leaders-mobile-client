package com.penguins.educationmultiplatform.android.newsScreen.common.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.common.components.titleRow.NewsTitleRow
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News

@Composable
fun HorizontalListWithTitle(
    category: String,
    news: List<News>,
    clickCategory: (String) -> Unit,
    clickNews: (News) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        NewsTitleRow(
            category,
            clickCategory
        )
        HorizontalNewsList(
            list = news,
            modifier = Modifier.padding(top = 16.dp),
            onItemClick = clickNews,
        )
    }
}

@Composable
fun VerticalListWithTitle(
    title: String,
    news: List<News>,
    onClickNews: (News) -> Unit,
    onClickSeeAll: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        NewsTitleRow(
            title = title,
            clickCategory = onClickSeeAll
        )
        VerticalNewsList(
            list = news,
            modifier = Modifier.padding(top = 16.dp),
            onItemClick = onClickNews
        )
    }
}
