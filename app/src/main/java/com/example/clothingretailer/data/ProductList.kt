package com.example.clothingretailer.data

import androidx.room.Embedded

data class ProductList (

    @Embedded
    val products: List<Product> = emptyList()

)