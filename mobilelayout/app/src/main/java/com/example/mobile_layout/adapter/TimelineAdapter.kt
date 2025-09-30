package com.example.mobile_layout.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_layout.R
import com.example.mobile_layout.model.TimelineItem
import com.google.android.material.button.MaterialButton

/**
 * Adapter for displaying Instagram-like timeline with posts and ads
 */
class TimelineAdapter(
    private val items: List<TimelineItem>,
    private val onPostClick: (TimelineItem.Post) -> Unit,
    private val onAdClick: (TimelineItem.Ad) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TAG = "TimelineAdapter"
        private const val VIEW_TYPE_POST = 0
        private const val VIEW_TYPE_AD = 1
        
        // Ads will appear every 4 posts
        const val AD_INTERVAL = 4
    }

    /**
     * ViewHolder for regular posts
     */
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivUserAvatar: ImageView = itemView.findViewById(R.id.ivUserAvatar)
        val tvUsername: TextView = itemView.findViewById(R.id.tvUsername)
        val ivPostImage: ImageView = itemView.findViewById(R.id.ivPostImage)
        val tvLikesCount: TextView = itemView.findViewById(R.id.tvLikesCount)
        val tvCaption: TextView = itemView.findViewById(R.id.tvCaption)
        val tvViewComments: TextView = itemView.findViewById(R.id.tvViewComments)
        val tvTimePosted: TextView = itemView.findViewById(R.id.tvTimePosted)
        val btnLike: ImageButton = itemView.findViewById(R.id.btnLike)
        
        // Assign a unique ID to each ViewHolder instance for tracking
        val viewHolderId = viewHolderCounter++
        
        init {
            Log.d(TAG, "PostViewHolder #$viewHolderId created")
        }
        
        companion object {
            private var viewHolderCounter = 0
        }
    }

    /**
     * ViewHolder for advertisements
     */
    class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSponsorLogo: ImageView = itemView.findViewById(R.id.ivSponsorLogo)
        val tvSponsorName: TextView = itemView.findViewById(R.id.tvSponsorName)
        val ivAdImage: ImageView = itemView.findViewById(R.id.ivAdImage)
        val tvAdTitle: TextView = itemView.findViewById(R.id.tvAdTitle)
        val tvAdDescription: TextView = itemView.findViewById(R.id.tvAdDescription)
        val btnAdCta: MaterialButton = itemView.findViewById(R.id.btnAdCta)
        
        // Assign a unique ID to each ViewHolder instance for tracking
        val viewHolderId = viewHolderCounter++
        
        init {
            Log.d(TAG, "AdViewHolder #$viewHolderId created")
        }
        
        companion object {
            private var viewHolderCounter = 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        // Every AD_INTERVAL items, show an ad (except at position 0)
        return if (position > 0 && position % AD_INTERVAL == 0) VIEW_TYPE_AD else VIEW_TYPE_POST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder: Creating a new ViewHolder of type ${if (viewType == VIEW_TYPE_POST) "POST" else "AD"}")
        
        return when (viewType) {
            VIEW_TYPE_POST -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_timeline_post, parent, false)
                val holder = PostViewHolder(view)
                Log.d(TAG, "onCreateViewHolder: Created PostViewHolder #${holder.viewHolderId}")
                holder
            }
            VIEW_TYPE_AD -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_timeline_ad, parent, false)
                val holder = AdViewHolder(view)
                Log.d(TAG, "onCreateViewHolder: Created AdViewHolder #${holder.viewHolderId}")
                holder
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Calculate the actual position in the data list
        // For every AD_INTERVAL positions, we need to subtract the number of ads that have been shown
        val adjustedPosition = position - (position / AD_INTERVAL)
        
        // Make sure we don't go out of bounds
        if (adjustedPosition >= items.size) {
            return
        }
        
        when (holder) {
            is PostViewHolder -> {
                Log.d(TAG, "onBindViewHolder: Binding POST at position $position (adjusted: $adjustedPosition) to ViewHolder #${holder.viewHolderId}")
                
                val post = items[adjustedPosition] as? TimelineItem.Post
                    ?: throw IllegalStateException("Expected Post at position $adjustedPosition but found ${items[adjustedPosition]::class.java.simpleName}")
                
                // Bind post data
                holder.tvUsername.text = post.username
                holder.ivUserAvatar.setImageResource(post.userAvatarResId)
                holder.ivPostImage.setImageResource(post.imageResId)
                holder.tvLikesCount.text = "${post.likesCount} likes"
                holder.tvCaption.text = "${post.username} ${post.caption}"
                holder.tvViewComments.text = "View all ${post.commentsCount} comments"
                holder.tvTimePosted.text = post.timePosted
                
                // Set click listeners
                holder.itemView.setOnClickListener {
                    onPostClick(post)
                    Log.d(TAG, "Post clicked: ${post.username}'s post using ViewHolder #${holder.viewHolderId}")
                }
                
                Log.d(TAG, "onBindViewHolder: Completed binding POST at position $position using ViewHolder #${holder.viewHolderId}")
            }
            
            is AdViewHolder -> {
                // For ads, we need to get the ad from a separate list or generate it
                // Here we'll use a simple approach of using the position to determine which ad to show
                val adIndex = position / AD_INTERVAL - 1
                val ad = getAdForPosition(adIndex)
                
                Log.d(TAG, "onBindViewHolder: Binding AD at position $position (ad index: $adIndex) to ViewHolder #${holder.viewHolderId}")
                
                // Bind ad data
                holder.tvSponsorName.text = ad.sponsorName
                holder.ivSponsorLogo.setImageResource(ad.sponsorLogoResId)
                holder.ivAdImage.setImageResource(ad.adImageResId)
                holder.tvAdTitle.text = ad.adTitle
                holder.tvAdDescription.text = ad.adDescription
                holder.btnAdCta.text = ad.ctaText
                
                // Set click listeners
                holder.btnAdCta.setOnClickListener {
                    onAdClick(ad)
                    Log.d(TAG, "Ad CTA clicked: ${ad.sponsorName}'s ad using ViewHolder #${holder.viewHolderId}")
                }
                
                Log.d(TAG, "onBindViewHolder: Completed binding AD at position $position using ViewHolder #${holder.viewHolderId}")
            }
        }
    }

    override fun getItemCount(): Int {
        // Calculate total items including ads
        val postCount = items.size
        val adCount = if (postCount > 0) (postCount - 1) / AD_INTERVAL + 1 else 0
        return postCount + adCount
    }
    
    /**
     * Get an ad for a specific position
     */
    private fun getAdForPosition(adIndex: Int): TimelineItem.Ad {
        // In a real app, you would have a list of ads or fetch them from a server
        // For this example, we'll create a few sample ads and cycle through them
        val sampleAds = listOf(
            TimelineItem.Ad(
                id = 1001,
                sponsorName = "Fashion Brand",
                sponsorLogoResId = R.drawable.ic_launcher_foreground,
                adImageResId = R.drawable.ic_launcher_background,
                adTitle = "Summer Collection Sale",
                adDescription = "Get up to 50% off on our new summer collection. Limited time offer!",
                ctaText = "Shop Now"
            ),
            TimelineItem.Ad(
                id = 1002,
                sponsorName = "Tech Gadgets",
                sponsorLogoResId = R.drawable.ic_launcher_foreground,
                adImageResId = R.drawable.ic_launcher_background,
                adTitle = "New Smartphone Release",
                adDescription = "Experience the future with our latest smartphone. Pre-order today!",
                ctaText = "Learn More"
            ),
            TimelineItem.Ad(
                id = 1003,
                sponsorName = "Travel Agency",
                sponsorLogoResId = R.drawable.ic_launcher_foreground,
                adImageResId = R.drawable.ic_launcher_background,
                adTitle = "Dream Vacation Packages",
                adDescription = "Explore exotic destinations at unbeatable prices. Book now and save!",
                ctaText = "Book Now"
            )
        )
        
        // Use modulo to cycle through the ads
        return sampleAds[adIndex % sampleAds.size]
    }
    
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        when (holder) {
            is PostViewHolder -> {
                Log.d(TAG, "onViewRecycled: PostViewHolder #${holder.viewHolderId} recycled")
            }
            is AdViewHolder -> {
                Log.d(TAG, "onViewRecycled: AdViewHolder #${holder.viewHolderId} recycled")
            }
        }
    }
    
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        when (holder) {
            is PostViewHolder -> {
                Log.d(TAG, "onViewAttachedToWindow: PostViewHolder #${holder.viewHolderId} attached")
            }
            is AdViewHolder -> {
                Log.d(TAG, "onViewAttachedToWindow: AdViewHolder #${holder.viewHolderId} attached")
            }
        }
    }
    
    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        when (holder) {
            is PostViewHolder -> {
                Log.d(TAG, "onViewDetachedFromWindow: PostViewHolder #${holder.viewHolderId} detached")
            }
            is AdViewHolder -> {
                Log.d(TAG, "onViewDetachedFromWindow: AdViewHolder #${holder.viewHolderId} detached")
            }
        }
    }
}
