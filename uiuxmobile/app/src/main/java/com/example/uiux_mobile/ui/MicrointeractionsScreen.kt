package com.example.uiux_mobile.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uiux_mobile.ui.theme.UiuxmobileTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MicrointeractionsScreen(
    onBackClick: () -> Unit = {}
) {
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Microinteractions") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Loading and Transition Example
            item {
                MicrointeractionCard(
                    title = "1. Loading & Transitions",
                    description = "Smooth loading indicators and transitions"
                ) {
                    var showContent by remember { mutableStateOf(false) }
                    
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (showContent) {
                            // Content with fade-in animation
                            AnimatedVisibility(
                                visible = showContent,
                                enter = fadeIn() + slideInVertically(),
                                exit = fadeOut() + slideOutVertically()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .background(
                                            MaterialTheme.colorScheme.primaryContainer,
                                            RoundedCornerShape(8.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("Loaded!")
                                }
                            }
                        } else {
                            // Loading indicator with rotation
                            val infiniteTransition = rememberInfiniteTransition()
                            val angle by infiniteTransition.animateFloat(
                                initialValue = 0f,
                                targetValue = 360f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(1000, easing = LinearEasing),
                                    repeatMode = RepeatMode.Restart
                                )
                            )
                            
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = "Loading",
                                modifier = Modifier
                                    .size(48.dp)
                                    .rotate(angle),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Button(onClick = { showContent = !showContent }) {
                            Text(if (showContent) "Reset" else "Load Content")
                        }
                    }
                }
            }
            
            // Visual/Audio Feedback Example
            item {
                MicrointeractionCard(
                    title = "2. Visual & Haptic Feedback",
                    description = "Responsive UI elements with feedback"
                ) {
                    var isLiked by remember { mutableStateOf(false) }
                    var isBookmarked by remember { mutableStateOf(false) }
                    
                    val likeColor by animateColorAsState(
                        targetValue = if (isLiked) MaterialTheme.colorScheme.error else LocalContentColor.current,
                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                    )
                    
                    val bookmarkColor by animateColorAsState(
                        targetValue = if (isBookmarked) MaterialTheme.colorScheme.primary else LocalContentColor.current,
                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Like button with scale animation
                        var likeScale by remember { mutableStateOf(1f) }
                        
                        IconButton(
                            onClick = {
                                isLiked = !isLiked
                                likeScale = if (isLiked) 1.5f else 1f
                            },
                            modifier = Modifier
                                .scale(likeScale)
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            likeScale = 0.8f
                                            tryAwaitRelease()
                                            likeScale = 1f
                                        }
                                    )
                                }
                        ) {
                            Icon(
                                if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Like",
                                tint = likeColor,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        
                        // Bookmark button with rotation animation
                        var bookmarkRotation by remember { mutableStateOf(0f) }
                        
                        IconButton(
                            onClick = {
                                isBookmarked = !isBookmarked
                                scope.launch {
                                    bookmarkRotation = 15f
                                    delay(100)
                                    bookmarkRotation = -15f
                                    delay(100)
                                    bookmarkRotation = 10f
                                    delay(100)
                                    bookmarkRotation = -5f
                                    delay(100)
                                    bookmarkRotation = 0f
                                }
                            }
                        ) {
                            Icon(
                                if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Bookmark",
                                tint = bookmarkColor,
                                modifier = Modifier
                                    .size(32.dp)
                                    .rotate(bookmarkRotation)
                            )
                        }
                    }
                    
                    // Ripple effect example
                    var showRipple by remember { mutableStateOf(false) }
                    val rippleAlpha by animateFloatAsState(
                        targetValue = if (showRipple) 0.3f else 0f,
                        animationSpec = tween(300)
                    )
                    
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = {
                                        showRipple = true
                                        tryAwaitRelease()
                                        showRipple = false
                                    }
                                )
                            }
                    ) {
                        if (showRipple) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        MaterialTheme.colorScheme.primary.copy(alpha = rippleAlpha)
                                    )
                            )
                        }
                        Text("Tap for ripple effect")
                    }
                }
            }
            
            // Guidance Interaction Example
            item {
                MicrointeractionCard(
                    title = "3. Guidance Interactions",
                    description = "Pull to refresh and interactive elements"
                ) {
                    // Pull to refresh example
                    var isRefreshing by remember { mutableStateOf(false) }
                    var items by remember { mutableStateOf((1..5).map { "Item $it" }) }
                    
                    if (isRefreshing) {
                        LaunchedEffect(Unit) {
                            delay(1500) // Simulate network request
                            items = (1..5).shuffled().map { "Item $it" }
                            isRefreshing = false
                        }
                    }
                    
                    Column {
                        // Pull to refresh indicator
                        if (isRefreshing) {
                            val infiniteTransition = rememberInfiniteTransition()
                            val angle by infiniteTransition.animateFloat(
                                initialValue = 0f,
                                targetValue = 360f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(1000, easing = LinearEasing),
                                    repeatMode = RepeatMode.Restart
                                )
                            )
                            
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Refresh,
                                    contentDescription = "Refreshing",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .rotate(angle),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Refreshing...")
                            }
                        }
                        
                        // Refresh button
                        Button(
                            onClick = { isRefreshing = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Pull Down to Refresh")
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // List of items
                        items.forEach { item ->
                            var isChecked by remember { mutableStateOf(false) }
                            val scale by animateFloatAsState(
                                targetValue = if (isChecked) 0.95f else 1f,
                                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                            )
                            
                            Surface(
                                onClick = { isChecked = !isChecked },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .scale(scale),
                                shape = RoundedCornerShape(8.dp),
                                color = if (isChecked) MaterialTheme.colorScheme.primaryContainer 
                                      else MaterialTheme.colorScheme.surfaceVariant,
                                shadowElevation = if (isChecked) 4.dp else 1.dp
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = isChecked,
                                        onCheckedChange = { isChecked = it }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        item,
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = if (isChecked) MaterialTheme.colorScheme.onPrimaryContainer 
                                               else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MicrointeractionCard(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Content divider
            Divider(modifier = Modifier.padding(vertical = 4.dp))
            
            // Custom content
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MicrointeractionsScreenPreview() {
    UiuxmobileTheme {
        MicrointeractionsScreen()
    }
}
