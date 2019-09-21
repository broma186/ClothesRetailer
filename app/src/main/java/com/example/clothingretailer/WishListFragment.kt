package com.example.clothingretailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.clothingretailer.adapters.WishListAdapter
import com.example.clothingretailer.databinding.FragmentWishListBinding
import com.example.clothingretailer.utilities.InjectorUtils
import com.example.clothingretailer.viewmodels.ProductListViewModel

class WishListFragment : Fragment(){

    private val viewModel: ProductListViewModel by viewModels {
        InjectorUtils.provideProductListViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWishListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = WishListAdapter()
        binding.wishList.adapter = adapter

        viewModel.productList.observe(viewLifecycleOwner) {result ->
            binding.hasProducts = !result.isNullOrEmpty()
            adapter.submitList(result)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

}