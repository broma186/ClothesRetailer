package com.example.clothingretailer.adapters


import com.example.clothingretailer.R
import com.example.clothingretailer.databinding.ListItemProductBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.clothingretailer.viewmodels.ProductViewModel
import com.example.clothingretailer.data.Products

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
            binding.setClickListener { view ->
                binding.viewModel?.productId?.let { productId ->
                    //TODO User presses the item, either add to wish list or shopping cart (KAPOOW!!)
                }
            }
        }


        fun bind(products: Products) {
            with(binding) {
                viewModel = ProductViewModel(products)
                executePendingBindings()
            }
        }
    }

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
