package com.example.clothingretailer.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Embedded
import com.example.clothingretailer.data.Product
import com.example.clothingretailer.data.ProductRepository

class ProductListViewModel(products : List<Product>) {

    @Embedded
    val product: Product = products.get(0)

    val productId
        get() = product.productId
    val name
        get() = product.name
    val price
        get() = product.price
    val quantity
        get() = product.quantity
    val inWishList
        get() = product.inWishList
    val inShoppingCart
        get() = product.inShoppingCart
}