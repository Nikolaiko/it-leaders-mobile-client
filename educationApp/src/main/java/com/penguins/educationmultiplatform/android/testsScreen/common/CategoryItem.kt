package com.penguins.educationmultiplatform.android.testsScreen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.data.model.VoidCallback
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton
import com.penguins.educationmultiplatform.android.ui.neutral0
import com.penguins.educationmultiplatform.android.ui.neutral0Size17Weight700Style
import com.penguins.educationmultiplatform.android.ui.neutral400
import com.penguins.educationmultiplatform.android.ui.primary500
import com.penguins.educationmultiplatform.android.ui.primaryGray

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category,
    selected: Boolean = false,
    callback: VoidCallback = {}
) {
    Image(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = callback
            ),
        painter = painterResource(
            id = getImageIdFromParameters(category, selected)
        ),
        contentDescription = stringResource(id = R.string.category_button_image)
    )
}

fun getImageIdFromParameters(category: Category, selected: Boolean): Int {
    return when(selected) {
        true -> getSelectedIcon(category)
        false -> getUnSelectedIcon(category)
    }
}

fun getSelectedIcon(category: Category): Int {
    return when(category) {
        Category.MUSIC -> R.drawable.music_selected_
        Category.ART -> R.drawable.art_selected
        Category.DANCE -> R.drawable.dance_selected
        Category.THEATRE -> R.drawable.theatere_selected
    }
}

fun getUnSelectedIcon(category: Category): Int {
    return when(category) {
        Category.MUSIC -> R.drawable.music_not_selected
        Category.ART -> R.drawable.art_unselected
        Category.DANCE -> R.drawable.dance_unselected
        Category.THEATRE -> R.drawable.theatere_unselected
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    MyApplicationTheme {
        Surface {
            CategoryItem(
                modifier = Modifier
                    .width(162.dp)
                    .height(162.dp),
                category = Category.THEATRE,
                selected = true
            )
        }
    }
}