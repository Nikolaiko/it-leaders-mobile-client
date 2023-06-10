package com.penguins.educationmultiplatform.android.mapScreen.components

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolDataUi
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.yandex.mapkit.map.*
import com.yandex.runtime.image.ImageProvider


data class CircleMapObjectUserData internal constructor(val id: Int, val description: String)

fun createTappableCircle(
    school: SchoolDataUi,
    circleMapObjectTapListener: MapObjectTapListener,
    mapObjects: MapObjectCollection,
    context: Context,
    clusterCollection: ClusterizedPlacemarkCollection
) {
    val imageProvider = when (school.type) {
        SchoolType.ARTISTIC -> {
            ImageProvider.fromResource(context, R.drawable.artist_circle)
        }
        SchoolType.MUSICAL -> {
            ImageProvider.fromResource(context, R.drawable.musical_circle)
        }
        SchoolType.DANCING -> {
            ImageProvider.fromResource(context, R.drawable.dancing_circle)
        }
        SchoolType.THEATRICAL -> {
            ImageProvider.fromResource(context, R.drawable.theatrical_circle)
        }
    }
    val circle = clusterCollection.addPlacemark(school.coords, imageProvider)
//    circle.setText(
//        school.name,
//        TextStyle(
//            12f,
//            Color(0xFF000000).toArgb(),
//            null,
//            TextStyle.Placement.TOP_RIGHT,
//            4f,
//            true,
//            true
//        )
//    )
    circle.zIndex = 100.0f
    circle.userData = school
    // Client code must retain strong reference to the listener.
    circle.addTapListener(circleMapObjectTapListener)
}
