package com.example.lifecycle

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.example.lifecycle.ui.theme.LifecycleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifecycleTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Activity Lifecycle Demo",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    Divider()
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Basic Lifecycle Demo
                    NavigationDrawerItem(
                        icon = { Text("🔄", fontSize = 20.sp) },
                        label = { 
                            Column {
                                Text("Basic Lifecycle", fontWeight = FontWeight.Medium)
                                Text("onCreate, onStart, onResume...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            context.startActivity(Intent(context, BasicLifecycleActivity::class.java))
                        }
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // State Management Demo
                    NavigationDrawerItem(
                        icon = { Text("💾", fontSize = 20.sp) },
                        label = { 
                            Column {
                                Text("State Management", fontWeight = FontWeight.Medium)
                                Text("Configuration changes & state", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            context.startActivity(Intent(context, StateManagementActivity::class.java))
                        }
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Advanced Lifecycle Demo
                    NavigationDrawerItem(
                        icon = { Text("⚡", fontSize = 20.sp) },
                        label = { 
                            Column {
                                Text("Advanced Lifecycle", fontWeight = FontWeight.Medium)
                                Text("Observers & memory management", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            context.startActivity(Intent(context, AdvancedLifecycleActivity::class.java))
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Lifecycle Demo") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "🔄",
                            fontSize = 48.sp
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Text(
                            text = "Android Activity Lifecycle Demo",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Learn about Activity lifecycle methods, state management, and configuration changes through interactive demonstrations.",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Text(
                            text = "👈 Open the drawer menu to explore different lifecycle scenarios",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Quick access buttons
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = {
                            context.startActivity(Intent(context, BasicLifecycleActivity::class.java))
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("🔄 Basic Lifecycle Demo")
                    }
                    
                    Button(
                        onClick = {
                            context.startActivity(Intent(context, StateManagementActivity::class.java))
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("💾 State Management Demo")
                    }
                    
                    Button(
                        onClick = {
                            context.startActivity(Intent(context, AdvancedLifecycleActivity::class.java))
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("⚡ Advanced Lifecycle Demo")
                    }
                }
            }
        }
    }
}