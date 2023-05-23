package com.penguins.educationmultiplatform.android.mapScreen.view

import android.graphics.PointF
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.mapScreen.components.BottomSheetDetail
import com.penguins.educationmultiplatform.android.mapScreen.components.BottomSheetFilters
import com.penguins.educationmultiplatform.android.mapScreen.components.FloatButtons
import com.penguins.educationmultiplatform.android.mapScreen.components.createTappableCircle
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolDataUi
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.ui.*
import com.penguins.educationmultiplatform.android.mapScreen.viewModel.YandexMapViewModel
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.cancel
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

    val filterButtonColor = remember {
        mutableStateOf(nonClickedMapButtonColor)
    }
    val animateColorButtonFilter = animateColorAsState(
        targetValue = filterButtonColor.value, animationSpec = tween(durationMillis = 500)
    )
    val filterIconColor = remember {
        mutableStateOf(clickedMapButtonColor)
    }
    val animateIconButtonFilter = animateColorAsState(
        targetValue = filterIconColor.value, animationSpec = tween(durationMillis = 500)
    )

    val scope = rememberCoroutineScope()
    val detailBottomSheetDataState: MutableState<SchoolDataUi?> = remember { mutableStateOf(null) }
    val sheetDetailState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldDetailState = rememberBottomSheetScaffoldState(bottomSheetState = sheetDetailState)

    BottomSheetScaffold(
        sheetContent = {
            if (detailBottomSheetDataState.value != null) {
                BottomSheetDetail(detail = detailBottomSheetDataState.value!!) {
                    scope.launch {
                        sheetDetailState.collapse()
                        detailBottomSheetDataState.value = null
                    }
                }
            }
        },
        sheetElevation = 2.dp,
        sheetPeekHeight = 0.dp,
        scaffoldState = scaffoldDetailState,
        sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
        sheetBackgroundColor = clickedMapButtonColor
    ) {
        val sheetFilterState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
        val scaffoldFilterState =
            rememberBottomSheetScaffoldState(bottomSheetState = sheetFilterState)
        if (sheetFilterState.isCollapsed) {
            filterButtonColor.value = nonClickedMapButtonColor
            filterIconColor.value = clickedMapButtonColor
        } else {
            filterButtonColor.value = clickedMapButtonColor
            filterIconColor.value = nonClickedMapButtonColor
        }
        BottomSheetScaffold(
            sheetContent = {
                BottomSheetFilters()
            },
            sheetPeekHeight = 0.dp,
            scaffoldState = scaffoldFilterState,
            floatingActionButton = {
                FloatButtons(uiState = uiState, onEvent = viewModel::onEvent)
            },
            floatingActionButtonPosition = FabPosition.Center,
            sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
            sheetBackgroundColor = clickedMapButtonColor,
            sheetElevation = 2.dp,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AndroidView(factory = { context ->
                    MapKitFactory.initialize(context)
                    MapKitFactory.getInstance().onStart()
                    MapView(context).also { mapView ->
                        val mapObjects = mapView.map.mapObjects.addCollection()
                        mapView.map.move(CameraPosition(Point(55.754405, 37.619992), 14f, 0f, 0f))
                        var selectedMapObject: PlacemarkMapObject? = null

                        val inputMapListener = object : InputListener {
                            override fun onMapTap(p0: Map, p1: Point) {
                                selectedMapObject?.let {
                                    val userData = selectedMapObject!!.userData
                                    if (userData is SchoolDataUi) {
                                        selectedMapObject!!.setIcon(
                                            ImageProvider.fromResource(
                                                context,
                                                getCircleColor(userData.type)
                                            )
                                        )
                                    }
                                }
                                if (sheetDetailState.isExpanded)
                                    scope.launch {
                                        sheetDetailState.collapse()
                                        detailBottomSheetDataState.value = null
                                    }
                                if (sheetFilterState.isExpanded)
                                    scope.launch {
                                        sheetFilterState.collapse()
                                    }
                                selectedMapObject = null
                                mapView.map.removeInputListener(this)
                            }

                            override fun onMapLongTap(p0: Map, p1: Point) {
                            }
                        }

                        val onCircleTapListener = MapObjectTapListener { mapObject, point ->
                            if (mapObject is PlacemarkMapObject) {
                                mapView.map.addInputListener(inputMapListener)
                                selectedMapObject?.let {
                                    selectedMapObject!!.setIcon(
                                        ImageProvider.fromResource(
                                            context,
                                            getCircleColor((selectedMapObject!!.userData as SchoolDataUi).type)
                                        )
                                    )
                                }
                                selectedMapObject = mapObject
                                mapObject.setIcon(
                                    ImageProvider.fromResource(
                                        context,
                                        R.drawable.subtract
                                    ),
                                    IconStyle().setAnchor(PointF(0.5f, 1f))
                                )
                                val userData = mapObject.userData
                                if (userData is SchoolDataUi) {
                                    Log.e(
                                        "TAG",
                                        "YandexMapScreen: ${sheetDetailState.isCollapsed} ",
                                    )
                                    scope.launch {
                                        detailBottomSheetDataState.value = userData
                                        if (sheetFilterState.isExpanded)
                                            sheetFilterState.collapse()
                                        sheetDetailState.expand()
                                        sheetDetailState.expand()

                                    }
                                }
                            }
                            return@MapObjectTapListener true
                        }
                        mapView.map.addInputListener(inputMapListener)
                        scope.launch {
                            viewModel.state.collect {
                                mapObjects.clear()
                                selectedMapObject = null
                                it.schools.filter { school ->
                                    when (school.type) {
                                        SchoolType.DANCING -> {
                                            it.filters.dancingFilter
                                        }
                                        SchoolType.MUSICAL -> it.filters.musicalFilter
                                        SchoolType.ARTISTIC -> it.filters.artistFilter
                                        SchoolType.THEATRICAL -> it.filters.theatricalFilter
                                    }
                                }.forEach { school ->
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
                                if (sheetFilterState.isCollapsed) {
                                    if (sheetDetailState.isExpanded)
                                        sheetDetailState.collapse()
                                    sheetFilterState.expand()
                                } else {
                                    sheetFilterState.collapse()
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
            scope.cancel()
            lifecycleOwner.lifecycle.removeObserver(eventObserver)
        }
    })
}