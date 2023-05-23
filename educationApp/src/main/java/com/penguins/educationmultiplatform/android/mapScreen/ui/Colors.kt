package com.penguins.educationmultiplatform.android.mapScreen.ui

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType

val fontCardColor = Color(0xFF101010)
val nonClickedMapButtonColor = Color(0xFF3B4560)
val clickedMapButtonColor = Color(0xFFFFFFFF)
val musicalSchoolColor = Color(0xFF9AAE4F)
val theatricalSchoolColor = Color(0xFF8E74A8)
val artistSchoolColor = Color(0xFFBA768F)
val dancingSchoolColor = Color(0xFF81AE7B)
val buttonBottomSheetColor = Color(0xFFC9D670)
val gradientMusicalBackgroundSheet = Brush.verticalGradient(0.0f to Color(0xFF9ABE95), 0.4f to Color(0xFFFFFFFF))
val gradientArtistBackgroundSheet = Brush.verticalGradient(0.0f to Color(0xFFDBB0C2), 0.4f to Color(0xFFFFFFFF))
val gradientTheaterBackgroundSheet = Brush.verticalGradient(0.0f to Color(0xFFBCADCC), 0.4f to Color(0xFFFFFFFF))
val gradientDancingBackgroundSheet = Brush.verticalGradient(0.0f to Color(0xFF9ABE95), 0.4f to Color(0xFFFFFFFF))


fun getCircleColor(type:SchoolType):Int =
    when (type) {
        SchoolType.DANCING -> R.drawable.dancing_circle
        SchoolType.THEATRICAL -> R.drawable.theatrical_circle
        SchoolType.ARTISTIC -> R.drawable.artist_circle
        SchoolType.MUSICAL -> R.drawable.musical_circle
    }
fun getSelectedMarker(type:SchoolType) =
    when (type) {
        SchoolType.DANCING -> R.drawable.marker_dance
        SchoolType.THEATRICAL -> R.drawable.marker_theatre
        SchoolType.ARTISTIC -> R.drawable.marker_art
        SchoolType.MUSICAL -> R.drawable.marker_music
    }