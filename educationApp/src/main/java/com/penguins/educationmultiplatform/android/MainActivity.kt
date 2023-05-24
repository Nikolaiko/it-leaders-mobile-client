package com.penguins.educationmultiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyApplicationTheme {
                Surface(
                    Modifier
                        .fillMaxSize()
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.main)
                }
            }
        }
    }
}
