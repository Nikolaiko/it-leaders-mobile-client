package com.penguins.educationmultiplatform.android.ui.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.ui.primaryGray

@Composable
fun UserImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null
) {
    Card(
        modifier = modifier,
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(id = R.drawable.photo_teacher),
            modifier = Modifier
                .background(
                    color = primaryGray,
                    shape = RoundedCornerShape(8.dp)
                ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
