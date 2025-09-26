package com.example.lifecycle

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.*
import com.example.lifecycle.ui.theme.LifecycleTheme

class AdvancedLifecycleActivity : ComponentActivity(), LifecycleEventObserver {
    private val lifecycleEvents = mutableStateListOf<String>()
    private val TAG = "AdvancedLifecycle"
    private var isInForeground = mutableStateOf(false)
    private var configurationInfo = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logLifecycleEvent("onCreate", "Activity instance created")
        
        lifecycle.addObserver(this)
        updateConfigurationInfo()
        
        enableEdgeToEdge()
        setContent {
            LifecycleTheme {
                AdvancedLifecycleScreen(
                    lifecycleEvents = lifecycleEvents,
                    isInForeground = isInForeground.value,
                    configurationInfo = configurationInfo.value,
                    onClearLogs = { clearLogs() }
                )
            }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        val eventName = when (event) {
            Lifecycle.Event.ON_CREATE -> "ON_CREATE (Observer)"
            Lifecycle.Event.ON_START -> "ON_START (Observer)"
            Lifecycle.Event.ON_RESUME -> "ON_RESUME (Observer)"
            Lifecycle.Event.ON_PAUSE -> "ON_PAUSE (Observer)"
            Lifecycle.Event.ON_STOP -> "ON_STOP (Observer)"
            Lifecycle.Event.ON_DESTROY -> "ON_DESTROY (Observer)"
            else -> event.name
        }
        logLifecycleEvent(eventName, "Lifecycle observer callback")
    }

    override fun onStart() {
        super.onStart()
        logLifecycleEvent("onStart", "Activity becoming visible")
        isInForeground.value = true
    }

    override fun onResume() {
        super.onResume()
        logLifecycleEvent("onResume", "Activity ready for interaction")
    }

    override fun onPause() {
        super.onPause()
        logLifecycleEvent("onPause", "Activity losing focus")
    }

    override fun onStop() {
        super.onStop()
        logLifecycleEvent("onStop", "Activity no longer visible")
        isInForeground.value = false
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycleEvent("onRestart", "Activity restarting after stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logLifecycleEvent("onDestroy", "Activity being destroyed")
        lifecycle.removeObserver(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        logLifecycleEvent("onConfigurationChanged", "Device configuration changed")
        updateConfigurationInfo()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logLifecycleEvent("onSaveInstanceState", "Saving state")
        outState.putStringArrayList("lifecycle_events", ArrayList(lifecycleEvents))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        logLifecycleEvent("onRestoreInstanceState", "Restoring state")
        savedInstanceState.getStringArrayList("lifecycle_events")?.let { savedEvents ->
            lifecycleEvents.clear()
            lifecycleEvents.addAll(savedEvents)
        }
    }

    private fun updateConfigurationInfo() {
        val config = resources.configuration
        val orientation = if (config.orientation == Configuration.ORIENTATION_PORTRAIT) "Portrait" else "Landscape"
        configurationInfo.value = "Orientation: $orientation"
    }

    private fun logLifecycleEvent(event: String, description: String) {
        val timestamp = System.currentTimeMillis()
        val timeString = java.text.SimpleDateFormat("HH:mm:ss.SSS", java.util.Locale.getDefault()).format(timestamp)
        val logMessage = "$event - $description ($timeString)"
        Log.d(TAG, logMessage)
        lifecycleEvents.add(logMessage)
    }

    private fun clearLogs() {
        lifecycleEvents.clear()
        logLifecycleEvent("Logs Cleared", "User manually cleared the log history")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedLifecycleScreen(
    lifecycleEvents: List<String>,
    isInForeground: Boolean,
    configurationInfo: String,
    onClearLogs: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Advanced Lifecycle Demo") },
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
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isInForeground) 
                            MaterialTheme.colorScheme.primaryContainer 
                        else 
                            MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (isInForeground) "🟢 FOREGROUND" else "🔴 BACKGROUND",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                Card(
                    modifier = Modifier.weight(2f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = configurationInfo,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Advanced Lifecycle Features",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Demonstrates advanced lifecycle concepts including lifecycle observers, configuration changes, and state management. Try rotating the device or switching apps!",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Lifecycle Events Log:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                
                OutlinedButton(
                    onClick = onClearLogs,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("🗑️ Clear Log")
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(lifecycleEvents.reversed()) { event ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Text(
                            text = event,
                            modifier = Modifier.padding(12.dp),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
