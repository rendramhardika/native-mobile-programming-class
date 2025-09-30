package com.example.mobile_layout.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.example.mobile_layout.R
import com.example.mobile_layout.model.ComposeProduct
import java.text.NumberFormat
import java.util.Locale

/**
 * Screen demonstrating RecyclerView implementation using Jetpack Compose
 */
private const val TAG = "ComposeRecyclerView"

/**
 * Logs information about how Compose's recycling differs from traditional RecyclerView
 */
private fun logComposeRecyclingInfo() {
    Log.d(TAG, "============================================")
    Log.d(TAG, "COMPOSE RECYCLING VS TRADITIONAL RECYCLERVIEW")
    Log.d(TAG, "============================================")
    Log.d(TAG, "1. Compose doesn't use ViewHolders - it uses Composition")
    Log.d(TAG, "2. No explicit recycling - Compose handles this automatically")
    Log.d(TAG, "3. LazyColumn only composes visible items + buffer")
    Log.d(TAG, "4. Items are recomposed when data or state changes")
    Log.d(TAG, "5. Skipping unchanged items via smart recomposition")
    Log.d(TAG, "============================================")
    Log.d(TAG, "Watch the logs to see how items are composed and recomposed")
    Log.d(TAG, "============================================")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecyclerViewScreen(navController: NavController? = null) {
    // Sample product data
    val products = remember {
        listOf(
            ComposeProduct(
                id = 1,
                title = "Smartphone XYZ",
                description = "Latest smartphone with 6.5-inch display, 128GB storage, and triple camera setup.",
                price = 899.99,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            ComposeProduct(
                id = 2,
                title = "Laptop Pro",
                description = "Powerful laptop with Intel i7 processor, 16GB RAM, and 512GB SSD storage.",
                price = 1299.99,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            ComposeProduct(
                id = 3,
                title = "Wireless Headphones",
                description = "Premium noise-cancelling headphones with 30-hour battery life.",
                price = 249.99,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            ComposeProduct(
                id = 4,
                title = "Smart Watch",
                description = "Fitness tracker with heart rate monitor, GPS, and water resistance.",
                price = 199.99,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            ComposeProduct(
                id = 5,
                title = "Bluetooth Speaker",
                description = "Portable speaker with 360° sound and 12-hour playtime.",
                price = 79.99,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            ComposeProduct(
                id = 6,
                title = "Tablet Ultra",
                description = "10-inch tablet with high-resolution display and long battery life.",
                price = 349.99,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            ComposeProduct(
                id = 7,
                title = "Wireless Earbuds",
                description = "True wireless earbuds with touch controls and charging case.",
                price = 129.99,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            ComposeProduct(
                id = 8,
                title = "External Hard Drive",
                description = "2TB portable hard drive with USB 3.0 for fast data transfer.",
                price = 89.99,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            ComposeProduct(
                id = 9,
                title = "Gaming Console",
                description = "Next-gen gaming console with 4K graphics and 1TB storage.",
                price = 499.99,
                imageResId = R.drawable.ic_launcher_foreground
            ),
            ComposeProduct(
                id = 10,
                title = "Wireless Mouse",
                description = "Ergonomic wireless mouse with adjustable DPI and long battery life.",
                price = 39.99,
                imageResId = R.drawable.ic_launcher_foreground
            )
        )
    }

    // Selected product state
    var selectedProductId by remember { mutableStateOf<Int?>(null) }
    
    // Log explanation about Compose recycling vs traditional RecyclerView
    LaunchedEffect(Unit) {
        logComposeRecyclingInfo()
    }
    
    // Log lifecycle events
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> Log.d(TAG, "Lifecycle: ON_CREATE")
                Lifecycle.Event.ON_START -> Log.d(TAG, "Lifecycle: ON_START")
                Lifecycle.Event.ON_RESUME -> Log.d(TAG, "Lifecycle: ON_RESUME")
                Lifecycle.Event.ON_PAUSE -> Log.d(TAG, "Lifecycle: ON_PAUSE")
                Lifecycle.Event.ON_STOP -> Log.d(TAG, "Lifecycle: ON_STOP")
                Lifecycle.Event.ON_DESTROY -> Log.d(TAG, "Lifecycle: ON_DESTROY")
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    
    // Log initial composition
    SideEffect {
        Log.d(TAG, "Initial composition of RecyclerViewScreen")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RecyclerView with Compose") },
                navigationIcon = {
                    IconButton(onClick = { navController?.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
        ) {
            // Header explaining the example
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "RECYCLERVIEW EXAMPLE",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "This is an implementation of RecyclerView pattern using Jetpack Compose's LazyColumn. Tap on any item to select it.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            // RecyclerView implementation using LazyColumn
            val listState = rememberLazyListState()
            
            // Log visible items when scrolling
            LaunchedEffect(listState) {
                snapshotFlow { listState.firstVisibleItemIndex to listState.layoutInfo.visibleItemsInfo.size }
                    .collect { (firstIndex, visibleItemCount) ->
                        Log.d(TAG, "Visible items: $firstIndex to ${firstIndex + visibleItemCount - 1} (total visible: $visibleItemCount)")
                    }
            }
            
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(products) { product ->
                    // Log when item is composed
                    val itemKey = "item-${product.id}"
                    SideEffect {
                        Log.d(TAG, "Composing $itemKey: ${product.title}")
                    }
                    
                    ProductItem(
                        product = product,
                        isSelected = product.id == selectedProductId,
                        onProductClick = { 
                            selectedProductId = product.id 
                            Log.d(TAG, "Item clicked: ${product.title} (id: ${product.id})")
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    product: ComposeProduct,
    isSelected: Boolean,
    onProductClick: () -> Unit
) {
    // Log recomposition of this item
    val recomposeCount = remember { mutableStateOf(0) }
    SideEffect {
        recomposeCount.value++
        if (recomposeCount.value > 1) {
            Log.d(TAG, "ProductItem recomposed for ${product.title} (count: ${recomposeCount.value})")
        }
    }
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surface
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Image
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Placeholder icon
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(40.dp)
                )
            }

            // Product Details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = if (isSelected) {
                        MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Price with formatting
                val formattedPrice = remember(product.price) {
                    NumberFormat.getCurrencyInstance(Locale.US).format(product.price)
                }
                
                Text(
                    text = formattedPrice,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
