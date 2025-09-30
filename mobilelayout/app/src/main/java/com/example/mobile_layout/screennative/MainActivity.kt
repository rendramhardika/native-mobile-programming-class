package com.example.mobile_layout.screennative

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.mobile_layout.R
import com.google.android.material.button.MaterialButton

/**
 * Main activity for the native implementation of layout examples.
 * This activity serves as an entry point to various native layout examples.
 */
class MainActivity : BaseActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_native)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setupToolbar(toolbar, getString(R.string.native_layouts_title), false)

        // Set up button click listeners with proper animations
        setupButton(R.id.btnLinearLayout, LinearLayoutActivity::class.java)
        setupButton(R.id.btnRelativeLayout, RelativeLayoutActivity::class.java)
        setupButton(R.id.btnConstraintLayout, ConstraintLayoutActivity::class.java)
        setupButton(R.id.btnRecyclerView, RecyclerViewActivity::class.java)
        setupButton(R.id.btnTimeline, TimelineActivity::class.java)
        
        // Set up back button to return to Compose version
        findViewById<MaterialButton>(R.id.btnBack).setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
    
    private fun setupButton(buttonId: Int, activityClass: Class<*>) {
        findViewById<Button>(buttonId).setOnClickListener {
            startActivity(Intent(this, activityClass))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
