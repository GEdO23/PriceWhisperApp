package br.com.pricewhisper.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.utils.CurrencyUtil

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setTitle("Detalhes")

        val productClicked: Product = intent.getSerializableExtra("product") as Product

        val productName = findViewById<TextView>(R.id.product_details_name)
        val productPrice = findViewById<TextView>(R.id.product_details_price)
        val productDescription = findViewById<TextView>(R.id.product_details_description)
        val productStock = findViewById<TextView>(R.id.product_details_stock)

        productName.text = productClicked.name
        productPrice.text = CurrencyUtil().format(productClicked.price)
        productDescription.text = productClicked.description
        productStock.text = productClicked.stock.toString()
    }
}