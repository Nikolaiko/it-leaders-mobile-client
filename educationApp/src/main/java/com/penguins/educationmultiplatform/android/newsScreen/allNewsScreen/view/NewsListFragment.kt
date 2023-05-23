package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.toolbar.NewsToolbar
import com.penguins.educationmultiplatform.android.ui.list.CategoryList

@Composable
fun NewsListFragment() {
    Column(modifier = Modifier.padding(top = 32.dp)) {
        NewsToolbar()
        CategoryList()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    NewsListFragment()
}
