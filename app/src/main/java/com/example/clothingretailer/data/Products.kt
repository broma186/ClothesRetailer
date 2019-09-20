package com.example.clothingretailer.data

import androidx.room.Embedded

data class Products (

    @Embedded
    val product: Product
)