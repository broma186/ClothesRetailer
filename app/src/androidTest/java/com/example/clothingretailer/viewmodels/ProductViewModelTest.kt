package com.example.clothingretailer.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.clothingretailer.R
import com.example.clothingretailer.api.ProductApiHelper
import com.example.clothingretailer.data.AppDatabase
import com.example.clothingretailer.data.Product
import com.example.clothingretailer.data.ProductRepository
import com.example.clothingretailer.data.Products
import com.example.clothingretailer.utilities.InjectorUtils
import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito
import retrofit2.HttpException

class ProductViewModelTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: ProductViewModel
    val productId: String = "1"
    val context = Mockito.mock(Context::class.java)

    val productArr = arrayListOf(
        Product(1, "Shoe", "A big shoe", 25.0.toFloat(), 0.0.toFloat(), 4, null, null, null),
        Product(2, "Shirt", "A big shirt", 12.0.toFloat(), 0.0.toFloat(), 6, null, 1, 4),
        Product(3, "Sunnies", "Big sunnies", 35.0.toFloat(), 0.0.toFloat(), 1, 1, 1, 4)
    )


    @Test
    fun addProductToWishList() {
        CoroutineScope(Dispatchers.IO).launch {
            storeProductInWishListDb()
            val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
            assertTrue(productRepository.inWishList(productId))
        }
    }


    @Test
    fun storeProductInWishListDb() {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        productRepository.addToWishList(productId)
    }

    @Test
    fun removeProductFromWishList() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ProductApiHelper.removeProductFromCart(productId)
            if (response.isSuccessful) {
                removeProductFromShoppingCartDb()
                assertFalse(isInShoppingCart())
            }
            withContext(Dispatchers.Main) {
                try {
                } catch (e: HttpException) {
                    fail(context.getString(R.string.remove_cart_failure) + "${e.message}\"")
                } catch (e: Throwable) {
                    fail(context.getString(R.string.remove_cart_failure))
                }
            }
        }
    }

    @Test
    fun addProductToShoppingCart() {
    }

    @Test
    fun removeProductFromShoppingCart() {
    }

    @Test
    fun removeProductFromShoppingCartDb() {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        productRepository.removeFromShoppingCart(productId)
    }

    @Test
    fun removeFromWishListDb() {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        productRepository.removeFromWishList(productId)
    }

    @Test
    fun isInWishList() : Boolean {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        assertTrue(productRepository.inWishList(productId))
        return productRepository.inWishList(productId)
    }

    @Test
    fun isInShoppingCart() : Boolean {
        val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
        assertTrue(productRepository.inShoppingCart(productId))
        return productRepository.inShoppingCart(productId)
    }
}