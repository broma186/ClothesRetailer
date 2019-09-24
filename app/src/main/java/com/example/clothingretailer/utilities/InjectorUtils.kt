package com.example.clothingretailer.utilities

import android.content.Context
import com.example.clothingretailer.viewmodels.ProductListViewModelViewFactory
import com.example.clothingretailer.data.AppDatabase
import com.example.clothingretailer.data.ProductRepository
import com.example.clothingretailer.viewmodels.ShoppingListViewModelFactory
import com.example.clothingretailer.viewmodels.WishListViewModelFactory

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

    fun provideShoppingListViewModelFactory(
        context: Context
    ): ShoppingListViewModelFactory {
        val repository = getProductRepository(context)
        return ShoppingListViewModelFactory(repository)
    }

    fun provideWishListViewModelFactory(
        context: Context
    ): WishListViewModelFactory {
        val repository = getProductRepository(context)
        return WishListViewModelFactory(repository)
    }
}