package com.penguins.educationmultiplatform.android.mapScreen.components

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.penguins.educationmultiplatform.android.R
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolDataUi
import com.penguins.educationmultiplatform.android.mapScreen.data.SchoolType
import com.penguins.educationmultiplatform.android.mapScreen.ui.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BottomSheetDetail(detail: SchoolDataUi, onClickClose: () -> Unit) {

    val context = LocalContext.current
    val permission = rememberPermissionState(
        permission = Manifest.permission.CALL_PHONE,
    )
    val intent = Intent(Intent.ACTION_CALL)
    val gradientBackground = when(detail.type){
        SchoolType.MUSICAL -> gradientMusicalBackgroundSheet
        SchoolType.THEATRICAL -> gradientTheaterBackgroundSheet
        SchoolType.DANCING -> gradientDancingBackgroundSheet
        SchoolType.ARTISTIC -> gradientArtistBackgroundSheet

    }
    Column(
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth()
            .background(
                brush = gradientBackground,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Divider(modifier = Modifier.width(35.dp), color = Color(0x99FFFFFF), thickness = 3.dp)
        Spacer(modifier = Modifier.height(28.dp))
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .wrapContentHeight(),
                    text = detail.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color(0xFF101010)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.Close,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onClickClose
                        ),
                    contentDescription = null,
                    tint = fontCardColor
                )

            }
            Spacer(Modifier.height(6.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = detail.address,
                fontSize = 18.sp,
                color = Color(0xFF7C7C7B),
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = detail.description,
                fontSize = 18.sp,
                color = Color(0xFF7C7C7B),
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier
                    .height(48.dp)
                    .wrapContentWidth()
                    .align(Alignment.Start),
                onClick = {
                    if (permission.status.isGranted) {
                        intent.data = Uri.parse("tel:${detail.phoneNumber}")
                        context.startActivity(intent)
                    } else
                        permission.launchPermissionRequest()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonBottomSheetColor,
                    contentColor = fontCardColor
                ),
                elevation = ButtonDefaults.elevation(0.dp)
            ) {
                Icon(imageVector = Icons.Default.Call, contentDescription = null)
                Spacer(modifier = Modifier.width(13.dp))
                Text(
                    text = detail.phoneNumber,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    color = Color(0xFF101010)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_world),
                        contentDescription = null,
                        tint = theatricalSchoolColor
                    )
                    Spacer(modifier = Modifier.width(11.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .wrapContentHeight(),
                        text = detail.email,
                        fontSize = 16.sp,
                        color = theatricalSchoolColor,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Start
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_arrows__1_),
                    contentDescription = null,
                    tint = theatricalSchoolColor
                )
            }
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}