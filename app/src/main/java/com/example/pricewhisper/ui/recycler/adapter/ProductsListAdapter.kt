package com.example.pricewhisper.ui.recycler.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pricewhisper.R
import com.example.pricewhisper.models.Product
import com.example.pricewhisper.ui.recycler.holder.ProductViewHolder

class ProductsListAdapter(
    private val context: Context,
    private val productsList: List<Product>
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val viewCreated = LayoutInflater.from(context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(viewCreated)
    }

    override fun getItemCount(): Int = productsList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: Product = productsList[position]
        holder.bind(product)
    }

}
