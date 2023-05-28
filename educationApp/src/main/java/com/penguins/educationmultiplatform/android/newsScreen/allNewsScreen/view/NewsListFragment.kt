package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.column.AllNewsColumn
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.toolbar.NewsToolbar
import com.penguins.educationmultiplatform.android.ui.allNewsGradientBackground

@Composable
fun NewsListScreen() {
    Column(
        modifier = Modifier
            .background(brush = allNewsGradientBackground)
            .padding(top = 32.dp)
    ) {
        NewsToolbar()
        AllNewsColumn()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    NewsListScreen()
}
