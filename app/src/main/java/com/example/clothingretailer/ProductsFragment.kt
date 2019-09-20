package com.example.clothingretailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.example.clothingretailer.ViewModels.ProductListViewModel
import com.example.clothingretailer.ViewModels.ProductViewModel
import com.example.clothingretailer.adapters.ProductAdapter
import com.example.clothingretailer.databinding.FragmentProductBinding
import com.example.clothingretailer.utilities.InjectorUtils

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding

    private val viewModel: ProductListViewModel by viewModels {
       // InjectorUtils
    }
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
        viewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
        viewModel.productList.observe(viewLifecycleOwner) {result ->
            binding.hasProducts = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
        return binding.root
    }

}