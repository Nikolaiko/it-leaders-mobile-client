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
    title: String,
    news: List<News>,
    isHeadingVisible: Boolean,
    onClickSeeAll: (String) -> Unit,
    onClickNews: (News) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        NewsTitleRow(
            title,
            onClickSeeAll
        )
        HorizontalNewsList(
            list = news,
            modifier = Modifier.padding(top = 16.dp),
            onItemClick = onClickNews,
            isHeadingVisible = isHeadingVisible
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
            list = news.take(LIMIT_VERTICAL_NEWS),
            modifier = Modifier.padding(top = 16.dp),
            onItemClick = onClickNews
        )
    }
}

const val LIMIT_VERTICAL_NEWS = 2
