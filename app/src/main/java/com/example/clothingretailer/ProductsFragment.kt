package com.example.clothingretailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clothingretailer.adapters.ProductAdapter
import com.example.clothingretailer.databinding.FragmentProductBinding

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        val adapter = ProductAdapter()
        binding.productList.adapter = adapter

        binding.addWishlist.setOnClickListener {
            //TODO: Add item to wish list

        }
        binding.addCart.setOnClickListener {
           //TODO: Add item to shopping cart

        }
        return binding.root
    }

}