package com.penguins.educationmultiplatform.android.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun ListPreview() {
    NewsList(getDebugProductList())
}

private fun getDebugProductList() = listOf("item1", "item2")
