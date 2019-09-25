package com.example.clothingretailer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.example.clothingretailer.api.ProductApiHelper
import com.example.clothingretailer.api.ProductServiceFactory
import com.example.clothingretailer.data.AppDatabase
import com.example.clothingretailer.utilities.SPLASH_DELAY
import retrofit2.HttpException
import com.example.clothingretailer.data.Product
import com.example.clothingretailer.data.ProductRepository
import com.example.clothingretailer.data.Products
import com.example.clothingretailer.utilities.InjectorUtils
import com.example.clothingretailer.utilities.SUCCESS
import kotlinx.coroutines.*
import org.jetbrains.anko.toast
import retrofit2.Response

class RetailerLaunch : AppCompatActivity() {

    val context : Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retailer_launch)

        checkIfThereAreProducts()
    }

    /*
    Checks to see if there are products downloaded and stored locally. We want items
    to be persistent so that they are found in the user's wish list and shopping cart if they were
    previously added. If the products download again every time, the shopping cart/wish list items will be
    replaced through their ids.
     */
    fun checkIfThereAreProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val productRepository: ProductRepository = InjectorUtils.getProductRepository(context)
            val product: Products = productRepository.checkForAProduct()
            withContext(Dispatchers.Main) {
                if (product == null) { // No products downloaded, app running first time, download all.
                    storeAllProducts()
                } else { // Already have products, go straight to home activity.
                    goToHome()
                }
            }

        }
    }

    /*
    Coroutine runs on the IO thread for the API call to the endpoint that retrieves all products
    with a GET request. If successful the results are stored in the database. Toast error reporting
    will display on the main thread should either of the product operations fail.
     */
    fun storeAllProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<List<Product>> = ProductApiHelper.getProducts()
            if (response.isSuccessful) {
                ProductApiHelper.writeProductsToDb(response.body(), context)
                goToHome()
            } else {
                toast("Error: ${response.code()}")
            }
            withContext(Dispatchers.Main) {
                try {
                   // Do nothing
                } catch (e: HttpException) {
                    toast("\"Exception ${e.message}\"")
                    finish()
                } catch (e: Throwable) {
                    toast("Ooops: Something else went wrong")
                    finish()
                }
            }
        }
    }

    /*
    Takes the user to the home activity, providing there are products to work with
     */
    fun goToHome() {
        val intent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
