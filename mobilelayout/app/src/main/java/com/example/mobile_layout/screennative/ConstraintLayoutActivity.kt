package com.example.mobile_layout.screennative

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.mobile_layout.R

/**
 * Activity demonstrating the use of ConstraintLayout in native Android.
 * This shows a product page for headphones using ConstraintLayout for responsive design.
 */
class ConstraintLayoutActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout)
        
        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setupToolbar(toolbar, getString(R.string.constraint_layout_title))
        
        // Set up back button click listener
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
