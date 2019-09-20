package com.example.clothingretailer.ViewModels

import com.example.clothingretailer.data.Product
import com.example.clothingretailer.data.ProductList

class ProductViewModel(productList : ProductList) {

    val product: Product = productList.products[0]

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