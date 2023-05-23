package com.penguins.educationmultiplatform.android.domain.location

import android.location.Location


interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}