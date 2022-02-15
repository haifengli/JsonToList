package com.wayfair.jsontolist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.wayfair.jsontolist.R
import com.wayfair.jsontolist.api.ProductService
import com.wayfair.jsontolist.model.Product
import com.wayfair.jsontolist.util.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.atomic.AtomicBoolean

class ProductServiceAPIRepo {
    companion object {
        @Volatile
        private var INSTANCE : ProductServiceAPIRepo? = null
        fun getInstance() = INSTANCE ?: synchronized(this) {
            INSTANCE ?: ProductServiceAPIRepo().apply {
                INSTANCE = this
            }
        }

    }

    fun fetchProducts(context : Context) : LiveData<Resource<List<Product>>> {
        return object : LiveData<Resource<List<Product>>>() {

            private val started = AtomicBoolean(false)
            init {
                postValue(Resource.loading(null))
            }

            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    ProductService.getInstance(context).products.enqueue(object :
                        Callback<List<Product>> {
                        override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                            if (response.isSuccessful) {
                                postValue(Resource.success(response.body()))
                                return
                            }

                            val errorMessage  = response.errorBody()?.toString()
                            postValue(Resource.error(errorMessage ?: context.getString(R.string.unknown_error)))
                        }
                        override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                            postValue(Resource.error(t.message ?: context.getString(R.string.unknown_error)))
                        }
                    })
                }
            }
        }
    }


}