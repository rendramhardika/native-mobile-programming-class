package com.example.mobile_layout.screennative

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.mobile_layout.R

/**
 * Activity demonstrating the use of LinearLayout in native Android.
 * This shows a simple form layout using vertical and horizontal LinearLayouts.
 */
class LinearLayoutActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_layout)
        
        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setupToolbar(toolbar, getString(R.string.linear_layout_title))
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
