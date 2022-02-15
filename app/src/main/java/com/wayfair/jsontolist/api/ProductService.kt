package com.wayfair.jsontolist.api

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.wayfair.jsontolist.model.Product
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductService {
    @get:GET("products.json")
    val products: Call<List<Product>>


    companion object {
        @Volatile
        private var INSTANCE : ProductService? = null

        // ***** Singleton instance of the ProductService ******
        fun getInstance(context : Context): ProductService = INSTANCE ?: synchronized(this) {
           Retrofit.Builder()
                .baseUrl("https://api/")
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .create()
                    )
                )
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(RequestInterceptor(context.assets))
                        .build())
                .build().create(ProductService::class.java)
        }
    }
}
