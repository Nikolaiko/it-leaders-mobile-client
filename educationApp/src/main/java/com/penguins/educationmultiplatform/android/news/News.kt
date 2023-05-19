package com.penguins.educationmultiplatform.android.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.news.components.cards.CategoryCardTitle
import com.penguins.educationmultiplatform.android.news.components.cards.DividerAndTextButton
import com.penguins.educationmultiplatform.android.news.components.toolbar.NewsToolbar
import com.penguins.educationmultiplatform.android.ui.list.NewsCategoryList

@Composable
fun News() {
    Column {
        NewsToolbar()
        CategoriesList()
    }
}

@Composable
fun CategoriesList() {
    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
    ) {
        CategoryCard()
//        NewsList(items = listOf("item1", "item2"))
    }
}

@Composable
fun CategoryCard() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CategoryCardTitleRow()
        CategoryCardNewsList()
    }
}

@Composable
fun CategoryCardTitleRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        CategoryCardTitle()
        DividerAndTextButton()
    }
}

@Composable
fun CategoryCardNewsList() {
    NewsCategoryList(
        modifier = Modifier
            .padding(top = 32.dp),
        items = listOf(
        "item1                  ",
        "item2                  ",
        "item1                  ",
        "item2                  ",
        "item1                  ",
        "item2                  "
    ))
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    News()
}
