package com.penguins.educationmultiplatform.android.categoryScreen.components

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
import com.penguins.educationmultiplatform.android.ui.buttons.ImageButton

@Composable
fun CategoryToolbar() {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        BackButton(
            onClick = {}
        )
        TitleToolbar(
            modifier = Modifier.weight(1f),
            title = "Музыка"
        )
        SearchButton(
            onClick = {}
        )
    }
}

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        ImageButton(
            onClick = onClick,
            imageId = R.drawable.ic_back
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
            text = title
        )
    }
}

@Composable
fun SearchButton(
    onClick: () -> Unit
) {
    ImageButton(
        onClick = onClick,
        imageId = R.drawable.ic_search
    )
}