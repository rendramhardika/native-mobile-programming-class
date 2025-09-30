package com.example.mobile_layout.model

/**
 * Data class representing a product for the RecyclerView example
 */
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val imageResId: Int
)
