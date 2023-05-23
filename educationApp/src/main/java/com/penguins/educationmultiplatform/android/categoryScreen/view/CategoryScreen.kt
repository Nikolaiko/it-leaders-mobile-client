package com.penguins.educationmultiplatform.android.categoryScreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.categoryScreen.components.CategoryToolbar
import com.penguins.educationmultiplatform.android.categoryScreen.components.LastNewsCard
import com.penguins.educationmultiplatform.android.categoryScreen.data.getDebugNews
import com.penguins.educationmultiplatform.android.newsScreen.components.cards.CategoryCard
import com.penguins.educationmultiplatform.android.newsScreen.components.titleRow.CategoryCardTitleRow
import com.penguins.educationmultiplatform.android.newsScreen.data.listOfNews
import com.penguins.educationmultiplatform.android.ui.list.VerticalNewsList

@Composable
fun CategoryScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f, false),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CategoryToolbar()
            CategoryNews()
        }
    }
}

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

@Preview(showBackground = true, widthDp = 320, heightDp = 1640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    CategoryScreen()
}

