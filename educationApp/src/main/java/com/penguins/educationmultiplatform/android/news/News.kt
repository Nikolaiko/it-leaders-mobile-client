package com.penguins.educationmultiplatform.android.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.news.components.toolbar.NewsToolbar
import com.penguins.educationmultiplatform.android.ui.buttons.TextButton
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
        CategoryCardTitle()
        CategoryCardNewsList()
    }
}

@Composable
fun CategoryCardTitle() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val width = screenWidth * 0.73

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text = "Category title",
                modifier = Modifier.wrapContentWidth()
            )
        }
//        Line()
        Box (
            modifier = Modifier
                .wrapContentWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            TextButton(
                onClick = { },
                text = "Смотреть всё",
                modifier = Modifier
            )
        }
    }
}

@Composable
fun CategoryCardNewsList() {
    NewsCategoryList(items = listOf(
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

const val TITLE_SEARCH_TEXT_FIELD = "Поиск"
