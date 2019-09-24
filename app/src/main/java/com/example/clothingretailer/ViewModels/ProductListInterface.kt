package com.example.clothingretailer.viewmodels

import androidx.lifecycle.LiveData
import com.example.clothingretailer.data.ProductRepository
import com.example.clothingretailer.data.Products

interface ProductListInterface {

    fun getProductListForDisplay(productRepository: ProductRepository) : LiveData<List<Products>>
}