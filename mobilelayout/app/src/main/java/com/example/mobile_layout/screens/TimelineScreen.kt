package com.example.mobile_layout.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.example.mobile_layout.R
import com.example.mobile_layout.model.ComposeTimelineItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

/**
 * Screen demonstrating an Instagram-like timeline with posts and ads using Jetpack Compose
 */
private const val TAG = "ComposeTimeline"
private const val AD_INTERVAL = 4 // Show an ad after every 4 posts

/**
 * Logs information about how Compose's timeline differs from traditional RecyclerView
 */
private fun logComposeTimelineInfo() {
    Log.d(TAG, "============================================")
    Log.d(TAG, "COMPOSE TIMELINE VS TRADITIONAL RECYCLERVIEW")
    Log.d(TAG, "============================================")
    Log.d(TAG, "1. Multiple view types handled with sealed classes")
    Log.d(TAG, "2. Ad insertion logic with itemsIndexed + when statement")
    Log.d(TAG, "3. No need for complex adapter logic or getItemViewType")
    Log.d(TAG, "4. Smart recomposition only updates changed items")
    Log.d(TAG, "5. No explicit ViewHolder recycling needed")
    Log.d(TAG, "============================================")
    Log.d(TAG, "Watch the logs to see how posts and ads are composed")
    Log.d(TAG, "============================================")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(navController: NavController? = null) {
    // Sample timeline data (only posts, ads will be inserted dynamically)
    val posts = remember {
        listOf(
            ComposeTimelineItem.Post(
                id = 1,
                username = "travel_lover",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Exploring beautiful beaches! #vacation #summer",
                likesCount = 1245,
                commentsCount = 42,
                timePosted = "2 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 2,
                username = "food_enthusiast",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Homemade pasta for dinner tonight 🍝 #foodie #homecooking",
                likesCount = 876,
                commentsCount = 31,
                timePosted = "3 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 3,
                username = "fitness_guru",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Morning workout complete! 💪 #fitness #motivation",
                likesCount = 2103,
                commentsCount = 57,
                timePosted = "5 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 4,
                username = "pet_lover",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "My cat being adorable as always 😻 #catsofinstagram",
                likesCount = 3421,
                commentsCount = 98,
                timePosted = "6 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 5,
                username = "art_creator",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Latest artwork finished today! #art #creative",
                likesCount = 1567,
                commentsCount = 45,
                timePosted = "8 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 6,
                username = "tech_geek",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Unboxing my new gadget! #tech #unboxing",
                likesCount = 982,
                commentsCount = 63,
                timePosted = "10 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 7,
                username = "nature_explorer",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Beautiful sunset at the mountains #nature #hiking",
                likesCount = 2765,
                commentsCount = 72,
                timePosted = "12 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 8,
                username = "fashion_stylist",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Today's outfit of the day #fashion #ootd",
                likesCount = 1832,
                commentsCount = 54,
                timePosted = "14 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 9,
                username = "book_worm",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Current read - highly recommend! #books #reading",
                likesCount = 743,
                commentsCount = 28,
                timePosted = "16 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 10,
                username = "music_lover",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "At the concert tonight! #music #livemusic",
                likesCount = 2134,
                commentsCount = 67,
                timePosted = "18 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 11,
                username = "coffee_addict",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Perfect morning with perfect coffee ☕ #coffee #morning",
                likesCount = 1456,
                commentsCount = 39,
                timePosted = "20 HOURS AGO"
            ),
            ComposeTimelineItem.Post(
                id = 12,
                username = "diy_crafts",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Made this from scratch! #diy #crafts",
                likesCount = 987,
                commentsCount = 42,
                timePosted = "22 HOURS AGO"
            )
        )
    }
    
    // Sample ads
    val ads = remember {
        listOf(
            ComposeTimelineItem.Ad(
                id = 1001,
                sponsorName = "Fashion Brand",
                sponsorLogoResId = R.drawable.ic_launcher_foreground,
                adImageResId = R.drawable.ic_launcher_background,
                adTitle = "Summer Collection Sale",
                adDescription = "Get up to 50% off on our new summer collection. Limited time offer!",
                ctaText = "Shop Now"
            ),
            ComposeTimelineItem.Ad(
                id = 1002,
                sponsorName = "Tech Gadgets",
                sponsorLogoResId = R.drawable.ic_launcher_foreground,
                adImageResId = R.drawable.ic_launcher_background,
                adTitle = "New Smartphone Release",
                adDescription = "Experience the future with our latest smartphone. Pre-order today!",
                ctaText = "Learn More"
            ),
            ComposeTimelineItem.Ad(
                id = 1003,
                sponsorName = "Travel Agency",
                sponsorLogoResId = R.drawable.ic_launcher_foreground,
                adImageResId = R.drawable.ic_launcher_background,
                adTitle = "Dream Vacation Packages",
                adDescription = "Explore exotic destinations at unbeatable prices. Book now and save!",
                ctaText = "Book Now"
            )
        )
    }

    // Log explanation about Compose timeline
    LaunchedEffect(Unit) {
        logComposeTimelineInfo()
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
        Log.d(TAG, "Initial composition of TimelineScreen")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Instagram Timeline") },
                navigationIcon = {
                    IconButton(onClick = { navController?.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
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
                        "INSTAGRAM-STYLE TIMELINE",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "This example shows how to implement an Instagram-like timeline with posts and ads using Jetpack Compose. Ads appear after every $AD_INTERVAL posts.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }

            // Timeline implementation using LazyColumn
            val listState = rememberLazyListState()
            
            // Create a combined list of posts and ads
            val timelineItems = remember {
                buildList {
                    posts.forEachIndexed { index, post ->
                        // Add the post
                        add(post)
                        
                        // After every AD_INTERVAL posts (but not at position 0), add an ad
                        if ((index + 1) % AD_INTERVAL == 0 && index < posts.size - 1) {
                            val adIndex = (index / AD_INTERVAL) % ads.size
                            add(ads[adIndex])
                        }
                    }
                }
            }
            
            // Log visible items when scrolling
            LaunchedEffect(listState) {
                snapshotFlow { 
                    listState.firstVisibleItemIndex to listState.layoutInfo.visibleItemsInfo.size 
                }
                .distinctUntilChanged()
                .collect { (firstIndex, visibleItemCount) ->
                    val lastIndex = firstIndex + visibleItemCount - 1
                    Log.d(TAG, "Visible items: $firstIndex to $lastIndex (total visible: $visibleItemCount)")
                    
                    // Log what type of items are visible based on the combined timeline
                    for (i in firstIndex..lastIndex) {
                        if (i < timelineItems.size) {
                            val item = timelineItems[i]
                            when (item) {
                                is ComposeTimelineItem.Post -> 
                                    Log.d(TAG, "Visible item at position $i: POST - ${item.username}")
                                is ComposeTimelineItem.Ad -> 
                                    Log.d(TAG, "Visible item at position $i: AD - ${item.sponsorName}")
                            }
                        }
                    }
                }
            }
            
            // Log the structure of the timeline
            LaunchedEffect(Unit) {
                Log.d(TAG, "Timeline structure: ${timelineItems.size} total items")
                timelineItems.forEachIndexed { index, item ->
                    when (item) {
                        is ComposeTimelineItem.Post -> Log.d(TAG, "Item $index: POST - ${item.username}")
                        is ComposeTimelineItem.Ad -> Log.d(TAG, "Item $index: AD - ${item.sponsorName}")
                    }
                }
            }
            
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(timelineItems) { index, item ->
                    // Log the current item being composed
                    SideEffect {
                        Log.d(TAG, "Composing item at position $index: ${if (item is ComposeTimelineItem.Ad) "AD" else "POST"}")
                    }
                    
                    // Display the appropriate item type
                    when (item) {
                        is ComposeTimelineItem.Post -> PostItem(post = item)
                        is ComposeTimelineItem.Ad -> AdItem(ad = item)
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(post: ComposeTimelineItem.Post) {
    // Log recomposition of this item
    val recomposeCount = remember { mutableStateOf(0) }
    SideEffect {
        recomposeCount.value++
        if (recomposeCount.value > 1) {
            Log.d(TAG, "PostItem recomposed for ${post.username}'s post (count: ${recomposeCount.value})")
        }
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column {
            // Post header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User avatar
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                
                // Username and location
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = post.username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
                
                // More options
                IconButton(
                    onClick = { /* Show options */ },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options"
                    )
                }
            }
            
            // Post image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text("📷", fontSize = 40.sp)
            }
            
            // Post actions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                IconButton(onClick = { /* Like */ }) {
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Like")
                }
                
                IconButton(onClick = { /* Comment */ }) {
                    Icon(Icons.Default.Add, contentDescription = "Comment")
                }
                
                IconButton(onClick = { /* Share */ }) {
                    Icon(Icons.Outlined.Send, contentDescription = "Share")
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                IconButton(onClick = { /* Save */ }) {
                    Icon(Icons.Default.Email, contentDescription = "Save")
                }
            }
            
            // Likes count
            Text(
                text = "${post.likesCount} likes",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            // Caption
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text(
                    text = post.username,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = post.caption,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            // View all comments
            Text(
                text = "View all ${post.commentsCount} comments",
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
            
            // Time posted
            Text(
                text = post.timePosted,
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun AdItem(ad: ComposeTimelineItem.Ad) {
    // Log recomposition of this item
    val recomposeCount = remember { mutableStateOf(0) }
    SideEffect {
        recomposeCount.value++
        if (recomposeCount.value > 1) {
            Log.d(TAG, "AdItem recomposed for ${ad.sponsorName}'s ad (count: ${recomposeCount.value})")
        }
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column {
            // Ad header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Sponsor logo
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                
                // Sponsor name and sponsored label
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = ad.sponsorName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Sponsored",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                
                // More options
                IconButton(
                    onClick = { /* Show options */ },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options"
                    )
                }
            }
            
            // Ad image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color(0xFFF0F0F0)),
                contentAlignment = Alignment.Center
            ) {
                Text("📱", fontSize = 40.sp)
            }
            
            // Ad content
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = ad.adTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = ad.adDescription,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Button(
                    onClick = { /* CTA action */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Text(ad.ctaText)
                }
            }
        }
    }
}
