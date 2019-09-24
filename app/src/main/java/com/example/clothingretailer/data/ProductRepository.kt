package com.example.clothingretailer.data

class ProductRepository private constructor(
    private val productDao: ProductDao
) {

    fun getProducts() = productDao.getProducts()

    fun getShoppingList() = productDao.getShoppingCartProducts()

    fun getWishList() = productDao.getWishListProducts()

    fun addToShoppingCart(productId: String, cartId: String?) = productDao.addToShoppingCart(productId, cartId)

    fun removeFromShoppingCart(productId: String) = productDao.removeFromShoppingCart(productId)

    fun addToWishList(productId: String) = productDao.addToWishList(productId)

    fun removeFromWishList(productId: String) = productDao.removeFromWishList(productId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: ProductRepository? = null

        fun getInstance(gardenPlantingDao: ProductDao) =
            instance ?: synchronized(this) {
                instance ?: ProductRepository(gardenPlantingDao).also { instance = it }
            }
    }

}