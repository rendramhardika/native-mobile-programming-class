package com.example.mobile_layout.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun ConstraintLayoutScreen(navController: NavController? = null) {
    var selectedColor by remember { mutableStateOf(0) }
    var selectedSize by remember { mutableStateOf("M") }
    var quantity by remember { mutableStateOf(1) }

    val colors = listOf("Black", "Blue", "Red", "Green")
    val sizes = listOf("XS", "S", "M", "L", "XL")
    val productImages = listOf(
        "https://example.com/product1.jpg",
        "https://example.com/product2.jpg",
        "https://example.com/product3.jpg"
    )
    var selectedImage by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = { navController?.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle favorite */ }) {
                        Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Add to favorites")
                    }
                    IconButton(onClick = { /* Handle share */ }) {
                        Icon(Icons.Outlined.Share, contentDescription = "Share")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                tonalElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "$129.99",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            "In Stock",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF4CAF50)
                        )
                    }
                    Button(
                        onClick = { /* Add to cart */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                    ) {
                        Text("Add to Cart")
                    }
                }
            }
        }
    ) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            val (imageSlider, title, price, rating, colorLabel, colorGroup, 
                  sizeLabel, sizeGroup, description, divider, features) = createRefs()
            
            // Image Slider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .constrainAs(imageSlider) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                Text("👕", style = MaterialTheme.typography.displayLarge)
                
                // Image indicators
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    productImages.forEachIndexed { index, _ ->
                        Box(
                            modifier = Modifier
                                .size(if (selectedImage == index) 8.dp else 6.dp)
                                .padding(horizontal = 2.dp)
                                .background(
                                    color = if (selectedImage == index) 
                                        MaterialTheme.colorScheme.primary 
                                    else 
                                        Color.Gray.copy(alpha = 0.5f),
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }

            // Product Title
            Text(
                "Classic Cotton T-Shirt",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(imageSlider.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                }
            )

            // Price
            Text(
                "$129.99",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.constrainAs(price) {
                    top.linkTo(title.bottom, 8.dp)
                    start.linkTo(parent.start, 16.dp)
                }
            )

            // Rating
            Row(
                modifier = Modifier.constrainAs(rating) {
                    top.linkTo(title.bottom, 8.dp)
                    end.linkTo(parent.end, 16.dp)
                    baseline.linkTo(price.baseline)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("4.8 (128 reviews)", style = MaterialTheme.typography.bodySmall)
            }

            // Color Selection
            Text(
                "Color",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.constrainAs(colorLabel) {
                    top.linkTo(price.bottom, 24.dp)
                    start.linkTo(parent.start, 16.dp)
                }
            )

            // Color Options
            LazyRow(
                modifier = Modifier
                    .constrainAs(colorGroup) {
                        top.linkTo(colorLabel.bottom, 8.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(colors) { index, colorName ->
                    val color = when (colorName) {
                        "Black" -> Color.Black
                        "Blue" -> Color.Blue
                        "Red" -> Color.Red
                        "Green" -> Color.Green
                        else -> Color.Gray
                    }
                    
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .border(
                                width = if (selectedColor == index) 2.dp else 0.dp,
                                color = if (selectedColor == index) MaterialTheme.colorScheme.primary 
                                      else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable { selectedColor = index },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(color, CircleShape)
                        )
                        if (selectedColor == index) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            // Size Selection
            Text(
                "Size",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.constrainAs(sizeLabel) {
                    top.linkTo(colorGroup.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                }
            )

            // Size Options
            LazyRow(
                modifier = Modifier
                    .constrainAs(sizeGroup) {
                        top.linkTo(sizeLabel.bottom, 8.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                    }
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(sizes) { index, size ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (selectedSize == size) 
                                    MaterialTheme.colorScheme.primaryContainer 
                                else 
                                    MaterialTheme.colorScheme.surfaceVariant
                            )
                            .border(
                                width = if (selectedSize == size) 1.dp else 0.dp,
                                color = if (selectedSize == size) 
                                    MaterialTheme.colorScheme.primary 
                                else 
                                    Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedSize = size },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            size,
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (selectedSize == size) 
                                MaterialTheme.colorScheme.onPrimaryContainer 
                            else 
                                MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // Divider
            HorizontalDivider(
                modifier = Modifier
                    .constrainAs(divider) {
                        top.linkTo(sizeGroup.bottom, 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                thickness = DividerDefaults.Thickness, color = DividerDefaults.color)

            // Product Description
            Text(
                "Product Description",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(divider.bottom, 8.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                }
            )

            // Features
            Column(
                modifier = Modifier
                    .constrainAs(features) {
                        top.linkTo(description.bottom, 8.dp)
                        start.linkTo(parent.start, 16.dp)
                        end.linkTo(parent.end, 16.dp)
                        bottom.linkTo(parent.bottom, 16.dp)
                    }
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    "• 100% premium cotton\n" +
                    "• Regular fit\n" +
                    "• Crew neck\n" +
                    "• Short sleeves\n" +
                    "• Machine washable\n" +
                    "• Imported",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Quantity Selector
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Quantity",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        IconButton(
                            onClick = { if (quantity > 1) quantity-- },
                            modifier = Modifier
                                .size(36.dp)
                                .background(
                                    MaterialTheme.colorScheme.surfaceVariant,
                                    CircleShape
                                )
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Decrease quantity",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        
                        Text(
                            "$quantity",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.width(24.dp),
                            textAlign = TextAlign.Center
                        )
                        
                        IconButton(
                            onClick = { if (quantity < 10) quantity++ },
                            modifier = Modifier
                                .size(36.dp)
                                .background(
                                    MaterialTheme.colorScheme.surfaceVariant,
                                    CircleShape
                                )
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "Increase quantity",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}
