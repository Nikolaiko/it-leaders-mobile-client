package com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.view

import androidx.compose.foundation.background
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.penguins.educationmultiplatform.android.mapScreen.ui.clickedMapButtonColor
import com.penguins.educationmultiplatform.android.navigation.graps.NewsNavHost
import com.penguins.educationmultiplatform.android.navigation.navigation.NewsNavigation
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.column.AllNewsColumn
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.bottomSheet.FilterBottomSheet
import com.penguins.educationmultiplatform.android.newsScreen.allNewsScreen.components.toolbar.NewsToolbar
import com.penguins.educationmultiplatform.android.ui.allNewsGradientBackground
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsListScreen() {
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

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
        Column(
            modifier = Modifier
                .background(brush = allNewsGradientBackground)
                .padding(top = 32.dp)
        ) {
            NewsToolbar()
            AllNewsColumn()
        }
    }
}

@Composable
fun NewsActivity(
    navigation: NewsNavigation = get(),
    navController: NavHostController = rememberNavController()
) {
    NewsNavHost(navController)
//    if (navigation.getNavigator() != null) {
        navigation.setNavigator(navController)
//    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    NewsListScreen()
}
