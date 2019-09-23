package com.example.clothingretailer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.clothingretailer.api.ProductApiHelper
import com.example.clothingretailer.api.ProductServiceFactory
import com.example.clothingretailer.data.AppDatabase
import com.example.clothingretailer.utilities.SPLASH_DELAY
import retrofit2.HttpException
import com.example.clothingretailer.data.Product
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



        CoroutineScope(Dispatchers.IO).launch {
            val response: Response<List<Product>> = ProductApiHelper.getProducts()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        ProductApiHelper.writeProductsToDb(response.body(), context)
                        goToHome()
                    } else {
                        toast("Error: ${response.code()}")
                    }
                } catch (e: HttpException) {
                    toast("\"Exception ${e.message}\"")
                } catch (e: Throwable) {
                    toast("Ooops: Something else went wrong")
                }
            }

        }
    }

    fun goToHome() {
        val intent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
