package com.penguins.educationmultiplatform.android.commonViews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.data.model.VoidCallback
import com.penguins.educationmultiplatform.android.ui.neutral0Size20Weight700Style
import com.penguins.educationmultiplatform.android.ui.neutral400Size20Weight700Style
import com.penguins.educationmultiplatform.android.ui.secondary200
import com.penguins.educationmultiplatform.android.ui.secondary400

@Composable
fun VioletteButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    onClick: VoidCallback
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextButton(
            onClick = onClick,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = secondary400,
                disabledBackgroundColor = secondary200
            )
        ) {
            Text(
                text = text,
                style = when(enabled) {
                    true -> neutral0Size20Weight700Style
                    false -> neutral400Size20Weight700Style
                }
            )
        }
    }
}