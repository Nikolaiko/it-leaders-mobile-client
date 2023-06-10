package com.penguins.educationmultiplatform.android.profileScreen.components.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R

@Composable
fun DropDownMenu(
    isExpanded: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onClickItem: () -> Unit
) {
    Box(modifier = modifier) {
        DropDownItem(
            onClick = onClick
        )
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = onDismissRequest,
//            modifier = Modifier.background(PrimaryLight)
        ) {
            DropDownMenuItems(onClickItem)
        }
    }
}

@Composable
fun DropDownItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            modifier = Modifier
                .size(30.dp)
                .clickable { onClick() }
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = "Profile settings"
        )
    }
}

@Composable
private fun DropDownMenuItems(onClick: () -> Unit) = Column {
    DropdownMenuItem(
        text = "Изменить фотографию",
        iconId = R.drawable.ic_search,//fixed
        onClick = onClick
    )
    Divider(color = White)//fixed
    DropdownMenuItem(
        text = "Выйти из аккаунта",
        iconId = R.drawable.ic_search,//fixed
        onClick
    )
}

@Composable
private fun DropdownMenuItem(
    text: String,
    iconId: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 16.dp
            )
        )
    }
}