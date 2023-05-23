package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.components.newsColumn

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.common.debugData.getDebugLastNews
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.cards.CategoryCard
import com.penguins.educationmultiplatform.android.newsScreen.common.components.titleRow.NewsTitleRow
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.listOfNews
import com.penguins.educationmultiplatform.android.newsScreen.common.components.cards.LastNewsCard
import com.penguins.educationmultiplatform.android.newsScreen.common.components.list.VerticalNewsList

@Composable
fun CategoryNews() {
    val news = listOfNews()

    LastNewsCard(
        news = getDebugLastNews(),
        onClick = {}
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        NewsTitleRow(
            title = "Подзаголовок",
            clickCategory = {}
        )
        VerticalNewsList(
            list = news.take(2),
            modifier = Modifier.padding(top = 16.dp),
            onItemClick = {}
        )
    }

    CategoryCard(
        category = "Подзаголовок",
        news = news,
        clickCategory = {},
        clickNews = {}
    )
}
