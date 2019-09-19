package com.example.clothingretailer.adapters


import com.example.clothingretailer.R
import com.example.clothingretailer.ViewModels.ProductListViewModel
import com.example.clothingretailer.data.Product
import com.example.clothingretailer.databinding.ListItemProductBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter : ListAdapter<ProductListViewModel, ProductAdapter.ViewHolder>(ProductDiffCallback()) {

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
            binding.setClickListener { view ->
                binding.viewModel?.productId?.let { productId ->
                    //TODO User presses the item, either add to wish list or shopping cart (KAPOOW!!)
                }
            }
        }


        fun bind(products: List<Product>) {
            with(binding) {
                viewModel = ProductListViewModel(products)
                executePendingBindings()
            }
        }
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<ProductListViewModel>() {

        override fun areItemsTheSame(
            oldItem: ProductListViewModel,
            newItem: ProductListViewModel
        ): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(
            oldItem: ProductListViewModel,
            newItem: ProductListViewModel
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
