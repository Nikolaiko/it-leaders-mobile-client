package com.penguins.educationmultiplatform.android.mapScreen.view

import android.graphics.PointF
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.mapScreen.components.BottomSheetFilters
import com.penguins.educationmultiplatform.android.mapScreen.components.CircleMapObjectUserData
import com.penguins.educationmultiplatform.android.mapScreen.components.createTappableCircle
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.data.YandexMapEvents
import com.penguins.educationmultiplatform.android.mapScreen.ui.*
import com.penguins.educationmultiplatform.android.mapScreen.viewModel.YandexMapViewModel
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Circle
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.Runtime.getApplicationContext
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun YandexMapScreen(
    viewModel: YandexMapViewModel = koinViewModel()
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    val uiState = viewModel.state.collectAsState()

    val paddingCards = remember { mutableStateOf(34.dp) }
    val animatePaddingCards = animateDpAsState(targetValue = paddingCards.value)

    val filterButtonColor = remember {
        mutableStateOf(nonClickedMapButtonColor)
    }
    val animateColorButtonFilter = animateColorAsState(
        targetValue = filterButtonColor.value,
        animationSpec = tween(durationMillis = 500)
    )
    val filterIconColor = remember {
        mutableStateOf(clickedMapButtonColor)
    }
    val animateIconButtonFilter = animateColorAsState(
        targetValue = filterIconColor.value,
        animationSpec = tween(durationMillis = 500)
    )

    val scope = rememberCoroutineScope()
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    BottomSheetScaffold(
        sheetContent = {
            BottomSheetFilters()
        },
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(bottom = animatePaddingCards.value)
            ) {
                Spacer(modifier = Modifier.width(15.dp))
                SchoolCard(
                    schoolType = null,
                    modifier = Modifier,
                    selected = uiState.value.filters.artistFilter && uiState.value.filters.dancingFilter && uiState.value.filters.musicalFilter && uiState.value.filters.theatricalFilter,
                    onClick = viewModel::onEvent
                )
                Spacer(modifier = Modifier.width(8.dp))
                SchoolCard(
                    schoolType = SchoolType.MUSICAL,
                    modifier = Modifier,
                    selected = uiState.value.filters.musicalFilter,
                    onClick = viewModel::onEvent
                )
                Spacer(modifier = Modifier.width(8.dp))
                SchoolCard(
                    schoolType = SchoolType.ARTISTIC,
                    modifier = Modifier,
                    selected = uiState.value.filters.artistFilter,
                    onClick = viewModel::onEvent
                )
                Spacer(modifier = Modifier.width(8.dp))
                SchoolCard(
                    schoolType = SchoolType.THEATRICAL,
                    modifier = Modifier,
                    selected = uiState.value.filters.theatricalFilter,
                    onClick = viewModel::onEvent
                )
                Spacer(modifier = Modifier.width(8.dp))
                SchoolCard(
                    schoolType = SchoolType.DANCING,
                    modifier = Modifier,
                    selected = uiState.value.filters.dancingFilter,
                    onClick = viewModel::onEvent
                )
                Spacer(modifier = Modifier.width(15.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
        sheetBackgroundColor = clickedMapButtonColor
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            AndroidView(factory = { context ->
                MapKitFactory.initialize(context)
                MapKitFactory.getInstance().onStart()
                MapView(context).also { mapView ->
                    val mapObjects = mapView.map.mapObjects.addCollection()
                    mapView.map.move(CameraPosition(Point(55.754405, 37.619992), 14f, 0f, 0f))

                    val onCircleTapListener = MapObjectTapListener { mapObject, point ->
                        if (mapObject is CircleMapObject) {
                            val circle = mapObject
                            val randomRadius = 100.0f + 50.0f * Random().nextFloat()
                            val curGeometry = circle.geometry
                            val newGeometry = Circle(curGeometry.center, curGeometry.radius + 50f)
                            circle.geometry = newGeometry
                            val userData = circle.userData
                            if (userData is CircleMapObjectUserData) {
                                val circleUserData: CircleMapObjectUserData? =
                                    userData as CircleMapObjectUserData?
                                val toast = Toast.makeText(
                                    getApplicationContext(),
                                    "Circle with id " + circleUserData?.id + " and description '" + circleUserData?.description + "' tapped",
                                    Toast.LENGTH_SHORT
                                )
                                toast.show()
                            }
                        }
                        return@MapObjectTapListener true
                    }

                    val inputMapListener = object : InputListener {
                        override fun onMapTap(p0: Map, p1: Point) {
                            TODO("Not yet implemented")
                        }

                        override fun onMapLongTap(p0: Map, p1: Point) {
                            TODO("Not yet implemented")
                        }
                    }

                    scope.launch {
                        viewModel.state.collect {

                            it.schools.forEach { school ->
                                createTappableCircle(
                                    school,
                                    circleMapObjectTapListener = onCircleTapListener,
                                    mapObjects,
                                    context
                                )
                            }
                        }
                    }
                }

            })

            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
                    .padding(top = 32.dp, end = 16.dp)
            ) {
                Card(modifier = Modifier
                    .size(48.dp)
                    .clickable(remember { MutableInteractionSource() }, null) {
                        scope.launch {
                            if (sheetState.isCollapsed) {
                                filterButtonColor.value = clickedMapButtonColor
                                filterIconColor.value = nonClickedMapButtonColor
                                paddingCards.value = 0.dp
                                sheetState.expand()
                            } else {
                                filterButtonColor.value = nonClickedMapButtonColor
                                filterIconColor.value = clickedMapButtonColor
                                paddingCards.value = 34.dp
                                sheetState.collapse()
                            }
                        }

                    },
                    backgroundColor = animateColorButtonFilter.value,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.padding(5.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.filter_alt),
                        contentDescription = null,
                        tint = animateIconButtonFilter.value
                    )
                }
                Spacer(Modifier.height(16.dp))
                Card(modifier = Modifier
                    .size(48.dp)
                    .clickable(remember { MutableInteractionSource() }, null) {

                    }, shape = CircleShape, backgroundColor = nonClickedMapButtonColor
                ) {
                    Icon(
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 13.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.send),
                        contentDescription = null,
                        tint = clickedMapButtonColor
                    )
                }

            }


        }
    }

    DisposableEffect(key1 = lifecycleOwner, effect = {
        val eventObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {

                }
                Lifecycle.Event.ON_START -> {
                }
                Lifecycle.Event.ON_RESUME -> {
//                    when {
//                        permissionStates.allPermissionsGranted -> {
//                            coroutineScope.launch {
//                                locationSettingDialog(context = localContext,
//                                    onSuccess = {
//                                        startService.invoke()
//                                        sendMapEvent.invoke(MapState.InitState())
//                                    },
//                                    onFailure =
//                                    { intentSenderRequest ->
//                                        enableLocationSettingLauncher.launch(
//                                            intentSenderRequest
//                                        )
//                                    }
//                                )
//                            }
//                            permissionDialog.value = false
//                        }
//                        permissionStates.shouldShowRationale -> {
//                            permissionStates.launchMultiplePermissionRequest()
//                        }
//                        !permissionStates.allPermissionsGranted && !permissionStates.shouldShowRationale -> {
//                            permissionStates.launchMultiplePermissionRequest()
//                            permissionDialog.value = true
//                        }
//                    }

                }
                Lifecycle.Event.ON_PAUSE -> {}
                Lifecycle.Event.ON_STOP -> {
                    MapKitFactory.getInstance().onStop()
                }
                Lifecycle.Event.ON_DESTROY -> {
                }
                Lifecycle.Event.ON_ANY -> {
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(eventObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(eventObserver)
        }
    })


}

@Composable
fun SchoolCard(
    schoolType: SchoolType?,
    modifier: Modifier,
    selected: Boolean,
    onClick: (YandexMapEvents) -> Unit
) {

    val animatedColorBackground = animateColorAsState(
        targetValue = if (selected) {
            when (schoolType) {
                SchoolType.DANCING -> {
                    dancingSchoolColor
                }
                SchoolType.MUSICAL -> {
                    musicalSchoolColor
                }
                SchoolType.ARTISTIC -> {
                    artistSchoolColor
                }
                SchoolType.THEATRICAL -> {
                    theatricalSchoolColor
                }
                null -> {
                    nonClickedMapButtonColor
                }
            }
        } else clickedMapButtonColor
    )
    Card(
        modifier = Modifier
            .height(60.dp)
            .wrapContentWidth()
            .clickable(interactionSource = remember {
                MutableInteractionSource()
            }, indication = null, onClick = {
                onClick.invoke(
                    when (schoolType) {
                        SchoolType.DANCING -> {
                            YandexMapEvents.SetDancingFilter
                        }
                        SchoolType.MUSICAL -> {
                            YandexMapEvents.SetMusicFilter
                        }
                        SchoolType.ARTISTIC -> {
                            YandexMapEvents.SetArtistFilter
                        }
                        SchoolType.THEATRICAL -> {
                            YandexMapEvents.SetTheaterFilter
                        }
                        null -> {
                            YandexMapEvents.SetAllFilter
                        }
                    }
                )
            }),
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        backgroundColor = animatedColorBackground.value
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                text = schoolType?.nameSchool ?: "Все",
                fontSize = 16.sp,
                color = if (selected) clickedMapButtonColor else fontCardColor,
                fontWeight = if (selected) FontWeight.W700 else FontWeight.W400,
                style = if (selected) MaterialTheme.typography.h4.copy(
                    shadow = Shadow(
                        color = Color(0xFF000000),
                        offset = Offset(x = 0f, y = 1f),
                        blurRadius = 1f
                    )
                ) else LocalTextStyle.current,
                textAlign = TextAlign.Center
            )
        }
    }
}