package com.penguins.educationmultiplatform.android.newsScreen.categoryNewsScreen.components.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.mapScreen.components.YandexMapScreenForIntegration
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category

@Composable
fun CategoryMap(category: Category?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
            .padding(top = 32.dp)
    ) {
        YandexMapScreenForIntegration(
            type = getSchoolType(category),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private fun getSchoolType(category: Category?) = when (category) {
    Category.MUSIC -> SchoolType.MUSICAL
    Category.THEATRE -> SchoolType.THEATRICAL
    Category.ART -> SchoolType.ARTISTIC
    else -> SchoolType.DANCING
}
