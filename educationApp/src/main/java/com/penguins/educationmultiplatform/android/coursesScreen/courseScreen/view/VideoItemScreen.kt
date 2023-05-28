package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.view

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components.toBackGroundColor
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.viewModel.VideoItemViewModel
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.ui.fontCardColor
import com.penguins.educationmultiplatform.android.ui.*
import org.koin.androidx.compose.koinViewModel

@Composable
fun VideoItemScreen(
    viewModel: VideoItemViewModel = koinViewModel(),
    id: Int?,
    navController: NavHostController
) {

    val state = viewModel.lessonState.collectAsState()
    val lifecycle = remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle.value = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    state.value.selectedLesson?.let { selectedLesson ->
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Color.Unspecified,
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(bottom = 60.dp)
                        .horizontalScroll(rememberScrollState())

                ) {
                    state.value.lessons.forEachIndexed { index, videoLesson ->
                        if (index == 0)
                            Spacer(modifier = Modifier.width(16.dp))
                        Card(
                            modifier = Modifier.size(height = 72.dp, width = 72.dp)
                                .clickable { viewModel.selectVideoLesson(videoLesson.id) },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.course_cover),
                                    modifier = Modifier
                                        .background(
                                            color = primaryGray,
                                            shape = RoundedCornerShape(8.dp)
                                        ),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = "${index + 1}",
                                    fontFamily = calibri,
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.W700,
                                    color = primaryWhite.copy(alpha = if (index == selectedLesson.id) 1.0f else 0.5f)
                                )
                                if (index == selectedLesson.id) {
                                    Box(
                                        modifier = Modifier
                                            .size(64.dp)
                                            .border(
                                                width = 2.dp,
                                                color = primaryWhite,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

        ) {
            it

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(state.value.type.toBackGroundVideoItemColor())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 15.dp)
                        .statusBarsPadding()
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
                            },
                            imageVector = ImageVector.vectorResource(id = R.drawable.rotate_icon),
                            contentDescription = null,
                            tint = primaryBlack
                        )
                    }
                    if (state.value.loading)
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(color = primaryGreen)
                        }
                    else {
                        Spacer(modifier = Modifier.height(10.dp))
                        AndroidView(
                            factory = { context ->
                                PlayerView(context).also {
                                    it.player = viewModel.player
                                }
                            },
                            update = {
                                when (lifecycle.value) {
                                    Lifecycle.Event.ON_PAUSE -> {
                                        it.onPause()
                                        it.player?.stop()
                                    }
                                    Lifecycle.Event.ON_RESUME -> {
                                        it.onResume()
                                    }
                                    else -> Unit
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16 / 9f)
                        )
                        Spacer(modifier = Modifier.height(13.dp))
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                text = selectedLesson.name,
                                color = fontCardColor,
                                fontWeight = FontWeight.W700,
                                fontFamily = calibri,
                                fontSize = 20.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                text = selectedLesson.description,
                                color = textGrayColor,
                                fontWeight = FontWeight.W400,
                                fontFamily = calibri,
                                fontSize = 16.sp
                            )
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
                                    Card(
                                        modifier = Modifier.size(72.dp),
                                        shape = CircleShape
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.photo_teacher),
                                            modifier = Modifier
                                                .background(
                                                    color = primaryGray,
                                                    shape = RoundedCornerShape(8.dp)
                                                ),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 10.dp)
                                    ) {
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
                                            color = textGrayColor,
                                            fontWeight = FontWeight.W400,
                                            fontFamily = calibri,
                                            fontSize = 12.sp
                                        )
                                        Spacer(modifier = Modifier.height(3.dp))
                                        Text(
                                            text = state.value.nameSchool,
                                            color = state.value.type.toBackGroundColor(),
                                            fontWeight = FontWeight.W700,
                                            fontFamily = calibri,
                                            fontSize = 16.sp
                                        )
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.height(100.dp))
                        }

                    }

                }
            }
        }
    }
}

fun SchoolType.toBackGroundVideoItemColor(): Brush {
    return when (this) {
        SchoolType.MUSICAL -> musicVerticalGradientBackground
        SchoolType.ARTISTIC -> artVerticalGradientBackground
        SchoolType.DANCING -> danceVerticalGradientBackground
        SchoolType.THEATRICAL -> theatreVerticalGradientBackground
    }
}