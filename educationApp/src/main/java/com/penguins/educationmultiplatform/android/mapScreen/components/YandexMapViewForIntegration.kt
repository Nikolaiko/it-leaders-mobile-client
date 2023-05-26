package com.penguins.educationmultiplatform.android.mapScreen.components

import android.graphics.PointF
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolDataUi
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.ui.clickedMapButtonColor
import com.penguins.educationmultiplatform.android.mapScreen.ui.getCircleColor
import com.penguins.educationmultiplatform.android.mapScreen.ui.getSelectedMarker
import com.penguins.educationmultiplatform.android.mapScreen.viewModel.YandexMapViewModel
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun YandexMapScreenForIntegration(
    modifier: Modifier = Modifier,
    viewModel: YandexMapViewModel = koinViewModel(),
    type: SchoolType
) {
    val scope = rememberCoroutineScope()
    val detailBottomSheetDataState: MutableState<SchoolDataUi?> = remember { mutableStateOf(null) }
    val sheetDetailState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldDetailState = rememberBottomSheetScaffoldState(bottomSheetState = sheetDetailState)

    BottomSheetScaffold(
        modifier = modifier,
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
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(factory = { context ->
                MapKitFactory.initialize(context)
                val mapkit = MapKitFactory.getInstance()
                MapKitFactory.getInstance().onStart()
                MapView(context).also { mapView ->
                    mapView.onStart()
                    val mapObjects = mapView.map.mapObjects.addCollection()
                    mapView.map.move(CameraPosition(Point(55.754405, 37.619992), 10f, 0f, 0f))
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
                                    getSelectedMarker((selectedMapObject!!.userData as SchoolDataUi).type)
                                ),
                                IconStyle().setAnchor(PointF(0.5f, 1f))
                            )
                            val userData = mapObject.userData
                            if (userData is SchoolDataUi) {
                                scope.launch {
                                    detailBottomSheetDataState.value = userData
                                    sheetDetailState.expand()
                                    sheetDetailState.expand()

                                }
                            }
                        }
                        return@MapObjectTapListener true
                    }

                    val clusterListener = ClusterListener { cluster ->
                        // We setup cluster appearance and tap handler in this method
                        cluster.appearance.setIcon(
                            TextImageProvider(
                                context,
                                cluster.size.toString()
                            )
                        )
                    }
                    var clusterCollection = mapView.map.mapObjects
                        .addClusterizedPlacemarkCollection(clusterListener)

                    scope.launch {
                        viewModel.state.collect {
                            clusterCollection.clear()
                            clusterCollection = mapView.map.mapObjects
                                .addClusterizedPlacemarkCollection(clusterListener)
                            mapObjects.clear()
                            selectedMapObject = null
                            it.schools.filter { school ->
                                when (type) {
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
                }
            })

        }
    }
}