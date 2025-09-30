package com.example.mobile_layout.screennative

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_layout.R
import com.example.mobile_layout.adapter.ItemSpacingDecoration
import com.example.mobile_layout.adapter.ProductAdapter
import com.example.mobile_layout.model.Product

/**
 * Activity demonstrating the use of RecyclerView in native Android.
 * This shows a list of products using RecyclerView and CardView.
 */
class RecyclerViewActivity : BaseActivity() {
    
    companion object {
        private const val TAG = "RecyclerViewActivity"
    }
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val productList = ArrayList<Product>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        
        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setupToolbar(toolbar, getString(R.string.recycler_view_title))
        
        // Initialize RecyclerView
        Log.d(TAG, "onCreate: Initializing RecyclerView")
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        // Add item decoration for spacing
        recyclerView.addItemDecoration(ItemSpacingDecoration(8))
        Log.d(TAG, "onCreate: Added ItemSpacingDecoration")
        
        // Add divider between items
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        Log.d(TAG, "onCreate: Added DividerItemDecoration")
        
        // Add scroll listener to monitor recycling
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> 
                        Log.d(TAG, "onScrollStateChanged: Scroll IDLE")
                    RecyclerView.SCROLL_STATE_DRAGGING -> 
                        Log.d(TAG, "onScrollStateChanged: Scroll DRAGGING")
                    RecyclerView.SCROLL_STATE_SETTLING -> 
                        Log.d(TAG, "onScrollStateChanged: Scroll SETTLING")
                }
            }
            
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisible = layoutManager.findFirstVisibleItemPosition()
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                Log.d(TAG, "onScrolled: Visible items from $firstVisible to $lastVisible")
            }
        })
        
        // Prepare data
        Log.d(TAG, "onCreate: Preparing product data")
        prepareProductData()
        Log.d(TAG, "onCreate: Prepared ${productList.size} products")
        
        // Set up adapter
        Log.d(TAG, "onCreate: Creating ProductAdapter")
        productAdapter = ProductAdapter(productList) { product ->
            // Handle item click
            Toast.makeText(
                this,
                "Selected: ${product.title}",
                Toast.LENGTH_SHORT
            ).show()
            Log.d(TAG, "Item clicked from Activity: ${product.title}")
        }
        
        recyclerView.adapter = productAdapter
        Log.d(TAG, "onCreate: Set adapter to RecyclerView")
        
        // Set up back button click listener
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
    
    private fun prepareProductData() {
        // Add sample products
        productList.add(
            Product(
                1,
                "Smartphone XYZ",
                "Latest smartphone with 6.5-inch display, 128GB storage, and triple camera setup.",
                899.99,
                R.drawable.ic_launcher_foreground
            )
        )
        
        productList.add(
            Product(
                2,
                "Laptop Pro",
                "Powerful laptop with Intel i7 processor, 16GB RAM, and 512GB SSD storage.",
                1299.99,
                R.drawable.ic_launcher_foreground
            )
        )
        
        productList.add(
            Product(
                3,
                "Wireless Headphones",
                "Premium noise-cancelling headphones with 30-hour battery life.",
                249.99,
                R.drawable.ic_launcher_foreground
            )
        )
        
        productList.add(
            Product(
                4,
                "Smart Watch",
                "Fitness tracker with heart rate monitor, GPS, and water resistance.",
                199.99,
                R.drawable.ic_launcher_foreground
            )
        )
        
        productList.add(
            Product(
                5,
                "Bluetooth Speaker",
                "Portable speaker with 360° sound and 12-hour playtime.",
                79.99,
                R.drawable.ic_launcher_foreground
            )
        )
        
        productList.add(
            Product(
                6,
                "Tablet Ultra",
                "10-inch tablet with high-resolution display and long battery life.",
                349.99,
                R.drawable.ic_launcher_foreground
            )
        )
        
        productList.add(
            Product(
                7,
                "Wireless Earbuds",
                "True wireless earbuds with touch controls and charging case.",
                129.99,
                R.drawable.ic_launcher_foreground
            )
        )
        
        productList.add(
            Product(
                8,
                "External Hard Drive",
                "2TB portable hard drive with USB 3.0 for fast data transfer.",
                89.99,
                R.drawable.ic_launcher_foreground
            )
        )
        
        productList.add(
            Product(
                9,
                "Gaming Console",
                "Next-gen gaming console with 4K graphics and 1TB storage.",
                499.99,
                R.drawable.ic_launcher_foreground
            )
        )
        
        productList.add(
            Product(
                10,
                "Wireless Mouse",
                "Ergonomic wireless mouse with adjustable DPI and long battery life.",
                39.99,
                R.drawable.ic_launcher_foreground
            )
        )
    }
    
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
    
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Activity resumed")
    }
    
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Activity paused")
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Activity destroyed")
    }
}
