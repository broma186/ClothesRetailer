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

class ProductsFragment : Fragment(), ListObserver {

    private lateinit var binding: FragmentProductBinding
    private lateinit var adapter: ProductAdapter

    private val viewModel: ProductListViewModel by viewModels {
        InjectorUtils.provideProductListViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        adapter = ProductAdapter()
        binding.productList.adapter = adapter

        observeList()

        return binding.root
    }

    /*
        Utilizes live data to Observe any changes in the database which then refreshes the list of products
        that are displayed in the recycler view. Will indicate there are no products with a textview if
        no products are returned in the result variable.
     */
    override fun observeList() {
        viewModel.productList.observe(viewLifecycleOwner) {result ->
            if (!result.isNullOrEmpty()) {
                binding.noProducts.visibility = View.GONE
            }
            adapter.submitList(result)
        }
    }
}