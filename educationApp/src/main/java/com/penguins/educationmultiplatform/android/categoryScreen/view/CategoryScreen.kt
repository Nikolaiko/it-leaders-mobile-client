package com.penguins.educationmultiplatform.android.categoryScreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.categoryScreen.components.LastNewsCard
import com.penguins.educationmultiplatform.android.categoryScreen.components.CategoryToolbar
import com.penguins.educationmultiplatform.android.newsScreen.data.News

@Composable
fun CategoryScreen() {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CategoryToolbar()
        CategoryNews()
    }
}

@Composable
fun CategoryNews() {
    LastNewsCard(
        news = News(),
        onClick = {}
    )
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    CategoryScreen()
}

