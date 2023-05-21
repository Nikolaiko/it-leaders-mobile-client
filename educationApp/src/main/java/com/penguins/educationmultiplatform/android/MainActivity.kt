package com.penguins.educationmultiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.penguins.educationmultiplatform.android.authScreen.view.RegisterScreen
import com.penguins.educationmultiplatform.android.mapScreen.view.YandexMapScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyApplicationTheme {
            YandexMapScreen()
            }
        }
    }
}

