package com.penguins.educationmultiplatform.android.mapScreen.components

import android.content.Context
import android.location.LocationManager
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.mapScreen.data.YandexMapEvents
import com.penguins.educationmultiplatform.android.mapScreen.ui.clickedMapButtonColor
import com.penguins.educationmultiplatform.android.mapScreen.ui.nonClickedMapButtonColor

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavigationButton(
    locationPermissions: MultiplePermissionsState,
    onEvent: (YandexMapEvents) -> Unit,
    showDialog:()->Unit
) {

    val locationManager = LocalContext.current.getSystemService(
        Context.LOCATION_SERVICE
    ) as LocationManager
    Card(modifier = Modifier
        .size(48.dp)
        .clickable(remember { MutableInteractionSource() }, null) {
            if (!locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER) &&
                !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            ){
                showDialog.invoke()
            }
                when {
                    locationPermissions.allPermissionsGranted -> {
                        onEvent.invoke(YandexMapEvents.GetCurrentLocation)
                    }
                    locationPermissions.shouldShowRationale -> {
                        locationPermissions.launchMultiplePermissionRequest()
                    }
                    !locationPermissions.allPermissionsGranted && !locationPermissions.shouldShowRationale -> {
                        locationPermissions.launchMultiplePermissionRequest()

                    }
                }


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