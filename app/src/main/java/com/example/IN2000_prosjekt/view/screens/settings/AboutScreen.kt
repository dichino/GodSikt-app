package com.example.IN2000_prosjekt.view.screens.settings

import NavigationMenu
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.R


@Composable
fun AboutScreen(
    navController: NavController
) {
    var lastOrientation by remember { mutableIntStateOf(Configuration.ORIENTATION_UNDEFINED) }
    val configuration = LocalConfiguration.current

    // Handle orientation change
    LaunchedEffect(configuration.orientation) {
        if (lastOrientation != configuration.orientation) {
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
    // Set the background color and fill the screen
    Surface(color = Color(0xFF17161E), modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .semantics { isTraversalGroup = true },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(85.dp))
            Text(
                text = stringResource(id = R.string.about),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .semantics { heading() },
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 4.sp),
                color = Color.White,
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.height(35.dp))
            Divider(
                color = Color.White,
                thickness = 1.dp,
                modifier = Modifier
                    .width(330.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(35.dp))

            // Navigation item for the "About Us" screen
            LazyColumn {
                item {
                    Row(
                        modifier = Modifier
                            .padding(60.dp, 10.dp)
                            .height(70.dp)
                            .clickable { navController.navigate("AboutUsScreen") }
                    ) {
                        Text(
                            text = stringResource(id = R.string.about_us),
                            color = Color.White,
                            fontSize = 25.sp,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .semantics { traversalIndex = 1F }
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(30.dp)
                                .clickable { navController.navigate("AboutUsScreen") }
                        )
                    }
                    // Navigation item for the "Privacy" screen
                    Row(
                        modifier = Modifier
                            .padding(60.dp, 10.dp)
                            .height(70.dp)
                            .clickable { navController.navigate("PrivacyInfoScreen") }
                    ) {
                        Text(
                            text = stringResource(id = R.string.privacy),
                            color = Color.White,
                            fontSize = 25.sp,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .semantics { traversalIndex = 2F }
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(30.dp)
                                .clickable { navController.navigate("PrivacyInfoScreen") }
                        )
                    }
                    // Navigation item for the "Terms and Conditions" screen
                    Row(
                        modifier = Modifier
                            .padding(60.dp, 10.dp)
                            .height(70.dp)
                            .clickable { navController.navigate("TermsAndConditionsScreen") }
                    ) {
                        Text(
                            text = stringResource(id = R.string.terms_and_conditions),
                            color = Color.White,
                            fontSize = 25.sp,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .semantics { traversalIndex = 3F }
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(30.dp)
                                .clickable { navController.navigate("TermsAndConditionsScreen") }
                        )
                    }
                    // Navigation item for the "About Data Sources" screen
                    Row(
                        modifier = Modifier
                            .padding(60.dp, 10.dp)
                            .height(70.dp)
                            .clickable { navController.navigate("AboutDataSourcesScreen") }
                    ) {
                        Text(
                            text = stringResource(id = R.string.about_data_sources),
                            color = Color.White,
                            fontSize = 25.sp,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .semantics { traversalIndex = 4F }
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .size(30.dp)
                                .clickable { navController.navigate("AboutDataSourcesScreen") }
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        // Display the navigation menu at the bottom if in portrait mode
        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            Box {
                NavigationMenu(
                    navController = navController,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}









