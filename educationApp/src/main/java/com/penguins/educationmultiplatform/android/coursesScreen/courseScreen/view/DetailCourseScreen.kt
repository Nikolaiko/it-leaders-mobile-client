package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components.VideoLessonItem
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components.toBackGroundColor
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components.toTextType
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.DetailCourseViewModel
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.ui.fontCardColor
import com.penguins.educationmultiplatform.android.navigation.routeObject.CoursesTapScreens
import com.penguins.educationmultiplatform.android.ui.*
import com.penguins.educationmultiplatform.android.ui.images.UserImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailCourseScreen(
    id: Int?,
    viewModel: DetailCourseViewModel = koinViewModel(),
    navController: NavHostController
) {

    val state = viewModel.state.collectAsState()
    val courseState = id?.let {state.value[it]}
    id?.let {
        viewModel.setSelectedCourse(it)
    }
    courseState?.let { videoCourse ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(videoCourse.type.toBackGroundDetailColor())
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(top = 15.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            navController.popBackStack()
                        },
                        imageVector = ImageVector.vectorResource(id = R.drawable.back_icon),
                        contentDescription = null,
                        tint = primaryBlack
                    )
                    Icon(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            navController.navigate(CoursesTapScreens.MapScreenRoute.createRoute(videoCourse.type.idType))
                        },
                        imageVector = ImageVector.vectorResource(id = R.drawable.geo_icon),
                        contentDescription = null,
                        tint = primaryBlack
                    )
                }
                Spacer(modifier = Modifier.height(21.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = videoCourse.name,
                    fontWeight = FontWeight.W700,
                    fontFamily = calibri,
                    fontSize = 32.sp,
                    color = fontCardColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 16.dp)
                        .background(
                            color = primaryWhite, shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        text = videoCourse.type.toTextType(),
                        color = fontCardColor,
                        fontFamily = calibri,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backGroundCard)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        UserImage(modifier = Modifier.size(72.dp))

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)) {
                            Text(
                                text = "Величевский Валерий Анатольевич",
                                color = fontCardColor,
                                fontWeight = FontWeight.W400,
                                fontFamily = calibri,
                                fontSize = 18.sp
                            )

                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = "Заведующий кафедрой хорового искусства",
                                color = fontCardColor,
                                fontWeight = FontWeight.W400,
                                fontFamily = calibri,
                                fontSize = 12.sp
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            Text(
                                text = videoCourse.name,
                                color = videoCourse.type.toBackGroundColor(),
                                fontWeight = FontWeight.W700,
                                fontFamily = calibri,
                                fontSize = 16.sp
                            )
                        }
                    }

                }

                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                    items(videoCourse.listVideo.size) {
                        videoCourse.listVideo[it].apply {
                            if(it == 0){
                                Spacer(modifier = Modifier.height(40.dp))
                            }
                            VideoLessonItem(
                                name = this.name,
                                img = this.pictureId,
                                count = it
                            ) {
                                viewModel.setSelectedLesson(it)
                                navController.navigate(CoursesTapScreens.DetailCourseListScreenRoute.createRoute(it))
                            }
                            if (it == videoCourse.listVideo.size - 1)
                                Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }

            }

        }
    }

}

fun SchoolType.toBackGroundDetailColor(): Brush {
    return when (this) {
        SchoolType.MUSICAL -> musicHorizontalGradientBackground
        SchoolType.ARTISTIC -> artHorizontalGradientBackground
        SchoolType.DANCING -> danceHorizontalGradientBackground
        SchoolType.THEATRICAL -> theatreHorizontalGradientBackground
    }
}


