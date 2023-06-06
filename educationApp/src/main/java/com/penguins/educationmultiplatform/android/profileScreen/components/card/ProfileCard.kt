package com.penguins.educationmultiplatform.android.profileScreen.components.card

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.profileScreen.components.mapper.toChipsText
import com.penguins.educationmultiplatform.android.ui.images.UserImage

@Composable
fun ProfileCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min),
        ) {
            UserInfo(modifier = Modifier.weight(1f))
            UserImage()
        }
        ProfileChips()
    }
}

@Composable
fun UserInfo(modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.wrapContentSize()
            ) {
                Text(text = "Владимир Кузнецов")
                Text(text = "22 года")
            }
        }
        Box(
            modifier = Modifier.fillMaxHeight(),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(text = "165 баллов")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileChips(
    categories: List<Category> = Category.values().toList()
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
                    onClick = {},
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(end = 10.dp)
                ) {
                    Text(
                        text = it.toChipsText(),
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640, backgroundColor = 0xffffff)
@Composable
fun ProfilePreview() {
    ProfileChips()
}