package com.penguins.educationmultiplatform.android.categoryScreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.categoryScreen.components.LastNewsCard
import com.penguins.educationmultiplatform.android.categoryScreen.components.CategoryToolbar
import com.penguins.educationmultiplatform.android.categoryScreen.data.getDebugNews
import com.penguins.educationmultiplatform.android.newsScreen.components.titleRow.CategoryCardTitleRow
import com.penguins.educationmultiplatform.android.newsScreen.data.listOfNews
import com.penguins.educationmultiplatform.android.ui.list.VerticalNewsList
import com.penguins.educationmultiplatform.android.utils.constants.EMPTY_STRING

@Composable
fun CategoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryToolbar()
        CategoryNews()
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
            title = news.first().category ?: EMPTY_STRING,
            clickCategory = {}
        )
        VerticalNewsList(
            list = news.take(2),
            modifier = Modifier.padding(top = 16.dp),
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    CategoryScreen()
}

