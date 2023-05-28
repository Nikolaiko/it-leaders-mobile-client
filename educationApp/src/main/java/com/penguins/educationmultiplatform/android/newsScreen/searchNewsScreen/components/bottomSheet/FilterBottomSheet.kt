package com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.components.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.penguins.educationmultiplatform.android.mapScreen.components.StreetCheckBox
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.data.SearchNewsEvents
import com.penguins.educationmultiplatform.android.newsScreen.searchNewsScreen.viewModel.SearchNewsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilterBottomSheet(
    viewModel: SearchNewsViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    Column (
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        TitleBottomSheet()
        FiltersCheckboxBottomSheet(
            categories = state.value.mapCategories,
            onAllCategoriesChecked = { isChecked ->
                viewModel.onEvent(SearchNewsEvents.AllCategoriesChecked(isChecked))
            },
            onCategoryChecked = { category ->
                viewModel.onEvent(SearchNewsEvents.CategoryChecked(category))
            }
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
    categories: Map<Category, Boolean>,
    onAllCategoriesChecked: (Boolean) -> Unit,
    onCategoryChecked: (Category) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 64.dp)
    ) {
        val isAllSelected = categories.values.all { it }
        StreetCheckBox(street = "Все", selected = isAllSelected) {
            onAllCategoriesChecked(!isAllSelected)
        }

        categories.forEach {
            StreetCheckBox(street = it.key.title, selected = it.value) {
                onCategoryChecked(it.key)
            }
        }
    }
}
