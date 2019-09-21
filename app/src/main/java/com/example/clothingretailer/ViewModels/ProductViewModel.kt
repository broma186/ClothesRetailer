package com.example.clothingretailer.viewmodels

import com.example.clothingretailer.data.Products

class ProductViewModel(products : Products) {
    private val product = checkNotNull(products.product)

    val productId
        get() = product.productId
    val name
        get() = product.name
    val price
        get() = product.price
    val quantity
        get() = product.stock
}