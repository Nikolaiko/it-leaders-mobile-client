package com.penguins.educationmultiplatform.android.mapScreen.view

import android.graphics.PointF
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.mapScreen.components.*
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolDataUi
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.data.YandexMapEvents
import com.penguins.educationmultiplatform.android.mapScreen.ui.clickedMapButtonColor
import com.penguins.educationmultiplatform.android.mapScreen.ui.getCircleColor
import com.penguins.educationmultiplatform.android.mapScreen.ui.getSelectedMarker
import com.penguins.educationmultiplatform.android.mapScreen.ui.nonClickedMapButtonColor
import com.penguins.educationmultiplatform.android.mapScreen.viewModel.YandexMapViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.image.ImageProvider.fromResource
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun YandexMapScreen(
    viewModel: YandexMapViewModel = koinViewModel()
) {

    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    LaunchedEffect(key1 = locationPermissions.allPermissionsGranted) {
        if (locationPermissions.allPermissionsGranted) {
            viewModel.onEvent(YandexMapEvents.GetCurrentLocation)
        } else
            locationPermissions.launchMultiplePermissionRequest()
    }

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

    val dialogShow = remember {
        mutableStateOf(false)
    }

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
                    val mapkit = MapKitFactory.getInstance()
                    mapkit.onStart()
                    MapView(context).also { mapView ->
                        mapView.onStart()
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
                        mapView.map.addInputListener(inputMapListener)
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
                                        getSelectedMarker((selectedMapObject!!.userData as SchoolDataUi).type )
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
                        val userLocationLayer =
                            mapkit.createUserLocationLayer(mapView.mapWindow)
                        userLocationLayer.isVisible = true
                        val userLocationObjectListener = object : UserLocationObjectListener {
                            override fun onObjectAdded(userLocationView: UserLocationView) {
                                Log.e("TAG", "onObjectAdded: ")
                                userLocationLayer.resetAnchor()
                                userLocationView.pin.setIcon(
                                    fromResource(
                                        context,
                                        R.drawable.user_location
                                    )
                                )
                                userLocationView.arrow.setIcon(
                                    fromResource(
                                        context,
                                        R.drawable.user_location
                                    )
                                )

                            }

                            override fun onObjectRemoved(p0: UserLocationView) {
                            }

                            override fun onObjectUpdated(p0: UserLocationView, p1: ObjectEvent) {
                            }
                        }

                        val clusterListener = ClusterListener { cluster ->
                            // We setup cluster appearance and tap handler in this method
                            cluster.getAppearance().setIcon(
                                TextImageProvider(
                                    context,
                                    Integer.toString(
                                        cluster.getSize()
                                    )
                                )
                            )
//                            cluster.addClusterTapListener(cluster)
                        }
                        var clusterCollection = mapView.getMap().getMapObjects()
                            .addClusterizedPlacemarkCollection(clusterListener)

                        userLocationLayer.setObjectListener(
                            userLocationObjectListener
                        )
                        scope.launch {
                            viewModel.state.collect {
                                clusterCollection.clear()
                                clusterCollection = mapView.map.mapObjects
                                    .addClusterizedPlacemarkCollection(clusterListener)
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
                                        context,
                                        clusterCollection
                                    )
                                }
                                clusterCollection.clusterPlacemarks(70.0, 17)
                            }
                        }
                        scope.launch {
                            viewModel.currentLocation.collect {
                                Log.e("TAG", "YandexMapScreen:LOCATION ")
                                if (it != null) {
                                    mapView.map.move(
                                        CameraPosition(
                                            Point(it.latitude, it.longitude),
                                            16f,
                                            0f,
                                            0f
                                        ), Animation(Animation.Type.SMOOTH, 1f), null
                                    )
                                }
//
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
                    NavigationButton(
                        locationPermissions,
                        viewModel::onEvent,
                        showDialog = { dialogShow.value = true })

                }


            }
        }
        if (dialogShow.value)
            GpsAlertDialog {
                dialogShow.value = false
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