package com.wayfair.jsontolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.wayfair.jsontolist.repository.ProductServiceAPIRepo

/**
 * ViewModel which holds the Product list LiveData
 */
class ProductViewModel(app: Application) : AndroidViewModel(app) {

    val productLists = ProductServiceAPIRepo.getInstance().fetchProducts(app)

}