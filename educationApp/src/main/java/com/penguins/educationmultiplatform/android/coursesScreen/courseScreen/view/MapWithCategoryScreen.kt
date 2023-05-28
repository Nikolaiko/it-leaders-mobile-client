package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.penguins.educationmultiplatform.android.mapScreen.components.YandexMapScreenForIntegration
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.ui.fontCardColor
import com.penguins.educationmultiplatform.android.ui.*

@Composable
fun MapWithCategoryScreen(id:Int?, navController:NavHostController){

    id?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(it.toBackGroundMapDetailColor())
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
                    horizontalArrangement = Arrangement.Start,
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
                }
                Spacer(modifier = Modifier.height(21.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = "Где можно это изучать?",
                    fontWeight = FontWeight.W700,
                    fontFamily = calibri,
                    fontSize = 32.sp,
                    color = fontCardColor
                )
                Spacer(modifier = Modifier.height(24.dp))
                YandexMapScreenForIntegration(type = it.fromIdType(), bottomSheetHeight = 0.8f)

            }

        }
    }

}

fun Int.toBackGroundMapDetailColor(): Brush {
    return when (this) {
        SchoolType.MUSICAL.idType -> musicHorizontalGradientBackground
        SchoolType.ARTISTIC.idType -> artHorizontalGradientBackground
        SchoolType.DANCING.idType -> danceHorizontalGradientBackground
        SchoolType.THEATRICAL.idType -> theatreHorizontalGradientBackground
        else -> {
            musicHorizontalGradientBackground}
    }
}
fun Int.fromIdType(): SchoolType {
    return when (this) {
        SchoolType.MUSICAL.idType -> SchoolType.MUSICAL
        SchoolType.ARTISTIC.idType -> SchoolType.ARTISTIC
        SchoolType.DANCING.idType -> SchoolType.DANCING
        SchoolType.THEATRICAL.idType -> SchoolType.THEATRICAL
        else -> {
            SchoolType.THEATRICAL}
    }
}