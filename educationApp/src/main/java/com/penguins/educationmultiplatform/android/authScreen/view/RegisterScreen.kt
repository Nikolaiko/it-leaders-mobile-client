package com.penguins.educationmultiplatform.android.authScreen.view

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.penguins.educationmultiplatform.android.authScreen.components.EducationButton
import com.penguins.educationmultiplatform.android.authScreen.components.FormField
import com.penguins.educationmultiplatform.android.authScreen.data.RegisterScreenEvents
import com.penguins.educationmultiplatform.android.authScreen.viewModel.RegisterViewModel
import com.penguins.educationmultiplatform.android.data.model.consts.errorEffect
import com.penguins.educationmultiplatform.android.data.model.error.AppError
import com.penguins.educationmultiplatform.android.data.remote.api.VKUsersCommand
import com.penguins.educationmultiplatform.android.ui.educationGreenColor
import com.penguins.educationmultiplatform.android.ui.gradientBackground
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.util.*

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel()
){

    val scope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(
        contract = VK.getVKAuthActivityResultContract(),
        onResult = { result: VKAuthenticationResult ->
            when (result) {
                is VKAuthenticationResult.Success -> {
                    scope.launch(context = Dispatchers.IO) {
                        val obj = VK.executeSync(VKUsersCommand())
                        viewModel.onEvent(RegisterScreenEvents.SetPhotoUri(obj.first?.toUri()))
                        obj.second?.let {
                            viewModel.onEvent(RegisterScreenEvents.SetNameField(it))
                        }
                        obj.third?.let {
                            viewModel.onEvent(RegisterScreenEvents.SetAgeField(it.toInt()))
                        }
                    }
                }
                is VKAuthenticationResult.Failed -> {
                    Log.e("TAG", ":${result.exception.authError} ")
                }
            }
        })

//    val screenIsBusy = uiState.screenBusy.observeAsState(false)
    val state = viewModel.state.collectAsState()

    val context = LocalContext.current.applicationContext
    val cropImage = rememberLauncherForActivityResult(contract = CropImageContract()) { result ->
        when (result.isSuccessful) {
            true -> {
                val uriContent = result.uriContent
                if (uriContent != null) viewModel.onEvent(RegisterScreenEvents.SetPhotoUri(uriContent))
            }
            else -> {
                val exception = result.error
                Log.e("AVATAR_TAG", "error: $exception")
            }
        }
    }

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

//        ChangeProfilePhoto(
//            isPhotoBusy = false,
//            photoUri = state.value.photoUri,
//            context,
//            cropImage
//        )
        FormField(
            text = state.value.email,
            placeHolder = "Email",
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            valueCallback = {
                viewModel.onEvent(RegisterScreenEvents.SetEmailField(it))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        FormField(
            text = state.value.password,
            placeHolder = "Пароль",
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            valueCallback = {
                viewModel.onEvent(RegisterScreenEvents.SetPasswordField(it))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        FormField(
            text = state.value.name,
            placeHolder = "Имя",
            keyBoardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            valueCallback = {
                viewModel.onEvent(RegisterScreenEvents.SetNameField(it))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        FormField(
            text = state.value.age?.toString() ?: "",
            keyBoardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            placeHolder = "Возраст",
            valueCallback = {
                viewModel.onEvent(RegisterScreenEvents.SetAgeField(it.toInt()))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        EducationButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp), text = "Зарегистрироваться",
            enabled = viewModel.validateFields()
        ) {
            viewModel.onEvent(RegisterScreenEvents.RegisterUser)
            //launcher.launch(listOf(VKScope.EMAIL))
        }
        Spacer(modifier = Modifier.height(20.dp))
        EducationButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp), text = "Войти позже"
        ) {
            viewModel.onEvent(RegisterScreenEvents.AuthLater)
        }
    }
}

@Composable
fun ChangeProfilePhoto(
    isPhotoBusy: Boolean,
    photoUri: Uri?,
    context: Context,
    cropImage: ManagedActivityResultLauncher<CropImageContractOptions, CropImageView.CropResult>
) {
    Surface(
        modifier = Modifier
            .size(184.dp)
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                takeChangePhotoProfile(context = context, cropImage = cropImage)
            },
    color = Color.Unspecified) {

        Card(
            modifier = Modifier.wrapContentSize(),
            shape = CircleShape,
            backgroundColor = Color.DarkGray,
            elevation = 0.dp
        ) {
            SubcomposeAsyncImage(
                model = photoUri,
                contentDescription = "profile_photo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                    is AsyncImagePainter.State.Error -> {
                        when (isPhotoBusy) {
                            true -> CircularProgressIndicator(color = educationGreenColor)
                            else -> {
                                NonChangePhotoField()
                            }
                        }
                    }
                    else -> {
                        when (isPhotoBusy) {
                            true -> {
                                SubcomposeAsyncImageContent()
                                CircularProgressIndicator(
                                    color = educationGreenColor,
                                    strokeWidth = 4.dp
                                )
                            }
                            else -> {
                                SubcomposeAsyncImageContent()
                            }
                        }
                    }
                }
            }
        }
        Column(verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
//            Image(
//                painter = painterResource(id = R.drawable.ic_text_button_fluid),
//                contentDescription = "change photo icon"
//            )
        }
    }
}


fun takeChangePhotoProfile(
    context: Context,
    cropImage: ManagedActivityResultLauncher<CropImageContractOptions, CropImageView.CropResult>
) {

    val path = context.filesDir
    val letDirectory = File(path, "images")
    letDirectory.mkdirs()
    if (!letDirectory.exists()) {
        letDirectory.mkdir()
    }

    val file = Uri.fromFile(File(letDirectory, "avatar-${UUID.randomUUID()}.png"))
    cropImage.launch(
        CropImageContractOptions(
            uri = null,
            cropImageOptions = CropImageOptions(
                customOutputUri = file,
                backgroundColor = Color.Transparent.hashCode(),
                borderCornerColor = Color.Transparent.hashCode(),
                guidelines = CropImageView.Guidelines.ON_TOUCH,
                outputCompressFormat = Bitmap.CompressFormat.PNG,
                aspectRatioX = 1,
                aspectRatioY = 1,
                fixAspectRatio = true,
                cropShape = CropImageView.CropShape.OVAL,
                minCropResultHeight = 150,
                minCropResultWidth = 150
            )

        )
    )
}

@Composable
fun NonChangePhotoField() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.ic_image_field),
//            contentDescription = stringResource(id = R.string.photo_field),
//            modifier = Modifier
//                .width(50.dp)
//                .height(50.dp)
//        )
    }
}