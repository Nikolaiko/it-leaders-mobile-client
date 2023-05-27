package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.components.newsColumn

import androidx.compose.runtime.Composable
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.LastNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.common.components.list.HorizontalListWithTitle
import com.penguins.educationmultiplatform.android.newsScreen.common.components.list.VerticalListWithTitle
import com.penguins.educationmultiplatform.android.newsScreen.common.data.News
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING

@Composable
fun CategoryNews(
    news: List<Pair<String, List<News>>>,
    lastNews: News,
    onClickNews: (News) -> Unit,
    onClickSeeAll: (String) -> Unit
) {
    LastNewsCard(
        news = lastNews,
        onClick = { onClickNews(lastNews) }
    )

    VerticalListWithTitle(
        title = news.firstOrNull()?.first ?: EMPTY_STRING,
        news = news.firstOrNull()?.second ?: emptyList(),
        onClickNews = onClickNews,
        onClickSeeAll = onClickSeeAll
    )

    HorizontalListWithTitle(
        title = news.lastOrNull()?.first ?: EMPTY_STRING,
        news = news.lastOrNull()?.second ?: emptyList(),
        onClickSeeAll = onClickSeeAll,
        onClickNews = onClickNews
    )
}
