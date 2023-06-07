package com.penguins.educationmultiplatform.android.profileScreen.components.tabs

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components.VideoCourseItem
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.CoursesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyCourses(viewModel: CoursesViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()
    val courses = state.value.courses

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(courses.size) {
            courses[it].apply {
                VideoCourseItem(
                    name = this.name,
                    type = this.type,
                    img = R.drawable.course_cover,
                    count = this.listVideo.size
                ) {
//                   navController.navigate(CoursesTapScreens.DetailCourseScreenRoute.createRoute(it))
                }
                if (it == courses.size - 1)
                    Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}
