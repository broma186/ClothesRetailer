package com.example.clothingretailer.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getProducts(): LiveData<List<Products>>

    @Query("SELECT * FROM products where in_wish_list = 1")
    fun getWishListProducts() : LiveData<List<Products>>

    @Query("SELECT * FROM products where in_shopping_cart = 1")
    fun getShoppingCartProducts() : LiveData<List<Products>>

    @Query("SELECT EXISTS(SELECT 1 FROM products WHERE product_id = :productId AND in_wish_list = 1 LIMIT 1)")
    fun InWishList(productId: String): LiveData<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM products WHERE product_id = :productId AND in_shopping_cart = 1 LIMIT 1)")
    fun InShoppingCart(productId: String): LiveData<Boolean>

    @Query("UPDATE products SET in_shopping_cart = 1 AND cart_id = :cartId WHERE product_id = :productId")
    fun addToShoppingCart(productId: String, cartId : String?) : LiveData<Boolean>

    @Query("UPDATE products SET in_shopping_cart = 0 WHERE product_id = :productId")
    fun removeFromShoppingCart(productId: String) : LiveData<Boolean>

    @Query("UPDATE products SET in_wish_list = 1 WHERE product_id = :productId")
    fun addToWishList(productId: String) : LiveData<Boolean>

    @Query("UPDATE products SET in_wish_list = 0 WHERE product_id = :productId")
    fun removeFromWishList(productId: String) : LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>?)
}