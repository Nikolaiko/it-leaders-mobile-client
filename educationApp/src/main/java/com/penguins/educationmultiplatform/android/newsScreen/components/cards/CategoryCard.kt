package com.penguins.educationmultiplatform.android.newsScreen.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.components.titleRow.CategoryCardTitleRow
import com.penguins.educationmultiplatform.android.newsScreen.data.News
import com.penguins.educationmultiplatform.android.ui.list.HorizontalNewsList

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
        CategoryCardTitleRow(category, clickCategory)
        HorizontalNewsList(
            list = news,
            modifier = Modifier.padding(top = 32.dp),
            onItemClick = clickNews,
        )
    }
}
