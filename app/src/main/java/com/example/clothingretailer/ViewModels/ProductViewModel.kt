package com.example.clothingretailer.viewmodels

import android.content.Context
import android.util.Log
import com.example.clothingretailer.data.Products
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.clothingretailer.R
import com.example.clothingretailer.api.ProductApiHelper
import com.example.clothingretailer.data.ProductRepository
import com.example.clothingretailer.data.ShoppingCartResponse
import com.example.clothingretailer.utilities.InjectorUtils
import com.example.clothingretailer.utilities.NO_STOCK
import com.example.clothingretailer.utilities.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response


class ProductViewModel(products: Products) : ViewModel() {
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


    fun addProductToWishList(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            storeProductInWishListDb(context, productId.toString())
            withContext(Dispatchers.Main) {
                try {
                    Toast.makeText(context, "Added " + name + " to wish list", Toast.LENGTH_SHORT)
                        .show()
                } catch (e: HttpException) {
                    Toast.makeText(
                        context,
                        "\"Failed to add product to cart: ${e.message}\"",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Throwable) {
                    Toast.makeText(context, "Failed to add product to cart", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    fun removeProductFromWishList(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            removeFromWishListDb(context, productId.toString())
            withContext(Dispatchers.Main) {
                try {
                    Toast.makeText(context, "Added " + name + " to wish list", Toast.LENGTH_SHORT).show()
                } catch (e: HttpException) {
                    Toast.makeText(context, context.getString(R.string.remove_wish_list_failure) +
                            "${e.message}\"", Toast.LENGTH_SHORT).show()
                } catch (e: Throwable) {
                    Toast.makeText(context, context.getString(R.string.remove_wish_list_failure), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun addProductToShoppingCart(context: Context) {
        if (stock > NO_STOCK) { // Product must be in stock to be added to cart.
            CoroutineScope(Dispatchers.IO).launch {
                val response: Response<ShoppingCartResponse> =
                    ProductApiHelper.addProductToCart(productId)
                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {
                            val shoppingCartRes: ShoppingCartResponse? = response.body()
                            storeProductInShoppingListDb(context, shoppingCartRes?.productId.toString(),
                                shoppingCartRes?.cartId.toString())
                        } else { // Store product in shopping list locally so that the call can run again when they purchase.
                            storeProductInShoppingListDb(context, productId.toString(), null)
                        }
                    } catch (e: HttpException) {
                        Log.d(TAG, context.getString(R.string.add_cart_failure) + "${e.message}\"")
                        Toast.makeText(context, context.getString(R.string.add_cart_failure) +
                                "${e.message}\"", Toast.LENGTH_SHORT).show()
                        storeProductInShoppingListDb(context, productId.toString(), null)
                    } catch (e: Throwable) {
                        Log.d(TAG, context.getString(R.string.add_cart_failure))
                        Toast.makeText(context, context.getString(R.string.add_cart_failure), Toast.LENGTH_SHORT).show()
                        storeProductInShoppingListDb(context, productId.toString(), null)
                    }
                }
            }
        } else { // The selected product is out of stock. Don't add the product to the cart.
            Toast.makeText(context, context.getString(R.string.out_of_stock), Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun removeProductFromShoppingCart(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val response: ResponseBody = ProductApiHelper.removeProductFromCart(productId)
            withContext(Dispatchers.Main) {
                try {
                    removeProductFromShoppingCartDb(context, productId.toString())
                } catch (e: HttpException) {
                    Log.d(TAG,  context.getString(R.string.remove_cart_failure) + "${e.message}\"")
                    removeProductFromShoppingCartDb(context, productId.toString())
                } catch (e: Throwable) {
                    Log.d(TAG, context.getString(R.string.remove_cart_failure))
                    removeProductFromShoppingCartDb(context, productId.toString())
                }
            }
        }
    }

    fun storeProductInWishListDb(context: Context, productId: String) {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        productRepository.addToWishList(productId)
    }

    fun removeFromWishListDb(context: Context, productId: String) {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        productRepository.removeFromWishList(productId)
    }

    fun storeProductInShoppingListDb(context: Context, productId: String, cartId: String?) {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        productRepository.addToShoppingCart(productId, cartId)
    }

    fun removeProductFromShoppingCartDb(context: Context, productId: String) {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        productRepository.removeFromShoppingCart(productId)
    }
}

