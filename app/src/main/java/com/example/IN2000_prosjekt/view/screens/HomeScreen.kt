package com.example.IN2000_prosjekt.view.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.MainActivity
import com.example.IN2000_prosjekt.R

@Composable
fun HomeScreen(navController: NavController, activity: MainActivity) {
    val image = painterResource(R.drawable.logo)

    // Get the screen height in pixels
    val screenHeight = with(LocalDensity.current) {
        (LocalConfiguration.current.screenHeightDp * density).toInt()
    }
    var lastOrientation by remember { mutableIntStateOf(Configuration.ORIENTATION_UNDEFINED) }
    val configuration = LocalConfiguration.current

    LaunchedEffect(configuration.orientation) {
        if (lastOrientation != configuration.orientation) {
            // Handle orientation change
            lastOrientation = configuration.orientation
            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                }
                Configuration.ORIENTATION_PORTRAIT -> {
                }
                Configuration.ORIENTATION_UNDEFINED -> {
                }
            }
        }
    }
    if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = stringResource(id = R.string.logo_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size((screenHeight * 0.15).dp),
            )
            Text(
                modifier = Modifier
                    .offset(y = (screenHeight * -0.01).dp)
                    .semantics { heading() },
                text = stringResource(id = R.string.app_name_heading),
                fontSize = 70.sp,
                fontWeight = FontWeight.ExtraLight,
            )
            Text(
                modifier = Modifier
                    .offset(y = (screenHeight * 0.01).dp),
                text = stringResource(id = R.string.permission_needed),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(0.2f),
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.25f)
            )
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.permission_message),
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraLight,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { activity.checkAndRequestLocationPermissions() },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF6358DC)
                    ),
                    modifier = Modifier
                        .height(80.dp)
                        .width(200.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.start_button_text),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = stringResource(id = R.string.logo_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size((screenHeight * 0.05).dp),
            )
            Text(
                modifier = Modifier
                    .offset(y = (screenHeight * -0.005).dp)
                    .semantics { heading() },
                text = stringResource(id = R.string.app_name_heading),
                fontSize = 70.sp,
                fontWeight = FontWeight.ExtraLight,
            )
            Text(
                modifier = Modifier
                    .offset(y = (screenHeight * 0.01).dp),
                text = stringResource(id = R.string.permission_needed),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(0.2f),
                thickness = 1.dp,
                color = Color.White.copy(alpha = 0.25f)
            )
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.permission_message),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraLight,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { activity.checkAndRequestLocationPermissions() },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF6358DC)
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.start_button_text),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }
}
