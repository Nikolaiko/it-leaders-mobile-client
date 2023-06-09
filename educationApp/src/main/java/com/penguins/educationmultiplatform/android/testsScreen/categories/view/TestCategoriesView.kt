package com.penguins.educationmultiplatform.android.testsScreen.categories.view

import android.widget.Toast
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.data.localUserDataRepository.SharedPreferencesRepository
import com.penguins.educationmultiplatform.android.data.model.consts.errorEffect
import com.penguins.educationmultiplatform.android.data.model.consts.testCategoriesScreenEffect
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.data.navigation.DestinationController
import com.penguins.educationmultiplatform.android.domain.useCases.GetUserDataUseCase
import com.penguins.educationmultiplatform.android.newsScreen.common.data.Category
import com.penguins.educationmultiplatform.android.testsScreen.common.CategoryItem
import com.penguins.educationmultiplatform.android.testsScreen.categories.viewModel.TestCategoriesViewModel
import com.penguins.educationmultiplatform.android.ui.gradientTestCategoriesScreen
import com.penguins.educationmultiplatform.android.ui.neutral0
import com.penguins.educationmultiplatform.android.ui.neutral0Size20Weight700Style
import com.penguins.educationmultiplatform.android.ui.neutral400
import com.penguins.educationmultiplatform.android.ui.neutral400Size20Weight700Style
import com.penguins.educationmultiplatform.android.ui.neutral900Size32Weight700Style
import com.penguins.educationmultiplatform.android.ui.primary500
import com.penguins.educationmultiplatform.android.ui.primary600
import com.penguins.educationmultiplatform.android.ui.primaryGray
import com.penguins.educationmultiplatform.android.ui.secondary200
import com.penguins.educationmultiplatform.android.ui.secondary400
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
fun TestCategoriesView(
    viewModel: TestCategoriesViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = testCategoriesScreenEffect) {
        viewModel.initUserData()
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = errorEffect) {
        viewModel.errorState.collect {
            if (it !is AppError.NoError) {
                Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        val itemSide = maxHeight.times(0.1995F)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientTestCategoriesScreen)
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
                    category = Category.MUSIC,
                    selected = state.value.musicSelected,
                    callback = { viewModel.toggleMusic() }
                )
                CategoryItem(
                    modifier = Modifier
                        .width(itemSide)
                        .height(itemSide),
                    selected = state.value.artSelected,
                    category = Category.ART,
                    callback = { viewModel.toggleArt() }
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
                    selected = state.value.theatereSelected,
                    category = Category.THEATRE,
                    callback = { viewModel.toggleTheatere() }
                )
                CategoryItem(
                    modifier = Modifier
                        .width(itemSide)
                        .height(itemSide),
                    selected = state.value.danceSelected,
                    category = Category.DANCE,
                    callback = { viewModel.toggleDance() }
                )
            }
            Spacer(modifier = Modifier.weight(1.0f))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    onClick = { viewModel.proceedToTests() },
                    enabled = state.value.canContinue,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = secondary400,
                        disabledBackgroundColor = secondary200
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.continue_text),
                        style = when(state.value.canContinue) {
                            true -> neutral0Size20Weight700Style
                            false -> neutral400Size20Weight700Style
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1.0f))
        }
    }
}
