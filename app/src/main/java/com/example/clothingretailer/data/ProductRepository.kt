package com.example.clothingretailer.data

class ProductRepository private constructor(
    private val productDao: ProductDao
) {

    fun getProducts() = productDao.getProducts()

    fun InWishList(productId: String) =
        productDao.InWishList(productId)

    fun InShoppingCart(productId: String) =
        productDao.InShoppingCart(productId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: ProductRepository? = null

        fun getInstance(gardenPlantingDao: ProductDao) =
            instance ?: synchronized(this) {
                instance ?: ProductRepository(gardenPlantingDao).also { instance = it }
            }
    }

}