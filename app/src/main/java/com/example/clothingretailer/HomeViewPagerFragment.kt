package com.example.clothingretailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.clothingretailer.adapters.PRODUCTS_INDEX
import com.example.clothingretailer.adapters.RetailerPagerAdapter
import com.example.clothingretailer.adapters.SHOPPING_CART_INDEX
import com.example.clothingretailer.adapters.WISH_LIST_INDEX
import com.example.clothingretailer.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = RetailerPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            PRODUCTS_INDEX -> getString(R.string.products_title)
            /*WISH_LIST_INDEX -> getString(R.string.wish_list_title)
            SHOPPING_CART_INDEX -> getString(R.string.shopping_cart_title)*/
            else -> null
        }
    }

}