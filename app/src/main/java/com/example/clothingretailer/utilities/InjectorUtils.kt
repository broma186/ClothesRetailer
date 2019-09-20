package com.example.clothingretailer.utilities

import android.content.Context
import com.example.clothingretailer.ViewModels.ProductListViewModelViewFactory
import com.example.clothingretailer.data.AppDatabase
import com.example.clothingretailer.data.ProductRepository

object InjectorUtils {

    private fun getProductRepository(context: Context): ProductRepository {
        return ProductRepository.getInstance(
            AppDatabase.AppDatabase.getInstance(context.applicationContext).productDao())
    }

    fun provideProductListViewModelFactory(
        context: Context
    ): ProductListViewModelViewFactory {
        val repository = getProductRepository(context)
        return ProductListViewModelViewFactory(repository)
    }
}