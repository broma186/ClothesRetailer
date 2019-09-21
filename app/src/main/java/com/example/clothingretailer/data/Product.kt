package com.example.clothingretailer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index
import java.util.*

@Entity(tableName = "products",
        indices = [Index("product_id")]
)

data class Product(
    @PrimaryKey @ColumnInfo(name = "product_id") val productId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "in_wish_list") val inWishList: Int,
    @ColumnInfo(name = "in_shopping_cart") val inShoppingCart: Int
)