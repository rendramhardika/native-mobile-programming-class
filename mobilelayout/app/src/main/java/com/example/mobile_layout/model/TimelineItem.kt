package com.example.mobile_layout.model

/**
 * Base class for timeline items
 */
sealed class TimelineItem {
    /**
     * Represents a regular post in the timeline
     */
    data class Post(
        val id: Int,
        val username: String,
        val userAvatarResId: Int,
        val imageResId: Int,
        val caption: String,
        val likesCount: Int,
        val commentsCount: Int,
        val timePosted: String
    ) : TimelineItem()

    /**
     * Represents an advertisement in the timeline
     */
    data class Ad(
        val id: Int,
        val sponsorName: String,
        val sponsorLogoResId: Int,
        val adImageResId: Int,
        val adTitle: String,
        val adDescription: String,
        val ctaText: String
    ) : TimelineItem()
}
