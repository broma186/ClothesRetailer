package com.example.clothingretailer.adapters


import android.util.Log
import com.example.clothingretailer.R
import com.example.clothingretailer.databinding.ListItemProductBinding
import android.view.LayoutInflater
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
            if (binding.productOldPrice.text.equals(ZERO_OLD_PRICE)) {
                binding.showOldPrice = false
            }
            binding.addToWishList.setOnClickListener {

            }
            binding.addToShoppingCart.setOnClickListener {
                addProductToCart()
            }
        }


        fun bind(products: Products) {
            with(binding) {
                viewModel = ProductViewModel(products)
                executePendingBindings()
            }
        }

        private fun addProductToCart() {
            CoroutineScope(Dispatchers.IO).launch {
                val response: Response<ShoppingCartResponse> = ProductApiHelper.addProductToCart(binding.viewModel.productId)
                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {
                            
                        } else {
                            Log.d(TAG, "Failed to add product to cart: ${response.code()}")
                        }
                    } catch (e: HttpException) {
                        Log.d(TAG, "\"Failed to add product to cart: ${e.message}\"")
                    } catch (e: Throwable) {
                        Log.d(TAG, "Failed to add product to cart")
                    }
                }

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
