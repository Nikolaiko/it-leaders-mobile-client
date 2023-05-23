package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.data.getDebugNews
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.cards.CategoryCard
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.titleRow.CategoryCardTitleRow
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.listOfNews
import com.penguins.educationmultiplatform.android.ui.list.VerticalNewsList

@Composable
fun CategoryNews() {
    val news = listOfNews()

    LastNewsCard(
        news = getDebugNews(),
        onClick = {}
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
    ) {
        CategoryCardTitleRow(
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
