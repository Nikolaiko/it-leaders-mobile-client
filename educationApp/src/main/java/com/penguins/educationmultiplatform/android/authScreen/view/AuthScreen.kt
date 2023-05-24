package com.penguins.educationmultiplatform.android.authScreen.view

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.penguins.educationmultiplatform.android.MyApplicationTheme
import com.penguins.educationmultiplatform.android.authScreen.components.EducationButton
import com.penguins.educationmultiplatform.android.authScreen.components.FormField
import com.penguins.educationmultiplatform.android.authScreen.data.AuthScreenEvents
import com.penguins.educationmultiplatform.android.authScreen.viewModel.AuthViewModel
import com.penguins.educationmultiplatform.android.data.localUserDataRepository.SharedPreferencesRepository
import com.penguins.educationmultiplatform.android.data.model.consts.errorEffect
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.data.navigation.DestinationController
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import com.penguins.educationmultiplatform.android.ui.gradientBackground
import com.ramcosta.composedestinations.annotation.Destination
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope
import org.koin.androidx.compose.koinViewModel

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

    val context = LocalContext.current.applicationContext
    LaunchedEffect(key1 = errorEffect) {
        viewModel.errorState.collect {
            if (it !is AppError.NoError) {
                Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBackground)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FormField(
            text = state.value.login,
            placeHolder = "youremail@mail.ru",
            valueCallback = {
                viewModel.onEvent(AuthScreenEvents.SetLogin(it))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        FormField(
            text = state.value.password,
            placeHolder = "password", valueCallback = {
                viewModel.onEvent(AuthScreenEvents.SetPassword(it))
            }
        )
        Spacer(modifier = Modifier.height(50.dp))
        EducationButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "Войти",
            enabled = viewModel.validateFields()
        ) {
            viewModel.onEvent(AuthScreenEvents.AuthWithEmail)
        }
        Spacer(modifier = Modifier.height(20.dp))
        EducationButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "Войти через VK"
        ) {
            launcher.launch(listOf(VKScope.EMAIL))
        }
        Spacer(modifier = Modifier.height(20.dp))
        EducationButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "Зарегистрироваться"
        ) {
            viewModel.onEvent(AuthScreenEvents.RegisterButton)
        }
        Spacer(modifier = Modifier.height(20.dp))
        EducationButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "Войти позже"
        ) {
            viewModel.onEvent(AuthScreenEvents.AuthLater)
        }
    }
}
