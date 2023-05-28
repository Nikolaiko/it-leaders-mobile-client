package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.penguins.educationmultiplatform.android.mapScreen.ui.fontCardColor
import com.penguins.educationmultiplatform.android.ui.calibri
import com.penguins.educationmultiplatform.android.ui.primaryGray

@Composable
fun VideoLessonItem(name: String, img: Int, count: Int, onClick:()->Unit){
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
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column (modifier = Modifier.fillMaxWidth()){
            Text(
                text = "${count+1}. $name",
                color = fontCardColor,
                fontWeight = FontWeight.W700,
                fontFamily = calibri,
                fontSize = 18.sp
            )
        }
    }
}