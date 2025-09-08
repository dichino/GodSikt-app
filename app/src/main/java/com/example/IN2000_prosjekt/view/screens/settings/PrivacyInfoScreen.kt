package com.example.IN2000_prosjekt.view.screens.settings

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.R

@Composable
fun PrivacyInfoScreen(
    navController: NavController
) {
    var textSize by remember { mutableStateOf(25.sp) }

    Surface(color = Color(0xFF17161E), modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar with Back Button
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button_description),
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(50.dp) // Set the size of the icon here. Adjust the value as needed.
                                .clickable { navController.popBackStack() }
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.privacy),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .semantics { heading() },
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 4.sp),
                color = Color.White,
                fontSize = 40.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .size(350.dp, 550.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(id = R.string.coming_soon),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = textSize,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Light
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .semantics { heading() }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .background(Color(0xFFD0BCFF))
                    .padding(15.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { textSize = (textSize.value + 1).sp },
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.IconButton(
                            onClick = { textSize = (textSize.value + 1).sp },
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(35.dp)
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(id = R.string.increase_text_size),
                                tint = Color.Black,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { textSize = (textSize.value - 1).sp },
                        contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.material3.IconButton(
                            onClick = { textSize = (textSize.value - 1).sp },
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(35.dp)
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = stringResource(id = R.string.decrease_text_size),
                                tint = Color.Black,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}



