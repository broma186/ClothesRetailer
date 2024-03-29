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
import com.example.clothingretailer.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

/*
The fragment that displays the nav bar and view pager using data binding. The fragments for the viewpager
 are initialized inside RetailerPagerAdapter which is the adapter set for the viewpager. The icon/title for
 each tab is dependant on the fragment index assigned in the RetailerPagerAdapter.
 */
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = RetailerPagerAdapter(this) // Set the view pager's adapter.

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    // Set icon for each fragment tab.
    private fun getTabIcon(position: Int): Int {
        return when (position) {
            PRODUCTS_INDEX -> R.drawable.product_tab_selector
            WISH_LIST_INDEX -> R.drawable.wish_list_tab_selector
            SHOPPING_CART_INDEX -> R.drawable.shopping_cart_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    // Set title for each fragment tab.
    private fun getTabTitle(position: Int): String? {
        return when (position) {
            PRODUCTS_INDEX -> getString(R.string.products_title)
            WISH_LIST_INDEX -> getString(R.string.wish_list_title)
            SHOPPING_CART_INDEX -> getString(R.string.shopping_cart_title)
            else -> null
        }
    }

}