package com.penguins.educationmultiplatform.android.mapScreen.components

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.mapScreen.ui.buttonBottomSheetColor
import com.penguins.educationmultiplatform.android.mapScreen.ui.clickedMapButtonColor
import com.penguins.educationmultiplatform.android.mapScreen.ui.fontCardColor

@Composable
fun GpsAlertDialog(
    onDismiss:()->Unit
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "GPS не включен") },
        backgroundColor = clickedMapButtonColor,
        text = {
            Column {
                Text(text = "Для использования определения геолокации необходимо включить GPS.", color = fontCardColor)
                Text(text = "Вы можете включить его в настройках устройства.", color = fontCardColor)
            }
        },
        confirmButton = {
            Button(
                onClick = { context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = buttonBottomSheetColor)
            ) {
                Text(text = "Открыть настройки", color = fontCardColor)
            }
        },

        dismissButton = {
            Button(
                onClick = onDismiss,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = buttonBottomSheetColor)
            ) {
                Text(text = "Отмена", color = fontCardColor)
            }
        }
    )
}