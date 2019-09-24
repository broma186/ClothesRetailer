package com.example.clothingretailer.viewmodels

import android.content.Context
import android.util.Log
import com.example.clothingretailer.data.Products
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothingretailer.api.ProductApiHelper
import com.example.clothingretailer.data.ProductRepository
import com.example.clothingretailer.data.ShoppingCartResponse
import com.example.clothingretailer.utilities.InjectorUtils
import com.example.clothingretailer.utilities.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response


class ProductViewModel(products: Products)
    : ViewModel() {
    private val product = checkNotNull(products.product)

    val productId
        get() = product.productId
    val name
        get() = product.name
    val category
        get() = product.category
    val price
        get() = product.price
    val oldPrice
        get() = product.oldPrice
    val stock
        get() = product.stock
    val inWishList
        get() = product.inWishList
    val inShoppingCart
        get() = product.inShoppingCart
    val cartId
        get() = product.cartId


    fun addProductToWishList(context : Context) {
        CoroutineScope(Dispatchers.IO).launch {
            storeProductInWishList(context, productId.toString())
            withContext(Dispatchers.Main) {
                try {
                     Toast.makeText(context, "Added " + name + " to wish list", Toast.LENGTH_SHORT).show()
                } catch (e: HttpException) {
                    Toast.makeText(context,"\"Failed to add product to cart: ${e.message}\"", Toast.LENGTH_SHORT).show()
                } catch (e: Throwable) {
                    Toast.makeText(context,"Failed to add product to cart", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun addProductToShoppingCart(context : Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<ShoppingCartResponse> = ProductApiHelper.addProductToCart(productId)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        val shoppingCartRes : ShoppingCartResponse? = response.body()
                        storeProductInShoppingList(context, shoppingCartRes?.productId.toString(), shoppingCartRes?.cartId.toString())
                    } else { // Store product in shopping list locally so that the call can run again when they purchase.
                        storeProductInShoppingList(context, productId.toString(), null)
                    }
                } catch (e: HttpException) {
                    Log.d(TAG, "\"Failed to add product to cart: ${e.message}\"")
                    storeProductInShoppingList(context, productId.toString(), null)
                } catch (e: Throwable) {
                    Log.d(TAG, "Failed to add product to cart")
                    storeProductInShoppingList(context, productId.toString(), null)
                }
            }
        }
    }

    fun storeProductInWishList(context: Context, productId : String) {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        productRepository.addToWishList(productId)
    }

    fun storeProductInShoppingList(context: Context, productId : String, cartId : String?) {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        productRepository.addToShoppingCart(productId, cartId)
    }

}

