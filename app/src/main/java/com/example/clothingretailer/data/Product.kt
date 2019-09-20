package com.example.clothingretailer.data

import androidx.room.*
import java.util.*

@Entity(tableName = "product",
        indices = [androidx.room.Index("product_id")]
)

data class Product(
    @PrimaryKey @ColumnInfo(name = "product_id") val productId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "in_wish list") val inWishList: Int,
    @ColumnInfo(name = "in_shopping_cart") val inShoppingCart: Int
)