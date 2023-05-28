package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.ui.fontCardColor
import com.penguins.educationmultiplatform.android.ui.*

@Composable
fun VideoCourseItem(name: String, type: SchoolType, img: Int, count: Int, onClick:()->Unit) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            .clickable(interactionSource = remember{ MutableInteractionSource() }, indication = null, onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Card(
            modifier = Modifier.size(height = 96.dp, width = 140.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = painterResource(id = img),
                modifier = Modifier
                    .background(
                        color = primaryGray,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(56.dp)
                        .background(backGroundAlphaColor),
                    contentAlignment = Alignment.Center
                ) {
                    Row {

                        Text(
                            text = "$count",
                            color = primaryWhite,
                            fontWeight = FontWeight.W700,
                            fontFamily = calibri,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            painter =
                            painterResource(id = com.penguins.educationmultiplatform.android.R.drawable.count_video),
                            modifier = Modifier,
                            contentDescription = null,
                            tint = primaryWhite
                        )
                    }

                }
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column (modifier = Modifier.fillMaxWidth()){
            Text(
                text = name,
                color = fontCardColor,
                fontWeight = FontWeight.W700,
                fontFamily = calibri,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(
                        type
                            .toBackGroundColor()
                            .copy(alpha = 0.4f), shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = type.toTextType(),
                    color = type.toBackGroundColor(),
                    fontFamily = calibri,
                    fontWeight = FontWeight.W700,
                    fontSize = 12.sp
                )
            }

        }
    }
}

fun SchoolType.toBackGroundColor(): Color {

    return when (this) {
        SchoolType.MUSICAL -> musicBackgroundColor
        SchoolType.ARTISTIC -> artBackgroundColor
        SchoolType.DANCING -> danceBackgroundColor
        SchoolType.THEATRICAL -> theatreBackgroundColor
    }
}

fun SchoolType.toTextType(): String {
    return when (this) {
        SchoolType.MUSICAL -> "Музыка"
        SchoolType.ARTISTIC -> "Изобразительное искусство"
        SchoolType.DANCING -> "Танцы"
        SchoolType.THEATRICAL -> "Театр"
    }
}