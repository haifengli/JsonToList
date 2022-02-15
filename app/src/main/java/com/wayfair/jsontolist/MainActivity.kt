package com.wayfair.jsontolist

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wayfair.jsontolist.model.Product
import com.wayfair.jsontolist.ui.ProductListAdapter
import com.wayfair.jsontolist.util.Status

/**
 * MainActivity observes the Product list resource status and displays the UI accordingly.
 *
 */
class MainActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels()

    private lateinit var productRV : RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var errorGeneralTxt : TextView
    private lateinit var errorDetailTxt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productRV = findViewById(R.id.product_rv)
        progressBar = findViewById(R.id.progress_bar)
        errorGeneralTxt = findViewById(R.id.error_general_txt)
        errorDetailTxt = findViewById(R.id.error_detail_txt)


        // **** Handles the different network resource fetching status ****
        viewModel.productLists.observe(this, { resource ->
            when (resource.status) {
                Status.LOADING -> displayProgressbar()
                Status.SUCCESS -> displayProductList(resource.data)
                Status.ERROR -> displayErrorMessage(resource.message)

            }
        })

    }

    private fun displayProgressbar() {
        progressBar.isVisible = true
    }
    private fun displayProductList(products : List<Product>?) {
        progressBar.isVisible = false
        productRV.layoutManager = LinearLayoutManager(productRV.context, LinearLayoutManager.VERTICAL, false)
        val productListAdapter = ProductListAdapter()
        productRV.adapter = productListAdapter
        productListAdapter.submitList(products)

    }

    private fun displayErrorMessage(errorMessage : String?) {
        progressBar.isVisible = false
        errorGeneralTxt.isVisible = true
        errorDetailTxt.isVisible = true
        errorDetailTxt.text = errorMessage ?: getString(R.string.unknown_error)
    }




}
