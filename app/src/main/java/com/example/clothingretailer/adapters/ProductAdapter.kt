package com.example.clothingretailer.adapters


import android.content.Context
import android.util.Log
import com.example.clothingretailer.R
import com.example.clothingretailer.databinding.ListItemProductBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingretailer.api.ProductApiHelper
import com.example.clothingretailer.data.Product
import com.example.clothingretailer.viewmodels.ProductViewModel
import com.example.clothingretailer.data.Products
import com.example.clothingretailer.data.ShoppingCartResponse
import com.example.clothingretailer.utilities.TAG
import com.example.clothingretailer.utilities.ZERO_OLD_PRICE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

/*
The listAdapter for the products list recycler view on the products tab. Has two buttons, one to add
an item to the shopping cart and another to add to wish list. The listeners for these buttons should be
in the list_item_product onclick methods for the various image views. This would have been the correct way
to data bind, but I ran into errors too late.
 */
class ProductAdapter : ListAdapter<Products, ProductAdapter.ViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_product, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { products ->
            with(holder) {
                bind(products)
            }
        }
    }

    class ViewHolder(
        private val binding: ListItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            if (binding.viewModel?.oldPrice == null || binding.viewModel?.oldPrice.toString() == ZERO_OLD_PRICE) {
                binding.oldPriceLayout.visibility = View.GONE
            }
            // User presses trolley icon, adds product to shopping cart. Stays in product list.
            binding.addToShoppingCart.setOnClickListener {
                binding.viewModel?.addProductToShoppingCart(binding.root.context)
            }
            // User presses star icon, adds product to wish list. Stays in product list.
            binding.addToWishList.setOnClickListener {
                binding.viewModel?.addProductToWishList(binding.root.context)
            }
        }

        fun bind(products: Products) { // Bind each product view with the view model
            with(binding) {
                viewModel = ProductViewModel(products)
                executePendingBindings()
            }
        }
    }

    /*
    Make sure that Products aren't displayed twice.
     */
    private class ProductDiffCallback : DiffUtil.ItemCallback<Products>() {

        override fun areItemsTheSame(
            oldItem: Products,
            newItem: Products
        ): Boolean {
            return oldItem.product.productId == newItem.product.productId
        }

        override fun areContentsTheSame(
            oldItem: Products,
            newItem: Products
        ): Boolean {
            return oldItem.product.name == newItem.product.name
        }
    }
}
