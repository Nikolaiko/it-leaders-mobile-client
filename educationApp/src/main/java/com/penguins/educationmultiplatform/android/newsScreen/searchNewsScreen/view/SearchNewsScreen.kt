package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.mapScreen.ui.clickedMapButtonColor
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.components.bottomSheet.FilterBottomSheet
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.components.list.SearchingNews
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.components.toolbar.SearchNewsToolbar
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data.SearchNewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.viewModel.SearchNewsViewModel
import com.penguins.educationmultiplatform.android.ui.allNewsGradientBackground
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchNewsScreen(
    category: String? = null,
    viewModel: SearchNewsViewModel = koinViewModel()
) {
    viewModel.onEvent(SearchNewsEvents.SetCategory(category))

    val state = viewModel.state.collectAsState()
    val categories = state.value.categories

    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
           FilterBottomSheet()
        },
        sheetElevation = 2.dp,
        sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
        sheetBackgroundColor = clickedMapButtonColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    brush = when {
                        categories.isEmpty() || categories.size > 1 -> allNewsGradientBackground
                        else -> categories.first().background
                    }
                )
                .padding(bottom = 64.dp, top = 32.dp)
        ) {
            SearchNewsToolbar(
                openBottomSheet = { scope.launch { bottomSheetState.expand() } }
            )
            SearchingNews()
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun NewsPreview() {
    SearchNewsScreen()
}
