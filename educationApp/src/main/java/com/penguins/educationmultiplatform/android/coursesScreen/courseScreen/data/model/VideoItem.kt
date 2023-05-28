package com.penguins.educationmultiplatform.android.coursesScreen.courseScreen.data.model

import android.net.Uri
import androidx.media3.common.MediaItem

data class VideoItem(
    val uri:Uri,
    val mediaItem:MediaItem = MediaItem.fromUri(uri)
)
