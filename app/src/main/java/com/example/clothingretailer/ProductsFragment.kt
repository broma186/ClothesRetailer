package com.example.clothingretailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.clothingretailer.viewmodels.ProductListViewModel
import com.example.clothingretailer.adapters.ProductAdapter
import com.example.clothingretailer.databinding.FragmentProductBinding
import com.example.clothingretailer.utilities.InjectorUtils

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding

    private val viewModel: ProductListViewModel by viewModels {
        InjectorUtils.provideProductListViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        val adapter = ProductAdapter()
        binding.productList.adapter = adapter

        viewModel.productList.observe(viewLifecycleOwner) {result ->
            binding.hasProducts = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
        return binding.root
    }

}