package com.example.mobile_layout.screennative

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_layout.R
import com.example.mobile_layout.adapter.TimelineAdapter
import com.example.mobile_layout.model.TimelineItem

/**
 * Activity demonstrating an Instagram-like timeline with posts and ads
 */
class TimelineActivity : BaseActivity() {
    
    companion object {
        private const val TAG = "TimelineActivity"
    }
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var timelineAdapter: TimelineAdapter
    private val timelineItems = ArrayList<TimelineItem>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)
        
        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setupToolbar(toolbar, getString(R.string.timeline_title))
        
        // Initialize RecyclerView
        Log.d(TAG, "onCreate: Initializing RecyclerView")
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        // Add scroll listener to monitor recycling
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> 
                        Log.d(TAG, "onScrollStateChanged: Scroll IDLE")
                    RecyclerView.SCROLL_STATE_DRAGGING -> 
                        Log.d(TAG, "onScrollStateChanged: Scroll DRAGGING")
                    RecyclerView.SCROLL_STATE_SETTLING -> 
                        Log.d(TAG, "onScrollStateChanged: Scroll SETTLING")
                }
            }
            
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisible = layoutManager.findFirstVisibleItemPosition()
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                
                // Log which items are visible and their types
                for (i in firstVisible..lastVisible) {
                    val viewType = timelineAdapter.getItemViewType(i)
                    val typeStr = if (viewType == 0) "POST" else "AD"
                    Log.d(TAG, "onScrolled: Item at position $i is $typeStr")
                }
            }
        })
        
        // Prepare data
        Log.d(TAG, "onCreate: Preparing timeline data")
        prepareTimelineData()
        Log.d(TAG, "onCreate: Prepared ${timelineItems.size} timeline items")
        
        // Set up adapter
        Log.d(TAG, "onCreate: Creating TimelineAdapter")
        timelineAdapter = TimelineAdapter(
            timelineItems,
            onPostClick = { post ->
                Toast.makeText(this, "Post by ${post.username} clicked", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Post clicked: ${post.username}'s post")
            },
            onAdClick = { ad ->
                Toast.makeText(this, "Ad by ${ad.sponsorName} clicked", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Ad clicked: ${ad.sponsorName}'s ad")
            }
        )
        
        recyclerView.adapter = timelineAdapter
        Log.d(TAG, "onCreate: Set adapter to RecyclerView")
        
        // Set up back button click listener
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        
        // Log the expected structure of the timeline with ads
        logTimelineStructure()
    }
    
    private fun prepareTimelineData() {
        // Add sample posts
        timelineItems.add(
            TimelineItem.Post(
                id = 1,
                username = "travel_lover",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Exploring beautiful beaches! #vacation #summer",
                likesCount = 1245,
                commentsCount = 42,
                timePosted = "2 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
                id = 2,
                username = "food_enthusiast",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Homemade pasta for dinner tonight 🍝 #foodie #homecooking",
                likesCount = 876,
                commentsCount = 31,
                timePosted = "3 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
                id = 3,
                username = "fitness_guru",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Morning workout complete! 💪 #fitness #motivation",
                likesCount = 2103,
                commentsCount = 57,
                timePosted = "5 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
                id = 4,
                username = "pet_lover",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "My cat being adorable as always 😻 #catsofinstagram",
                likesCount = 3421,
                commentsCount = 98,
                timePosted = "6 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
                id = 5,
                username = "art_creator",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Latest artwork finished today! #art #creative",
                likesCount = 1567,
                commentsCount = 45,
                timePosted = "8 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
                id = 6,
                username = "tech_geek",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Unboxing my new gadget! #tech #unboxing",
                likesCount = 982,
                commentsCount = 63,
                timePosted = "10 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
                id = 7,
                username = "nature_explorer",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Beautiful sunset at the mountains #nature #hiking",
                likesCount = 2765,
                commentsCount = 72,
                timePosted = "12 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
                id = 8,
                username = "fashion_stylist",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Today's outfit of the day #fashion #ootd",
                likesCount = 1832,
                commentsCount = 54,
                timePosted = "14 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
                id = 9,
                username = "book_worm",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Current read - highly recommend! #books #reading",
                likesCount = 743,
                commentsCount = 28,
                timePosted = "16 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
                id = 10,
                username = "music_lover",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "At the concert tonight! #music #livemusic",
                likesCount = 2134,
                commentsCount = 67,
                timePosted = "18 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
                id = 11,
                username = "coffee_addict",
                userAvatarResId = R.drawable.ic_launcher_foreground,
                imageResId = R.drawable.ic_launcher_background,
                caption = "Perfect morning with perfect coffee ☕ #coffee #morning",
                likesCount = 1456,
                commentsCount = 39,
                timePosted = "20 HOURS AGO"
            )
        )
        
        timelineItems.add(
            TimelineItem.Post(
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
    
    private fun logTimelineStructure() {
        val adInterval = TimelineAdapter.AD_INTERVAL
        val postCount = timelineItems.size
        val adCount = if (postCount > 0) (postCount - 1) / adInterval + 1 else 0
        val totalItems = postCount + adCount
        
        Log.d(TAG, "Timeline structure: $postCount posts + $adCount ads = $totalItems total items")
        Log.d(TAG, "Ad interval: Every $adInterval posts")
        
        // Log the expected positions of posts and ads
        val structure = StringBuilder("Timeline structure: ")
        var postIndex = 0
        
        for (i in 0 until totalItems) {
            if (i > 0 && i % adInterval == 0) {
                structure.append("AD, ")
            } else {
                structure.append("POST${postIndex + 1}, ")
                postIndex++
            }
            
            // Add line breaks for readability in logs
            if (i % 5 == 4) {
                Log.d(TAG, structure.toString())
                structure.clear()
                structure.append("                   ")
            }
        }
        
        if (structure.isNotEmpty()) {
            Log.d(TAG, structure.toString())
        }
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity resumed")
    }
    
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity paused")
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity destroyed")
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
