package com.penguins.educationmultiplatform.android.profileScreen.components.chips

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.profileScreen.components.mapper.toChipsText
import com.penguins.educationmultiplatform.android.ui.linksBoldTextStyle

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileChips(
    categories: List<Category>,
    onClick: (Category) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 24.dp)
    ) {
        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(2),
            userScrollEnabled = false,
            modifier = Modifier.heightIn(max = 80.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalItemSpacing = 0.dp
        ) {
            items(categories) {
                Chip(
                    colors = ChipDefaults.chipColors(
                        backgroundColor = it.color
                    ),
                    onClick = { onClick(it) },
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 10.dp)
                ) {
                    Text(
                        text = it.toChipsText(),
                        maxLines = 1,
                        style = linksBoldTextStyle,
                        color = Color.White
                    )
                }
            }
        }
    }
}
