package com.example.uiux_mobile.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.uiux_mobile.ui.theme.UiuxmobileTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NavigationPatternsScreen(
    onBackClick: () -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentPattern by remember { mutableIntStateOf(0) }
    val patterns = listOf("Bottom Nav", "Hamburger Menu", "Tabs", "Gestures")
    
    // For tab navigation
    val pagerState = rememberPagerState(pageCount = { 3 })
    val tabs = listOf("Home", "Messages", "Profile")
    
    // For gesture demo
    var showRefresh by remember { mutableStateOf(false) }
    var swipeDirection by remember { mutableStateOf("Swipe left/right") }
    
    // Auto-hide refresh indicator after 1 second
    LaunchedEffect(showRefresh) {
        if (showRefresh) {
            delay(1000)
            showRefresh = false
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Hamburger menu drawer content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    "Menu",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                
                listOf("Home", "Profile", "Settings", "Help").forEach { item ->
                    Text(
                        item,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    )
                    Divider()
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Navigation Patterns") },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        if (currentPattern == 1) { // Show menu icon only for hamburger menu pattern
                            IconButton(onClick = { 
                                scope.launch { drawerState.open() } 
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    }
                )
            },
            bottomBar = {
                if (currentPattern == 0) { // Bottom navigation
                    NavigationBar {
                        listOf(
                            Triple(Icons.Default.Home, "Home", 0),
                            Triple(Icons.Default.Search, "Search", 1),
                            Triple(Icons.Default.Favorite, "Favorites", 2),
                            Triple(Icons.Default.AccountCircle, "Profile", 3)
                        ).forEach { (icon, label, index) ->
                            NavigationBarItem(
                                icon = { Icon(icon, contentDescription = label) },
                                label = { Text(label) },
                                selected = currentPattern == index,
                                onClick = { currentPattern = index }
                            )
                        }
                    }
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // Pattern selector
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    patterns.forEachIndexed { index, pattern ->
                        val isSelected = currentPattern == index
                        Surface(
                            onClick = { currentPattern = index },
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 4.dp)
                        ) {
                            Text(
                                text = pattern,
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary 
                                       else MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Pattern content
                when (currentPattern) {
                    0 -> BottomNavDemo()
                    1 -> HamburgerMenuDemo()
                    2 -> TabNavigationDemo()
                    3 -> GestureNavigationDemo(
                        onSwipe = { direction ->
                            swipeDirection = "Swiped $direction"
                        },
                        onRefresh = { showRefresh = true },
                        showRefresh = showRefresh,
                        swipeHint = swipeDirection
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Bottom Navigation",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Selected content based on bottom nav item")
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Home, "Home", tint = MaterialTheme.colorScheme.primary)
                    Icon(Icons.Default.Search, "Search", tint = Color.Gray)
                    Icon(Icons.Default.Favorite, "Favorites", tint = Color.Gray)
                    Icon(Icons.Default.AccountCircle, "Profile", tint = Color.Gray)
                }
            }
        }
    }
}

@Composable
private fun HamburgerMenuDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Hamburger Menu",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Swipe from left or tap menu icon")
                }
                
                Text("Main content area", modifier = Modifier.padding(vertical = 16.dp))
                
                // Simulate menu items that would be in the drawer
                listOf("Home", "Profile", "Settings", "Help").forEach { item ->
                    Text(
                        item,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Divider()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TabNavigationDemo() {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val tabs = listOf("Home", "Messages", "Profile")
    
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = { /* handled by pager */ }
                )
            }
        }
        
        HorizontalPager(state = pagerState) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                when (page) {
                    0 -> Text("Home Content", style = MaterialTheme.typography.headlineMedium)
                    1 -> Text("Messages Content", style = MaterialTheme.typography.headlineMedium)
                    2 -> Text("Profile Content", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }
    }
}

@Composable
private fun GestureNavigationDemo(
    onSwipe: (String) -> Unit,
    onRefresh: () -> Unit,
    showRefresh: Boolean,
    swipeHint: String
) {
    var offsetX by remember { mutableStateOf(0f) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragStart = { /* Do nothing */ },
                    onDragEnd = {
                        offsetX = 0f
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount
                        
                        // Detect swipe direction based on drag amount
                        if (Math.abs(dragAmount) > 10) { // Threshold to avoid too sensitive detection
                            val direction = if (dragAmount > 0) "right" else "left"
                            onSwipe(direction)
                        }
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Gesture Navigation",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Pull to refresh indicator
            AnimatedVisibility(
                visible = showRefresh,
                enter = fadeIn() + slideInHorizontally(initialOffsetX = { -it }),
                exit = fadeOut() + slideOutHorizontally(targetOffsetX = { -it })
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Refreshing...")
                }
            }
            
            // Swipeable content
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .offset(x = offsetX.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Swipe left or right")
                    Text(swipeHint, style = MaterialTheme.typography.bodySmall)
                }
            }
            
            // Pull to refresh hint
            Text(
                "Pull down to refresh",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 32.dp)
            )
            
            // Swipe gesture area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp)
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { change, dragAmount ->
                            change.consume()
                            val direction = if (dragAmount > 0) "right" else "left"
                            onSwipe("$direction in the box")
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("Swipe left or right in this area")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationPatternsScreenPreview() {
    UiuxmobileTheme {
        NavigationPatternsScreen()
    }
}
