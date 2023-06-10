package com.penguins.educationmultiplatform.android.profileScreen.components.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.profileScreen.data.model.ProfileEvent
import com.penguins.educationmultiplatform.android.ui.neutral400

@Composable
fun DropDownMenu(
    isExpanded: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onClickItem: (ProfileEvent) -> Unit
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
private fun DropDownMenuItems(onClick: (ProfileEvent) -> Unit) = Column {
    DropdownMenuItem(
        text = "Изменить фотографию",
        iconId = R.drawable.png_camera,
        onClick = { onClick(ProfileEvent.ChangeImage) }
    )
    Divider(color = neutral400)
    DropdownMenuItem(
        text = "Выйти из аккаунта",
        iconId = R.drawable.ic_logout,
        onClick = { onClick(ProfileEvent.LogOut) }
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
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(start = 16.dp),
            painter = painterResource(id = iconId),
            contentDescription = text
        )
        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 16.dp
            )
        )
    }
}