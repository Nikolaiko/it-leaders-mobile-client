package com.penguins.educationmultiplatform.android.mapScreen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.data.YandexMapEvents
import com.penguins.educationmultiplatform.android.mapScreen.data.YandexMapUiState
import com.penguins.educationmultiplatform.android.mapScreen.ui.*

@Composable
fun FloatButtons(uiState: State<YandexMapUiState>,onEvent:(YandexMapEvents)->Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())

    ) {
        Spacer(modifier = Modifier.width(15.dp))
        SchoolCard(
            schoolType = null,
            modifier = Modifier,
            selected = uiState.value.filters.artistFilter && uiState.value.filters.dancingFilter && uiState.value.filters.musicalFilter && uiState.value.filters.theatricalFilter,
            onClick = onEvent
        )
        Spacer(modifier = Modifier.width(8.dp))
        SchoolCard(
            schoolType = SchoolType.MUSICAL,
            modifier = Modifier,
            selected = uiState.value.filters.musicalFilter,
            onClick = onEvent
        )
        Spacer(modifier = Modifier.width(8.dp))
        SchoolCard(
            schoolType = SchoolType.ARTISTIC,
            modifier = Modifier,
            selected = uiState.value.filters.artistFilter,
            onClick = onEvent
        )
        Spacer(modifier = Modifier.width(8.dp))
        SchoolCard(
            schoolType = SchoolType.THEATRICAL,
            modifier = Modifier,
            selected = uiState.value.filters.theatricalFilter,
            onClick = onEvent
        )
        Spacer(modifier = Modifier.width(8.dp))
        SchoolCard(
            schoolType = SchoolType.DANCING,
            modifier = Modifier,
            selected = uiState.value.filters.dancingFilter,
            onClick = onEvent
        )
        Spacer(modifier = Modifier.width(15.dp))
    }
}

@Composable
fun SchoolCard(
    schoolType: SchoolType?,
    modifier: Modifier,
    selected: Boolean,
    onClick: (YandexMapEvents) -> Unit
) {

    val animatedColorBackground = animateColorAsState(
        targetValue = if (selected) {
            when (schoolType) {
                SchoolType.DANCING -> {
                    dancingSchoolColor
                }
                SchoolType.MUSICAL -> {
                    musicalSchoolColor
                }
                SchoolType.ARTISTIC -> {
                    artistSchoolColor
                }
                SchoolType.THEATRICAL -> {
                    theatricalSchoolColor
                }
                null -> {
                    nonClickedMapButtonColor
                }
            }
        } else clickedMapButtonColor
    )
    Card(
        modifier = Modifier
            .height(60.dp)
            .wrapContentWidth()
            .clickable(interactionSource = remember {
                MutableInteractionSource()
            }, indication = null, onClick = {
                onClick.invoke(
                    when (schoolType) {
                        SchoolType.DANCING -> {
                            YandexMapEvents.SetDancingFilter
                        }
                        SchoolType.MUSICAL -> {
                            YandexMapEvents.SetMusicFilter
                        }
                        SchoolType.ARTISTIC -> {
                            YandexMapEvents.SetArtistFilter
                        }
                        SchoolType.THEATRICAL -> {
                            YandexMapEvents.SetTheaterFilter
                        }
                        null -> {
                            YandexMapEvents.SetAllFilter
                        }
                    }
                )
            }),
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        backgroundColor = animatedColorBackground.value
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                modifier = Modifier.padding(horizontal = 24.dp),
                text = schoolType?.nameSchool ?: "Все",
                fontSize = 16.sp,
                color = if (selected) clickedMapButtonColor else fontCardColor,
                fontWeight = if (selected) FontWeight.W700 else FontWeight.W400,
                style = if (selected) MaterialTheme.typography.h4.copy(
                    shadow = Shadow(
                        color = Color(0xFF000000), offset = Offset(x = 0f, y = 1f), blurRadius = 1f
                    )
                ) else LocalTextStyle.current,
                textAlign = TextAlign.Center
            )
        }
    }
}