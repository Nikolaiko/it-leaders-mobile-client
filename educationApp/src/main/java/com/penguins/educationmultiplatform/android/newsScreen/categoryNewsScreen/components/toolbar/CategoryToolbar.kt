package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.components.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.newsScreen.common.components.buttons.BackButton
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton
import com.penguins.educationmultiplatform.android.ui.heading1BoldTextStyle

@Composable
fun CategoryToolbar(
    title: String,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        BackButton(
            onClick = onBackClick
        )
        TitleToolbar(
            modifier = Modifier.weight(1f),
            title = title
        )
        SearchButton(
            onClick = onSearchClick
        )
    }
}

@Composable
fun TitleToolbar(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            text = title,
            style = heading1BoldTextStyle
        )
    }
}

@Composable
fun SearchButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        ImageButton(
            onClick = onClick,
            imageId = R.drawable.ic_search
        )
    }
}
