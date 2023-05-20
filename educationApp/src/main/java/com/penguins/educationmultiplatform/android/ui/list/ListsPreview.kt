package com.penguins.educationmultiplatform.android.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.penguins.educationmultiplatform.android.newsScreen.data.listOfCategories

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun ListPreview() {
    CategoryList(listOfCategories())
}

