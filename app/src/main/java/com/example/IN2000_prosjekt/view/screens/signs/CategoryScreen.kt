package com.example.IN2000_prosjekt.view.screens.signs

import NavigationMenu
import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.R
import com.example.IN2000_prosjekt.viewmodel.signs.SignCategories
import com.example.IN2000_prosjekt.viewmodel.signs.SignCategory
import com.example.IN2000_prosjekt.viewmodel.signs.SignViewModel

@Composable
fun CategoryScreen(
    navController: NavController,
    signViewModel: SignViewModel
) {
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
    Surface(color = Color(0xFF17161E), modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Spacer(modifier = Modifier.height(70.dp))

            Text(
                text = stringResource(id = R.string.signs_and_rules),
                fontSize = 40.sp,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .semantics { heading() },
                color = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))

            LazyColumn {
                item {
                    SignCategories.values().forEach { category ->
                        makeCategory(SignCategory(category), navController, signViewModel)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
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

@Composable
fun makeCategory(
    category: SignCategory,
    navController: NavController,
    signViewModel: SignViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    navController.navigate("SignScreen")
                    signViewModel.updateCategory(category)
                } //gå til skiltvalg
                .background(
                    Color(0xFF2B2930),
                    RoundedCornerShape(15.dp)
                )
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFD0BCFF), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = category.getIcon()),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = category.getNameString(),
                color = Color.White,
                fontSize = 22.sp,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}
