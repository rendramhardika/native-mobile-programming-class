package com.example.mobile_layout.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinearLayoutScreen(navController: NavController? = null) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Linear Layout") },
                navigationIcon = {
                    IconButton(onClick = { navController?.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Vertical Linear Layout
            Text("Vertical Linear Layout", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.2f))
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color.Red.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Item 1")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color.Green.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Item 2")
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color.Blue.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Item 3")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Horizontal Linear Layout
            Text("Horizontal Linear Layout", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.2f))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .background(Color.Red.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Left")
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .background(Color.Green.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Center")
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .background(Color.Blue.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Right")
                }
            }
        }
    }
}
