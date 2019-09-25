package com.example.clothingretailer.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class ProductDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var productDao: ProductDao

    val products = arrayListOf(
        Product(1, "Shoe", "A big shoe", 25.0.toFloat(), 0.0.toFloat(), 4, null, null, null),
        Product(2, "Shirt", "A big shirt", 12.0.toFloat(), 0.0.toFloat(), 6, null, 1, 4),
        Product(3, "Sunnies", "Big sunnies", 35.0.toFloat(), 0.0.toFloat(), 1, 1, 1, 4)
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking<Unit> {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        productDao = database.productDao()
        database.productDao().insertAll(products)
    }

}