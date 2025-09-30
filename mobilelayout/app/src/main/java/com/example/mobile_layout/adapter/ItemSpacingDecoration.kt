package com.example.mobile_layout.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Item decoration for adding spacing between items in a RecyclerView
 */
class ItemSpacingDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        
        // Add top spacing for all items except the first one
        if (position > 0) {
            outRect.top = spacing
        }
        
        // Add bottom spacing for all items
        outRect.bottom = spacing
        outRect.left = spacing
        outRect.right = spacing
    }
}
