package com.example.clothingretailer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getProducts(): LiveData<List<Products>>

    @Query("SELECT EXISTS(SELECT 1 FROM products WHERE product_id = :plantId AND in_wish_list = 1 LIMIT 1)")
    fun InWishList(plantId: String): LiveData<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM products WHERE product_id = :plantId AND in_shopping_cart = 1 LIMIT 1)")
    fun InShoppingCart(plantId: String): LiveData<Boolean>

    @Insert
    suspend fun insertProduct(product: Product): Long

    @Delete
    suspend fun deleteProduct(product: Product)
}