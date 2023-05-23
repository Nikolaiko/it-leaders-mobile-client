package com.penguins.educationmultiplatform.android.authScreen.view

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.authScreen.components.EducationButton
import com.penguins.educationmultiplatform.android.authScreen.components.FormField
import com.penguins.educationmultiplatform.android.authScreen.data.AuthScreenEvents
import com.penguins.educationmultiplatform.android.authScreen.viewModel.AuthViewModel
import com.penguins.educationmultiplatform.android.navigation.graps.MainNavGraph
import com.penguins.educationmultiplatform.android.ui.gradientBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import org.koin.androidx.compose.koinViewModel

@MainNavGraph
@Destination
@Composable
fun AuthScreen(viewModel:AuthViewModel = koinViewModel()) {

    val state = viewModel.state.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = VK.getVKAuthActivityResultContract(),
        onResult = { result: VKAuthenticationResult ->
            when (result) {
                is VKAuthenticationResult.Success -> {
                    Log.e("TAG", "${result.token.accessToken}: ")
                }
                is VKAuthenticationResult.Failed -> {
                    Log.e("TAG", ":${result.exception.authError} ")
                }
            }
        })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBackground)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        FormField(text = state.value.login, placeHolder = "youremail@mail.ru", valueCallback = {viewModel.onEvent(AuthScreenEvents.SetLogin(it))})
        Spacer(modifier = Modifier.height(20.dp))
        FormField(text = state.value.password,placeHolder = "password", valueCallback = {viewModel.onEvent(AuthScreenEvents.SetPassword(it))})
        Spacer(modifier = Modifier.height(50.dp))
        EducationButton(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp), text = "Войти"){

        }
        Spacer(modifier = Modifier.height(20.dp))
        EducationButton(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp), text = "Зарегистрироваться"){

        }
        Spacer(modifier = Modifier.height(20.dp))
        EducationButton(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp), text = "Войти через VK"){
            launcher.launch(listOf(VKScope.EMAIL))
        }
        Spacer(modifier = Modifier.height(20.dp))
        EducationButton(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp), text = "Войти позже"){

        }

    }

}