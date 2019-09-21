package com.example.clothingretailer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.clothingretailer.api.ProductServiceFactory
import com.example.clothingretailer.data.AppDatabase
import com.example.clothingretailer.utilities.SPLASH_DELAY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import com.example.clothingretailer.data.Product
import retrofit2.Response

class RetailerLaunch : AppCompatActivity() {

    private var mDelayHandler: Handler? = null

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retailer_launch)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
       // mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

        getProducts()

    }


    fun getProducts() {
        val service = ProductServiceFactory.createProductService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getProducts()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        Log.d(com.example.clothingretailer.utilities.TAG, "Success!!!")
                        val products : List<Product>? = response.body()
                        val database = AppDatabase.getInstance(applicationContext)
                        database.plantDao().insertAll(plantList)
                    } else {
                        Log.d(com.example.clothingretailer.utilities.TAG, "Error: ${response.code()}"
                        )
                    }
                } catch (e: HttpException) {
                    Log.d(com.example.clothingretailer.utilities.TAG, "\"Exception ${e.message}\"")
                } catch (e: Throwable) {
                    Log.d(com.example.clothingretailer.utilities.TAG, "Ooops: Something else went wrong"
                    )
                }
            }
        }

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
