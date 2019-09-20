package com.example.clothingretailer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getProducts(): LiveData<List<Product>>

    @Query("SELECT EXISTS(SELECT 1 FROM product WHERE product_id = :plantId LIMIT 1)")
    fun InWishList(plantId: String): LiveData<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM product WHERE product_id = :plantId LIMIT 1)")
    fun InShoppingCart(plantId: String): LiveData<Boolean>

    @Insert
    suspend fun insertProduct(product: Product): Long

    @Delete
    suspend fun deleteProduct(product: Product)
}