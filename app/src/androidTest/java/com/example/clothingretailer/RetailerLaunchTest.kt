package com.example.clothingretailer

import android.content.Context
import android.util.Log
import com.example.clothingretailer.api.ProductApiHelper
import com.example.clothingretailer.data.Product
import com.example.clothingretailer.data.ProductRepository
import com.example.clothingretailer.data.Products
import com.example.clothingretailer.utilities.InjectorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.toast
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import retrofit2.HttpException
import retrofit2.Response

class RetailerLaunchTest {

    @Test
    fun checkIfThereAreProducts() {
        val context = mock(Context::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
            val product: Products = productRepository.checkForAProduct()
            withContext(Dispatchers.Main) {
                assertTrue(product != null)
                assertFalse(product == null)
            }
        }
    }

    @Test
    fun storeAllProducts() {
        val context = mock(Context::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<List<Product>> = ProductApiHelper.getProducts()
            if (response.isSuccessful) {
                ProductApiHelper.writeProductsToDb(response.body(), context)
                val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
                val product: Products = productRepository.checkForAProduct()
                assertTrue(product != null)
            }
            withContext(Dispatchers.Main) {
                try {
                    // Do nothing
                } catch (e: HttpException) {
                    fail()
                } catch (e: Throwable) {
                    fail()
                }
            }
        }
    }
}