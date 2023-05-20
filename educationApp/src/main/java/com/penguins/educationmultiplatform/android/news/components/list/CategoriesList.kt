package com.penguins.educationmultiplatform.android.news.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.news.debug.listOfCategories
import com.penguins.educationmultiplatform.android.ui.list.CategoryList

@Composable
fun CategoriesList() {
    val categories = listOfCategories()
    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
    ) {
        CategoryList(list = categories)
    }
}
