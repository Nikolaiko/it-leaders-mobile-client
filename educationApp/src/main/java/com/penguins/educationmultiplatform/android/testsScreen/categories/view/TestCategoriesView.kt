package com.penguins.educationmultiplatform.android.testsScreen.categories.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.testsScreen.common.CategoryItem
import com.penguins.educationmultiplatform.android.testsScreen.categories.viewModel.TestCategoriesViewModel
import com.penguins.educationmultiplatform.android.ui.neutral0
import com.penguins.educationmultiplatform.android.ui.neutral900Size32Weight700Style
import com.penguins.educationmultiplatform.android.ui.primary500
import com.penguins.educationmultiplatform.android.ui.primary600
import com.penguins.educationmultiplatform.android.ui.secondary400
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestCategoriesView(
    viewModel: TestCategoriesViewModel = koinViewModel()
) {
    BoxWithConstraints(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        val itemSide = maxHeight.times(0.1995F)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(neutral0)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = stringResource(id = R.string.test_categories_title),
                style = neutral900Size32Weight700Style,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CategoryItem(
                    modifier = Modifier
                        .width(itemSide)
                        .height(itemSide),
                    name = stringResource(id = R.string.music_category_label),
                    background = primary500,
                    callback = { viewModel.navigateToMusic() }
                )
                CategoryItem(
                    modifier = Modifier
                        .width(itemSide)
                        .height(itemSide),
                    name = stringResource(id = R.string.art_category_label),
                    background = primary600,
                    enabled = false
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CategoryItem(
                    modifier = Modifier
                        .width(itemSide)
                        .height(itemSide),
                    name = stringResource(id = R.string.theatere_category_label),
                    background = secondary400,
                    enabled = false
                )
                CategoryItem(
                    modifier = Modifier
                        .width(itemSide)
                        .height(itemSide),
                    name = stringResource(id = R.string.dance_category_label),
                    background = primary600,
                    enabled = false
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
        }
    }
}
