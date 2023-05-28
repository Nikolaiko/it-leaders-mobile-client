package com.penguins.educationmultiplatform.android.newsScreen.common.components.titleRow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.ui.buttons.TextButton
import com.penguins.educationmultiplatform.android.ui.headingTextStyle
import com.penguins.educationmultiplatform.android.ui.linksBoldTextStyle
import com.penguins.educationmultiplatform.android.ui.neutral900

@Composable
fun NewsTitleRow(
    title: String,
    clickCategory: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        CategoryCardTitle(title)
        DividerAndTextButton {
            clickCategory(title)
        }
    }
}

@Composable
fun CategoryCardTitle(
    title: String
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val width = screenWidth * 0.38

    Box(
        modifier = Modifier
            .widthIn(max = width.dp)
            .fillMaxHeight(),
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            text = title,
            modifier = Modifier.wrapContentWidth(),
            style = headingTextStyle,
            color = neutral900
        )
    }
}

@Composable
fun DividerAndTextButton(clickCategory: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TitleDivider(modifier = Modifier.weight(1f))
        TitleTextButton(clickCategory)
    }
}

@Composable
fun TitleDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 9.dp),
        contentAlignment = Alignment.Center
    ) {
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.wrapContentWidth()
        )
    }
}

@Composable
fun TitleTextButton(
    onClick: () -> Unit
) = Box(
    modifier = Modifier.wrapContentWidth()
) {
    TextButton(
        onClick = onClick,
        text = SEE_ALL_TEXT_BUTTON,
        style = linksBoldTextStyle
    )
}

const val SEE_ALL_TEXT_BUTTON = "Смотреть всё"
