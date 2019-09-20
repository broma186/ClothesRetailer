package com.example.clothingretailer.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.clothingretailer.data.Product
import com.example.clothingretailer.data.ProductRepository
import com.example.clothingretailer.data.Products

class ProductListViewModel internal constructor(
    productRepository: ProductRepository
) : ViewModel() {
    val productList: LiveData<List<Products>> =
        productRepository.getProducts()
}