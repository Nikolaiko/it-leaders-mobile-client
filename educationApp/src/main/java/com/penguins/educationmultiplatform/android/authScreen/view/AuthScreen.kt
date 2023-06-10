package com.penguins.educationmultiplatform.android.authScreen.view

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import com.penguins.educationmultiplatform.android.commonViews.EducationButton
import com.penguins.educationmultiplatform.android.authScreen.components.FormField
import com.penguins.educationmultiplatform.android.authScreen.data.AuthScreenEvents
import com.penguins.educationmultiplatform.android.authScreen.viewModel.AuthViewModel
import com.penguins.educationmultiplatform.android.data.model.consts.errorEffect
import com.penguins.educationmultiplatform.android.data.model.consts.vkFields
import com.penguins.educationmultiplatform.android.data.model.dto.profile.VKProfile
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.data.remote.api.VKUsersCommand
import com.penguins.educationmultiplatform.android.ui.gradientBackground
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(viewModel:AuthViewModel = koinViewModel()) {

    val state = viewModel.state.collectAsState()

    val scope = rememberCoroutineScope()
    val vkProfileLauncher = rememberLauncherForActivityResult(
        contract = VK.getVKAuthActivityResultContract(),
        onResult = { result: VKAuthenticationResult ->
            when (result) {
                is VKAuthenticationResult.Success -> {
                    scope.launch(context = Dispatchers.IO) {
                        val response: VKProfile? = VK.executeSync(VKUsersCommand())
                        when(response) {
                            null -> viewModel.onEvent(AuthScreenEvents.LoginVKFailed)
                            else -> viewModel.onEvent(AuthScreenEvents.JoinWithVK(
                                result.token.userId.value.toString(),
                                result.token.email ?: "",
                                response
                            ))
                        }
                    }
                }
                is VKAuthenticationResult.Failed -> {
                    viewModel.onEvent(AuthScreenEvents.LoginVKFailed)
                }
            }
        }
    )

    val vkAuthLauncher = rememberLauncherForActivityResult(
        contract = VK.getVKAuthActivityResultContract(),
        onResult = { result: VKAuthenticationResult ->
            when (result) {
                is VKAuthenticationResult.Success -> {
                    //viewModel.onEvent(AuthScreenEvents.JoinWithVK(result.token.accessToken))
                    vkProfileLauncher.launch(vkFields)
                }
                is VKAuthenticationResult.Failed -> {
                    viewModel.onEvent(AuthScreenEvents.LoginVKFailed)
                }
            }
        }
    )

    val context = LocalContext.current
    LaunchedEffect(key1 = errorEffect) {
        viewModel.errorState.collect {
            if (it !is AppError.NoError) {
                Toast.makeText(context, "Извините! Что-то пошло не так.", Toast.LENGTH_LONG).show()
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
            keyBoardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            valueCallback = {
                viewModel.onEvent(AuthScreenEvents.SetLogin(it))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        FormField(
            text = state.value.password,
            placeHolder = "password",
            keyBoardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            valueCallback = {
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
            vkAuthLauncher.launch(vkFields)
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
