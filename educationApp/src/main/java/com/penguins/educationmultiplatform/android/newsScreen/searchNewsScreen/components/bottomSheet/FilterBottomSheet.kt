package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.components.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.penguins.educationmultiplatform.android.mapScreen.components.StreetCheckBox
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category

@Composable
fun FilterBottomSheet() {
    Column (
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        TitleBottomSheet()
        FiltersCheckboxBottomSheet(
            onAllCategoriesChecked = {},
            onCategoryChecked = {}
        )
    }
}

@Composable
fun TitleBottomSheet() {
    Text(
        text = "Направление",
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )
}

@Composable
fun FiltersCheckboxBottomSheet(
    onAllCategoriesChecked: () -> Unit,
    onCategoryChecked: (Category) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 64.dp)
    ) {
        StreetCheckBox(street = "Все", selected = false) {
            onAllCategoriesChecked()
        }

        Category.values().forEach {
            StreetCheckBox(street = it.title, selected = false) {
                onCategoryChecked(it)
            }
        }
    }
}
