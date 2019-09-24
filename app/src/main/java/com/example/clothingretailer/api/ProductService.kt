package com.example.clothingretailer.api

import com.example.clothingretailer.data.Product
import com.example.clothingretailer.data.ShoppingCartResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface ProductService {

    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("product/{productId}")
    suspend fun getProduct(@Path("productId") productId: Int): Response<Product>

    @Headers("Content-Type:application/json")
    @POST("cart")
    suspend fun addToCart(@Body productId : Int) : Response<ShoppingCartResponse>

    @DELETE("cart/{cartId}")
    suspend fun deleteFromCart(@Path("cartId") cartId: Int) : Response<ShoppingCartResponse>
}

