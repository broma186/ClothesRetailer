package com.example.clothingretailer.api

import com.example.clothingretailer.data.Product
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path


interface ProductService {

    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("product/{productId}")
    suspend fun getProduct(@Path("productId") productId: Int): Response<Product>
}

