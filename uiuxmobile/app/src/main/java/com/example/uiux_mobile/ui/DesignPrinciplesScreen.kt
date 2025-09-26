package com.example.uiux_mobile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.uiux_mobile.ui.theme.UiuxmobileTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignPrinciplesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Prinsip Desain Visual") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Color Section
            DesignSection(
                title = "Warna",
                description = "Penggunaan warna yang konsisten dan bermakna dalam desain",
                icon = Icons.Outlined.Favorite
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ColorBox("Primary", MaterialTheme.colorScheme.primary)
                    ColorBox("Secondary", MaterialTheme.colorScheme.secondary)
                    ColorBox("Tertiary", MaterialTheme.colorScheme.tertiary)
                }
            }

            // Typography Section
            DesignSection(
                title = "Tipografi",
                description = "Hierarki teks yang jelas dan mudah dibaca",
                icon = Icons.Outlined.Edit
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Heading Large",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "Body Medium",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Label Small",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            // Iconography Section
            DesignSection(
                title = "Ikonografi",
                description = "Ikon yang konsisten dan mudah dikenali",
                icon = Icons.Outlined.Star
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconItem(Icons.Default.Favorite, "Like")
                    IconItem(Icons.Default.Share, "Share")
                    IconItem(Icons.Outlined.Check, "Save")
                    IconItem(Icons.Default.Settings, "Settings")
                }
            }
        }
    }
}

@Composable
private fun DesignSection(
    title: String,
    description: String,
    icon: ImageVector,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            }
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
            
            content()
        }
    }
}

@Composable
private fun ColorBox(name: String, color: androidx.compose.ui.graphics.Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(color)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun IconItem(icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DesignPrinciplesScreenPreview() {
    UiuxmobileTheme {
        DesignPrinciplesScreen()
    }
}
