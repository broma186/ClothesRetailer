package com.example.clothingretailer.viewmodels

import com.example.clothingretailer.data.Products
import android.widget.TextView
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.BindingAdapter


class ProductViewModel(products: Products) {
    private val product = checkNotNull(products.product)

    val productId
        get() = product.productId
    val name
        get() = product.name
    val category
        get() = product.category
    val price
        get() = product.price
    val oldPrice
        get() = product.oldPrice


    val stock
        get() = product.stock




}

