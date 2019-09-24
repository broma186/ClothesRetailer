package com.example.clothingretailer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.clothingretailer.data.ProductRepository
import com.example.clothingretailer.data.Products

class ShoppingListViewModel internal constructor(
    productRepository: ProductRepository
) : ViewModel(), ProductListInterface{
    val productList: LiveData<List<Products>> =
        getProductListForDisplay(productRepository)


    override fun getProductListForDisplay(productRepository: ProductRepository): LiveData<List<Products>> {
        return productRepository.getShoppingList()
    }
}