package com.example.clothingretailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.clothingretailer.adapters.ShoppingCartAdapter
import com.example.clothingretailer.databinding.FragmentShoppingCartBinding
import com.example.clothingretailer.utilities.InjectorUtils
import com.example.clothingretailer.viewmodels.ProductListViewModel
import com.example.clothingretailer.viewmodels.ShoppingListViewModel

class ShoppingCartFragment : Fragment(), ListObserver {

    private lateinit var binding: FragmentShoppingCartBinding
    private lateinit var adapter: ShoppingCartAdapter

    private val viewModel: ShoppingListViewModel by viewModels {
        InjectorUtils.provideShoppingListViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingCartBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = ShoppingCartAdapter()
        binding.shoppingCart.adapter = adapter

        viewModel.productList.observe(viewLifecycleOwner) {result ->
            binding.hasProducts = !result.isNullOrEmpty()
            adapter.submitList(result)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun observeList() {
        viewModel.productList.observe(viewLifecycleOwner) { result ->
            binding.hasProducts = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
    }
}