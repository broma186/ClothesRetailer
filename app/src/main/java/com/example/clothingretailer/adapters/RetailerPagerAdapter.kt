package com.example.clothingretailer.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.clothingretailer.ProductsFragment
import com.example.clothingretailer.ShoppingCartFragment
import com.example.clothingretailer.WishListFragment

const val PRODUCTS_INDEX = 0
const val WISH_LIST_INDEX = 1
const val SHOPPING_CART_INDEX = 2

class RetailerPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        PRODUCTS_INDEX to { ProductsFragment() },
        WISH_LIST_INDEX to { WishListFragment() },
        SHOPPING_CART_INDEX to { ShoppingCartFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}