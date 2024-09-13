package br.com.pricewhisper.ui.recycler.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.utils.CurrencyUtil

class ProductViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val productName: TextView = itemView.findViewById(R.id.product_name)
    private val productPrice: TextView = itemView.findViewById(R.id.product_price)

    fun bind(product: Product) {
        productName.text = product.name
        productPrice.text = CurrencyUtil().format(product.price)
    }

}
