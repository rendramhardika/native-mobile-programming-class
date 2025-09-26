package com.example.mobile_layout.screennative

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.mobile_layout.R

/**
 * Activity demonstrating the use of RelativeLayout in native Android.
 * This shows how to position views relative to each other and the parent.
 */
class RelativeLayoutActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative_layout)
        
        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setupToolbar(toolbar, getString(R.string.relative_layout_title))
    }
    
    override fun onBackPressed() {
        super.onBackPressedDispatcher
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
