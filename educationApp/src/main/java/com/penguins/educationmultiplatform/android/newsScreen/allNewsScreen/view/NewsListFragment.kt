package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.mapScreen.ui.clickedMapButtonColor
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.bottomSheet.FilterBottomSheet
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.toolbar.NewsToolbar
import com.penguins.educationmultiplatform.android.newsScreen.common.components.list.CategoryList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsListScreen() {
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
//    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            FilterBottomSheet()
        },
        sheetElevation = 2.dp,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
        sheetBackgroundColor = clickedMapButtonColor
    ) {
        Column(modifier = Modifier.padding(top = 32.dp)) {
            NewsToolbar()
            CategoryList()
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    NewsListScreen()
}
