package com.example.clothingretailer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.example.clothingretailer.databinding.ActivityMainBinding
import org.jetbrains.anko.toast

/*
Home activity that isn't the launch activity, but the activity that displays the navigation bar
with it's view pager that has tabs for products, wish list and shopping cart. Uses AndroidX data binding.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
}