package com.wayfair.jsontolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wayfair.jsontolist.R
import com.wayfair.jsontolist.databinding.ProductItemBinding
import com.wayfair.jsontolist.model.Product
import com.wayfair.jsontolist.util.RatingConverter

/**
 * List adapter for each product.
 */
class ProductListAdapter : ListAdapter<Product, ProductViewHolder>(object : ItemCallback<Product>() {


    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.tagline == newItem.tagline && oldItem.rating == newItem.rating
    }

}) {

    private val ratingConverter = RatingConverter()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.product_item, parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.product = getItem(position)
        holder.binding.ratingConverter = ratingConverter

    }

}

class ProductViewHolder(val binding : ProductItemBinding) : RecyclerView.ViewHolder(binding.root)


