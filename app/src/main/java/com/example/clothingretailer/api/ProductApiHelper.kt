package com.example.clothingretailer.api

import android.content.Context
import android.widget.Toast
import com.example.clothingretailer.data.AppDatabase
import com.example.clothingretailer.data.Product
import com.example.clothingretailer.data.Products
import com.example.clothingretailer.data.ShoppingCartResponse
import com.example.clothingretailer.utilities.FAIL
import com.example.clothingretailer.utilities.SUCCESS
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

object ProductApiHelper {


   suspend fun getProducts() : Response<List<Product>> = ProductServiceFactory.
       createProductService().getProducts()

    suspend fun writeProductsToDb(products: List<Product>?, context: Context) =
        AppDatabase.getInstance(context).productDao().insertAll(products)

    suspend fun addProductToCart(productId : Int) : Response<ShoppingCartResponse> = ProductServiceFactory.
        createProductService().addToCart(productId)
}