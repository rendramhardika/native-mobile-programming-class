package com.example.mobile_layout.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_layout.R
import com.example.mobile_layout.model.Product
import java.text.NumberFormat
import java.util.Locale

/**
 * Adapter for displaying products in a RecyclerView
 */
class ProductAdapter(
    private val products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    
    companion object {
        private const val TAG = "ProductAdapter"
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItemImage: ImageView = itemView.findViewById(R.id.ivItemImage)
        val tvItemTitle: TextView = itemView.findViewById(R.id.tvItemTitle)
        val tvItemDescription: TextView = itemView.findViewById(R.id.tvItemDescription)
        val tvItemPrice: TextView = itemView.findViewById(R.id.tvItemPrice)
        
        // Assign a unique ID to each ViewHolder instance for tracking
        val viewHolderId = viewHolderCounter++
        
        init {
            Log.d(TAG, "ViewHolder #$viewHolderId created")
        }
        
        companion object {
            private var viewHolderCounter = 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        Log.d(TAG, "onCreateViewHolder: Creating a new ViewHolder")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view, parent, false)
        val holder = ProductViewHolder(view)
        Log.d(TAG, "onCreateViewHolder: Created ViewHolder #${holder.viewHolderId}")
        return holder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: Binding data at position $position to ViewHolder #${holder.viewHolderId} (RECYCLED)")
        val product = products[position]
        
        holder.ivItemImage.setImageResource(product.imageResId)
        holder.tvItemTitle.text = product.title
        holder.tvItemDescription.text = product.description
        
        // Format price as currency
        val format = NumberFormat.getCurrencyInstance(Locale.US)
        holder.tvItemPrice.text = format.format(product.price)
        
        // Set click listener
        holder.itemView.setOnClickListener {
            onItemClick(product)
            Log.d(TAG, "Item clicked: ${product.title} using ViewHolder #${holder.viewHolderId}")
        }
        
        Log.d(TAG, "onBindViewHolder: Completed binding for position $position using ViewHolder #${holder.viewHolderId}")
    }

    override fun getItemCount(): Int = products.size
    
    override fun onViewRecycled(holder: ProductViewHolder) {
        super.onViewRecycled(holder)
        Log.d(TAG, "onViewRecycled: ViewHolder #${holder.viewHolderId} recycled")
    }
    
    override fun onViewAttachedToWindow(holder: ProductViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.d(TAG, "onViewAttachedToWindow: ViewHolder #${holder.viewHolderId} attached to window")
    }
    
    override fun onViewDetachedFromWindow(holder: ProductViewHolder) {
        super.onViewDetachedFromWindow(holder)
        Log.d(TAG, "onViewDetachedFromWindow: ViewHolder #${holder.viewHolderId} detached from window")
    }
}
