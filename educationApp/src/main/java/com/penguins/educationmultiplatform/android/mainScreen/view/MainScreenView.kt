package com.penguins.educationmultiplatform.android.mainScreen.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.penguins.educationmultiplatform.android.navigation.graps.MainNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@MainNavGraph
@Destination
@Composable
fun MainScreenView() {
    Text(text = "Main Screen")
}