package com.example.clothingretailer.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.clothingretailer.data.Product
import com.example.clothingretailer.data.ProductRepository

class ProductListViewModel internal constructor(
    productRepository: ProductRepository
) : ViewModel() {
    val productList: LiveData<List<Product>> =
        productRepository.getProducts()
}