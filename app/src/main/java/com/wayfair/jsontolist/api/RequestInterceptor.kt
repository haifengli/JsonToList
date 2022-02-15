package com.wayfair.jsontolist.api

import android.content.res.AssetManager
import okhttp3.*
import java.io.IOException
import java.util.*

/**
 * This code should not be modified for the purposes of the sample application.
 * This code allows for us to retrieve data from the assets/products.json file instead of
 * relying on a server call. This ensures that for every interview we conduct, internet is not
 * required for each build, allowing less friction for development.
 */
class RequestInterceptor internal constructor(private val assetManager: AssetManager) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val inputStream = assetManager.open(PRODUCTS_JSON_FILE_NAME)
        val json = Scanner(inputStream).useDelimiter("\\A").next()
        return Response.Builder()
            .body(ResponseBody.create(MediaType.parse("application/json"), json))
            .request(chain.request())
            .protocol(Protocol.HTTP_2)
            .code(200)
            .message("200 OK")
            .build()
    }

    companion object {
        private const val PRODUCTS_JSON_FILE_NAME = "products.json"
    }
}
