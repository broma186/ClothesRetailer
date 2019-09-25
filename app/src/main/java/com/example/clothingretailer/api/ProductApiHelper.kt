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

/*
A helper class for all the app's network calls. Uses retrofit service for the abstraction and
coroutines for the implementation.
 */
object ProductApiHelper {

    // Gets all the products from the server endpoint.
   suspend fun getProducts() : Response<List<Product>> = ProductServiceFactory.
       createProductService().getProducts()

    /* Gets the Room database Dao class and calls the insertAll method that adds all the downloaded
        products to the db.
    */
    suspend fun writeProductsToDb(products: List<Product>?, context: Context) =
        AppDatabase.getInstance(context).productDao().insertAll(products)

    /*
    Calls the add products to cart method. This adds the product marked by it's respective id to a shopping cart.
    A cartId is returned as an identifier that the item is part of a shopping cart. This would be assigned to a
     user in future.
     */
    suspend fun addProductToCart(productId : Int) : Response<ShoppingCartResponse> = ProductServiceFactory.
        createProductService().addToCart(productId)

    /*
    Deletes a product from the shopping cart. Calls the APi call deleteFromCart.
     */
    suspend fun removeProductFromCart(cartId : Int) : Response<ShoppingCartResponse> = ProductServiceFactory.
        createProductService().deleteFromCart(cartId)
}