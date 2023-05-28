package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.CoursesUiEvents
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolTypeFilter
import com.penguins.educationmultiplatform.android.mapScreen.ui.clickedMapButtonColor

@Composable
fun BottomSheetCoursesFilter(filters: SchoolTypeFilter, onEvent: (CoursesUiEvents) -> Unit) {

    Card(
        modifier = Modifier.wrapContentSize(),
        elevation = 2.dp,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    clickedMapButtonColor,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier.width(35.dp),
                    color = Color(0xFFD9D9D9),
                    thickness = 3.dp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Направление",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.W700,
                    color = Color(0xFF101010)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column (Modifier.fillMaxWidth()){
                    SchoolCheckBox(
                        schoolType = null,
                        selected = filters.musicalFilter && filters.artistFilter && filters.dancingFilter && filters.theatricalFilter
                    ) {
                        onEvent.invoke(CoursesUiEvents.SetAllFilter)
                    }
                    SchoolCheckBox(
                        schoolType = SchoolType.MUSICAL,
                        selected = filters.musicalFilter
                    ) {
                        onEvent.invoke(CoursesUiEvents.SetMusicFilter)
                    }
                    SchoolCheckBox(
                        schoolType = SchoolType.ARTISTIC,
                        selected = filters.artistFilter
                    ) {
                        onEvent.invoke(CoursesUiEvents.SetArtistFilter)
                    }
                    SchoolCheckBox(
                        schoolType = SchoolType.DANCING,
                        selected = filters.dancingFilter
                    ) {
                        onEvent.invoke(CoursesUiEvents.SetDancingFilter)
                    }
                    SchoolCheckBox(
                        schoolType = SchoolType.THEATRICAL,
                        selected = filters.theatricalFilter
                    ) {
                        onEvent.invoke(CoursesUiEvents.SetTheaterFilter)
                    }
                }
            }
        }
    }
}


@Composable
fun SchoolCheckBox(schoolType: SchoolType?, selected: Boolean, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                null
            ) { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = selected,
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF8E74A8),
                uncheckedColor = Color(0xFF8E74A8),
                checkmarkColor = Color(0xFFFFFFFF)
            )
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = schoolType?.toStringType() ?: "Все",
            color = Color(0xFF101010),
            fontSize = 16.sp,
            fontWeight = FontWeight.W400
        )
    }
}

fun SchoolType.toStringType(): String = when (this) {
    SchoolType.DANCING -> "Танцы"
    SchoolType.MUSICAL -> "Музыка"
    SchoolType.ARTISTIC -> "Изобразительное искусство"
    SchoolType.THEATRICAL -> "Театр"
}