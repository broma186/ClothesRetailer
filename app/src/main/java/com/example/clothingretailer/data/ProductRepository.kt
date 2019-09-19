package com.example.clothingretailer.data

class ProductRepository private constructor(
    private val productDao: ProductDao
) {

    suspend fun createProduct(plantId: String) {
        val product = Product(plantId)
        productDao.insertProduct(product)
    }

    suspend fun removeProduct(gardenPlanting: Product) {
        productDao.deleteProduct(gardenPlanting)
    }

    fun getProducts() = productDao.getProducts()

    fun InWishList(productId: String) =
        productDao.InWishList(productId)

    fun InShoppingCart(productId: String) =
        productDao.InShoppingCart(productId)

}