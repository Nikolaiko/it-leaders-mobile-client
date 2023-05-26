package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components.BottomSheetCoursesFilter
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components.TopBarCourses
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.CoursesUiEvents
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.CoursesViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoursesScreen(viewModel: CoursesViewModel = koinViewModel()) {


    val state = viewModel.state.collectAsState()

    val sheetFilterState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldFilterState =
        rememberBottomSheetScaffoldState(bottomSheetState = sheetFilterState)
    val scope = rememberCoroutineScope()
    DisposableEffect(key1 = scope, effect =
    { onDispose { scope.cancel() } })
    BottomSheetScaffold(
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                BottomSheetCoursesFilter(state.value.filters, viewModel::onEvent)
            }
        },
        sheetPeekHeight = 60.dp,
        scaffoldState = scaffoldFilterState,
        floatingActionButtonPosition = FabPosition.Center,
        sheetShape = RoundedCornerShape(topEnd = 0.dp, topStart = 0.dp),
        sheetBackgroundColor = Color.Unspecified,
        sheetElevation = 0.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            TopBarCourses(text = state.value.search, onTextChange = {viewModel.onEvent(CoursesUiEvents.SetSearchField(it))}) {

                scope.launch {
                    if (sheetFilterState.isCollapsed)
                        sheetFilterState.expand()
                    else
                        sheetFilterState.collapse()
                }

            }
            Spacer(modifier = Modifier.height(19.dp))


        }
    }
}