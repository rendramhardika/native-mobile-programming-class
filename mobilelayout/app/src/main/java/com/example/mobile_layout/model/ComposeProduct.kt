package com.example.mobile_layout.model

/**
 * Data class representing a product for the Compose RecyclerView example
 */
data class ComposeProduct(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val imageResId: Int
)
