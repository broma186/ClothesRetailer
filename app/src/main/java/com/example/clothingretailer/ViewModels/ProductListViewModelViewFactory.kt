package com.example.clothingretailer.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clothingretailer.data.ProductRepository


class ProductListViewModelViewFactory(
    private val repository: ProductRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductListViewModel(repository) as T
    }
}
