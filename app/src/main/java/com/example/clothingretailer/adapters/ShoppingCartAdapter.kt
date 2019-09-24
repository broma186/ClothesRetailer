package com.example.clothingretailer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingretailer.R
import com.example.clothingretailer.data.Products
import com.example.clothingretailer.databinding.ListItemShoppingCartBinding
import com.example.clothingretailer.utilities.ZERO_OLD_PRICE
import com.example.clothingretailer.viewmodels.ProductViewModel

class ShoppingCartAdapter : ListAdapter<Products, ShoppingCartAdapter.ViewHolder>(ShoppingCartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_shopping_cart, parent, false
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
        private val binding: ListItemShoppingCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            if (binding.productOldPrice.text.equals(ZERO_OLD_PRICE)) {
                binding.productOldPrice.visibility = View.GONE
            }
            binding.removeFromShoppingCart.setOnClickListener {
                binding.viewModel?.removeProductFromShoppingCart(binding.root.context)
            }
        }

        fun bind(products: Products) {
            with(binding) {
                viewModel = ProductViewModel(products)
                executePendingBindings()
            }
        }
    }

    private class ShoppingCartDiffCallback : DiffUtil.ItemCallback<Products>() {

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
