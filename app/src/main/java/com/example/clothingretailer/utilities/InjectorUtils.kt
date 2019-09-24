package com.example.clothingretailer.utilities

import android.content.Context
import com.example.clothingretailer.viewmodels.ProductListViewModelViewFactory
import com.example.clothingretailer.data.AppDatabase
import com.example.clothingretailer.data.ProductRepository

object InjectorUtils {

    fun getProductRepository(context: Context): ProductRepository {
        return ProductRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).productDao())
    }

    fun provideProductListViewModelFactory(
        context: Context
    ): ProductListViewModelViewFactory {
        val repository = getProductRepository(context)
        return ProductListViewModelViewFactory(repository)
    }
}