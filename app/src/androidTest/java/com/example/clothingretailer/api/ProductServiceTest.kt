package com.example.clothingretailer.api

import android.util.Log
import android.widget.Toast
import com.example.clothingretailer.utilities.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import org.jetbrains.anko.toast

class ProductServiceTest {

    @Test
    fun getProducts() {
        val service = ProductServiceFactory.createProductService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getProducts()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        Log.d(TAG, "Success!!!")
                        assertTrue(response.isSuccessful)
                    } else {
                        Log.d(TAG, "Error: ${response.code()}")
                        fail()
                    }
                } catch (e: HttpException) {
                    Log.d(TAG, "\"Exception ${e.message}\"")
                    fail()
                } catch (e: Throwable) {
                    Log.d(TAG, "Ooops: Something else went wrong")
                    fail()
                }
            }
        }
    }
}