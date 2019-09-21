package com.example.clothingretailer.api

import com.example.clothingretailer.utilities.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ProductServiceFactory {

    fun createProductService(): ProductService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ProductService::class.java)
    }
}