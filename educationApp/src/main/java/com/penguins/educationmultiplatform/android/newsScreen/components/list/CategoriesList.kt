package com.penguins.educationmultiplatform.android.newsScreen.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.data.listOfCategories
import com.penguins.educationmultiplatform.android.ui.list.CategoryList

@Composable
fun CategoriesList() {
    val categories = listOfCategories()
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        CategoryList(list = categories)
    }
}
