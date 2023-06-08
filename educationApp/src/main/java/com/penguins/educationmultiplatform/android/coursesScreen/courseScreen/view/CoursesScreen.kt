package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavHostController
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components.BottomSheetCoursesFilter
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components.TopBarCourses
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components.VideoCourseItem
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.CoursesUiEvents
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.CoursesViewModel
import com.penguins.educationmultiplatform.android.navigation.routeObject.CoursesTapScreens
import com.penguins.educationmultiplatform.android.ui.gradientCoursesBackground
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoursesScreen(viewModel: CoursesViewModel = koinViewModel(), navController: NavHostController) {
    val state = viewModel.state.collectAsState()

    val sheetFilterState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldFilterState =
        rememberBottomSheetScaffoldState(bottomSheetState = sheetFilterState)
    val scope = rememberCoroutineScope()
    DisposableEffect(key1 = scope, effect =
    { onDispose { scope.cancel() } })

    Box(modifier = Modifier.fillMaxSize().background(brush = gradientCoursesBackground)) {
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
            backgroundColor = Color.Transparent,
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
                TopBarCourses(
                    text = state.value.search,
                    onTextChange = { viewModel.onEvent(CoursesUiEvents.SetSearchField(it)) }) {

                    scope.launch {
                        if (sheetFilterState.isCollapsed)
                            sheetFilterState.expand()
                        else
                            sheetFilterState.collapse()
                    }

                }
                Spacer(modifier = Modifier.height(19.dp))

                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    items(state.value.courses.size) {
                        state.value.courses[it].apply {
                            VideoCourseItem(
                                name = this.name,
                                type = this.type,
                                img = R.drawable.course_cover,
                                count = this.listVideo.size
                            ) {
                                navController.navigate(CoursesTapScreens.DetailCourseScreenRoute.createRoute(it))
                            }
                            if (it == state.value.courses.size - 1)
                                Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }

            }
        }
    }
}