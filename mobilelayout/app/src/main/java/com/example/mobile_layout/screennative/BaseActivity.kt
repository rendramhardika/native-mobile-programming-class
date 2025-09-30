package com.example.mobile_layout.screennative

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.mobile_layout.R

/**
 * Base activity that provides common functionality for all native activities.
 * Sets up the toolbar with back button and handles back navigation.
 */
abstract class BaseActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            // Make the content appear under the system bars
            WindowCompat.setDecorFitsSystemWindows(window, false)
            
            // Set up edge-to-edge display - dengan penanganan error yang lebih baik
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                try {
                    window.setDecorFitsSystemWindows(false)
                    window.insetsController?.let {
                        // Komentar baris berikut untuk mencegah crash
                        // it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                        it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    protected fun setupToolbar(toolbar: Toolbar, title: String, showBackButton: Boolean = true) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            this.title = title
            setDisplayHomeAsUpEnabled(showBackButton)
            setDisplayShowHomeEnabled(showBackButton)
        }
        
        // Handle window insets for the toolbar
        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                top = systemBars.top,
                left = systemBars.left,
                right = systemBars.right
            )
            insets
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle back button click in the toolbar
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
