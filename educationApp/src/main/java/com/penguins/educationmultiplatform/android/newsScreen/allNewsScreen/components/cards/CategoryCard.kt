package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.common.components.titleRow.NewsTitleRow
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.data.News
import com.penguins.educationmultiplatform.android.newsScreen.common.components.list.HorizontalNewsList

@Composable
fun CategoryCard(
    category: String,
    news: List<News>,
    clickCategory: () -> Unit,
    clickNews:() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        NewsTitleRow(category, clickCategory)
        HorizontalNewsList(
            list = news,
            modifier = Modifier.padding(top = 32.dp),
            onItemClick = clickNews,
        )
    }
}
