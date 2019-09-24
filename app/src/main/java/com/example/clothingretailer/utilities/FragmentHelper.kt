package com.example.clothingretailer.utilities

import android.util.Log
import com.example.clothingretailer.data.Products

object FragmentHelper {

    fun getTotalPrice(products : List<Products>) : String {
        var totalPrice = 0.0
        for(p : Products in products) {
            totalPrice += p.product.price
        }
        Log.d("TEST", "Value of total price is : " + totalPrice)
        return totalPrice.toString()
    }
}