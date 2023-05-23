package com.penguins.educationmultiplatform.android.mapScreen.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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
        Spacer(modifier = Modifier.height(16.dp))
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
    AnimatedVisibility(
        modifier = Modifier.padding(start = 32.dp),
        visible = selected,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(500)
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(500)
        )
    ) {
        if (selected)
            Column() {
                when (district) {
                    is Districts.All -> {
                    }
                    is Districts.Center -> {
                        CheckBoxes(district = district)
                    }
                    is Districts.East -> CheckBoxes(district = district)

                    is Districts.North -> CheckBoxes(district = district)

                    is Districts.North_East -> CheckBoxes(district = district)

                    is Districts.South -> CheckBoxes(district = district)

                    is Districts.SouthEast -> CheckBoxes(district = district)

                    is Districts.South_West -> CheckBoxes(district = district)

                    is Districts.West -> CheckBoxes(district = district)

                }
            }
    }

}


@Composable
fun CheckBoxes(district: Districts) {
    val allCheck = remember { mutableStateOf(false) }
    StreetCheckBox(street = null, selected = allCheck.value) {
        allCheck.value = !allCheck.value
    }
    district.districts.forEach {
        val checked = remember {
            mutableStateOf(false)
        }
        if (allCheck.value) checked.value = true

        StreetCheckBox(
            street = it.first,
            selected = if (allCheck.value) true else checked.value
        ) {
            checked.value = !checked.value
            allCheck.value = !district.districts.any { !it.second }
        }
    }

}

@Composable
fun StreetCheckBox(street: String?, selected: Boolean, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                null
            ) { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = selected,
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF8E74A8),
                uncheckedColor = Color(0xFF8E74A8)
            )
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = street ?: "Все",
            color = Color(0xFF101010),
            fontSize = 16.sp,
            fontWeight = FontWeight.W400
        )
    }
}

sealed class Districts(val text: String, val districts: List<Pair<String, Boolean>>) {
    object All : Districts("Все", emptyList())
    object Center : Districts(
        "Центральный",
        listOf(
            Pair("Арбат", false),
            Pair("Басманный", false),
            Pair("Замоскворечье", false),
            Pair("Красносельский", false),
            Pair("Мещанский", false)
        )
    )

    object North : Districts(
        "Северный",
        listOf(
            Pair("Арбат", false),
            Pair("Басманный", false),
            Pair("Замоскворечье", false),
            Pair("Красносельский", false),
            Pair("Мещанский", false)
        )
    )

    object North_East : Districts(
        "Северо-Восточный",
        listOf(
            Pair("Арбат", false),
            Pair("Басманный", false),
            Pair("Замоскворечье", false),
            Pair("Красносельский", false),
            Pair("Мещанский", false)
        )
    )

    object East : Districts(
        "Восточный",
        listOf(
            Pair("Арбат", false),
            Pair("Басманный", false),
            Pair("Замоскворечье", false),
            Pair("Красносельский", false),
            Pair("Мещанский", false)
        )
    )

    object SouthEast : Districts(
        "Юго-Восточный",
        listOf(
            Pair("Арбат", false),
            Pair("Басманный", false),
            Pair("Замоскворечье", false),
            Pair("Красносельский", false),
            Pair("Мещанский", false)
        )
    )

    object South : Districts(
        "Южный",
        listOf(
            Pair("Арбат", false),
            Pair("Басманный", false),
            Pair("Замоскворечье", false),
            Pair("Красносельский", false),
            Pair("Мещанский", false)
        )
    )

    object South_West : Districts(
        "Северо-Западный",
        listOf(
            Pair("Арбат", false),
            Pair("Басманный", false),
            Pair("Замоскворечье", false),
            Pair("Красносельский", false),
            Pair("Мещанский", false)
        )
    )

    object West : Districts(
        "Западный",
        listOf(
            Pair("Арбат", false),
            Pair("Басманный", false),
            Pair("Замоскворечье", false),
            Pair("Красносельский", false),
            Pair("Мещанский", false)
        )
    )

}