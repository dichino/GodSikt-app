package com.example.IN2000_prosjekt.view.screens.signs

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.R
import com.example.IN2000_prosjekt.viewmodel.signs.Sign
import com.example.IN2000_prosjekt.viewmodel.signs.SignCategory
import com.example.IN2000_prosjekt.viewmodel.signs.SignViewModel

// Composable function for displaying the sign screen
@Composable
fun SignScreen(
    signViewModel: SignViewModel,
    navController: NavController
) {
    // Observe the selected category from the ViewModel
    val category: SignCategory? = signViewModel.selectedCategory.observeAsState().value
    var signList by remember { mutableStateOf<List<Sign>>(emptyList()) } // Initialize an empty list

    // Fetch the list of signs in the selected category
    LaunchedEffect(category) {
        if (category != null) {
            signList = signViewModel.getSignsInCategory(selectedCategory = category.category)
        }
    }

    Surface(color = Color(0xFF17161E), modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button_description),
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color(0xFF17161E),
                contentColor = Color.White
            )
            // Display the category name if it is not null
            if (category != null) {
                Text(
                    text = category.getNameString(),
                    fontSize = 35.sp,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(
                modifier = Modifier.height(600.dp), // Set specific width and height
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                item {
                    signList.forEach {
                        SignItem(it, signViewModel, navController)
                    }
                }
            }
        }
    }
}

// Composable function for displaying an individual sign item
@Composable
fun SignItem(
    sign: Sign,
    signViewModel: SignViewModel,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("SignDescriptionScreen")
                signViewModel.updateSign(sign)
            }
            .background(Color(0xFF2b2930), RoundedCornerShape(8.dp))
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Spacer(modifier = Modifier.width(4.dp))
        
        // Display the sign image inside a Box
        Box(
            modifier = Modifier
                .size(75.dp)
                .background(Color(0xFFD0BCFF), RoundedCornerShape(4.dp))
        ) {
            Image(
                painter = painterResource(id = signViewModel.getPicture(sign.id)),
                contentDescription = null,
                modifier = Modifier
                    .size(55.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.width(25.dp))
        // Display the sign name
        Text(
            text = sign.name,
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .semantics { heading() }
        )
        // Display an arrow icon
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}
