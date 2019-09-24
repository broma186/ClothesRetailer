package com.example.clothingretailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingretailer.adapters.WishListAdapter
import com.example.clothingretailer.data.Products
import com.example.clothingretailer.databinding.FragmentWishListBinding
import com.example.clothingretailer.utilities.InjectorUtils
import com.example.clothingretailer.viewmodels.ProductListViewModel
import com.example.clothingretailer.viewmodels.WishListViewModel

class WishListFragment : Fragment(), ListObserver{

    private lateinit var binding: FragmentWishListBinding
    private lateinit var adapter: WishListAdapter

    private val viewModel: WishListViewModel by viewModels {
        InjectorUtils.provideWishListViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWishListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        adapter = WishListAdapter()
        binding.wishList.adapter = adapter

        observeList()

        return binding.root
    }

    override fun observeList() {
        viewModel.productList.observe(viewLifecycleOwner) { result ->
            if (!result.isNullOrEmpty()) {
                binding.noProducts.visibility = View.GONE
            }
            adapter.submitList(result)
        }
    }
}