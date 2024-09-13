package com.example.pricewhisper.ui.recycler.holder

import android.icu.text.DecimalFormat
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pricewhisper.R
import com.example.pricewhisper.models.Product
import java.util.Locale

class ProductViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val productName: TextView = itemView.findViewById(R.id.product_name)
    private val productPrice: TextView = itemView.findViewById(R.id.product_price)

    private val priceFormat = DecimalFormat.getCurrencyInstance(Locale("PT", "BR"))

    fun bind(product: Product) {
        productName.text = product.name
        productPrice.text = priceFormat.format(product.price)
    }

}
