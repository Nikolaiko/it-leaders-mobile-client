package com.penguins.educationmultiplatform.android.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton
import com.penguins.educationmultiplatform.android.ui.list.NewsList

@Composable
fun News() {
    Column {
        NewsToolbar()
        CategoriesList()
    }
}

@Composable
fun NewsToolbar() {
    Row (modifier = Modifier.wrapContentSize()) {
        SearchNewsEditText()
        FilterButton()
    }
}

@Composable
fun SearchNewsEditText() {
    var text by remember { mutableStateOf(TITLE_SEARCH_TEXT_FIELD) }

    TextField(
        modifier = Modifier,
//            .wrapContentHeight()
//            .padding(24.dp),
        value = text,
        onValueChange = { text = it }
    )
}

@Composable
fun FilterButton() {
    ImageButton(
        onClick = {  },
        imageId = R.drawable.ic_filter,
        modifier = Modifier
            .wrapContentHeight()
            .padding(top = 24.dp, end = 16.dp)
    )
}

@Composable
fun CategoriesList() {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {
        NewsList(items = listOf("item1", "item2"))
    }
}

@Composable
fun CategoryNewsCard() {

}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    News()
}

const val TITLE_SEARCH_TEXT_FIELD = "Поиск"
