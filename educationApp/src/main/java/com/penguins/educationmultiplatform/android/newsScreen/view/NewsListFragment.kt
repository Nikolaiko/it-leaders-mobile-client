package com.penguins.educationmultiplatform.android.newsScreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.penguins.educationmultiplatform.android.newsScreen.components.list.CategoriesList
import com.penguins.educationmultiplatform.android.newsScreen.components.toolbar.NewsToolbar

@Composable
fun NewsListFragment() {
    Column {
        NewsToolbar()
        CategoriesList()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    NewsListFragment()
}
