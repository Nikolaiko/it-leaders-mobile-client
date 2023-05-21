package com.penguins.educationmultiplatform.android.mapScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.penguins.educationmultiplatform.android.mapScreen.ui.clickedMapButtonColor

@Composable
fun BottomSheetFilters() {

    Column(
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Divider(modifier = Modifier.width(35.dp), color = Color(0xFFD9D9D9), thickness = 3.dp)
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Фильтры",
            fontSize = 32.sp,
            fontWeight = FontWeight.W700,
            color = Color(0xFF101010)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Местоположение",
                fontSize = 18.sp,
                fontWeight = FontWeight.W700,
                color = Color(0xFF101010)
            )
            Spacer(modifier = Modifier.width(9.dp))
            Divider(modifier = Modifier.fillMaxWidth(), color = Color(0xFF101010), thickness = 1.dp)

        }
        Spacer(modifier = Modifier.height(16.dp))

        val selectDistrict: MutableState<Districts> = remember {
            mutableStateOf(Districts.All)
        }
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            DistrictRadioItem(
                selected = selectDistrict.value is Districts.All,
                district = Districts.All,
                onClick = { selectDistrict.value = it })
            DistrictRadioItem(
                selected = selectDistrict.value is Districts.Center,
                district = Districts.Center,
                onClick = { selectDistrict.value = it })
            DistrictRadioItem(
                selected = selectDistrict.value is Districts.East,
                district = Districts.East,
                onClick = { selectDistrict.value = it })
            DistrictRadioItem(
                selected = selectDistrict.value is Districts.North,
                district = Districts.North,
                onClick = { selectDistrict.value = it })
            DistrictRadioItem(
                selected = selectDistrict.value is Districts.North_East,
                district = Districts.North_East,
                onClick = { selectDistrict.value = it })
            DistrictRadioItem(
                selected = selectDistrict.value is Districts.South,
                district = Districts.South,
                onClick = { selectDistrict.value = it })
            DistrictRadioItem(
                selected = selectDistrict.value is Districts.SouthEast,
                district = Districts.SouthEast,
                onClick = { selectDistrict.value = it })
            DistrictRadioItem(
                selected = selectDistrict.value is Districts.South_West,
                district = Districts.South_West,
                onClick = { selectDistrict.value = it })
            DistrictRadioItem(
                selected = selectDistrict.value is Districts.West,
                district = Districts.West,
                onClick = { selectDistrict.value = it })
        }
    }
}

@Composable
fun DistrictRadioItem(selected: Boolean, district: Districts, onClick: (Districts) -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                null
            ) { onClick.invoke(district) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF8E74A8),
                unselectedColor = Color(0xFF8E74A8)
            )
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = district.text,
            color = Color(0xFF101010),
            fontSize = 16.sp,
            fontWeight = FontWeight.W400
        )

    }
}

sealed class Districts(val text: String) {
    object All : Districts("Все")
    object Center : Districts("Центральный")
    object North : Districts("Северный")
    object North_East : Districts("Северо-Восточный")
    object East : Districts("Восточный")
    object SouthEast : Districts("Юго-Восточный")
    object South : Districts("Южный")
    object South_West : Districts("Северо-Западный")
    object West : Districts("Западный")

}