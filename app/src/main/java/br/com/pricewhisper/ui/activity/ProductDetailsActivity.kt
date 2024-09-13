package br.com.pricewhisper.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.utils.CurrencyUtil
import br.com.pricewhisper.utils.PRODUCT_KEY
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productDescription: TextView
    private lateinit var productStock: TextView
    private lateinit var fabEditProduct: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setTitle("Detalhes")

        val productClicked: Product = intent.getSerializableExtra(PRODUCT_KEY) as Product

        initializeFields()

        fillProductInfo(productClicked)

        fabEditProduct.setOnClickListener {
            goToEditProduct()
        }
    }

    private fun goToEditProduct() {
        val intent = Intent(this@ProductDetailsActivity, EditProductActivity::class.java)
        startActivity(intent)
    }

    private fun initializeFields() {
        productName = findViewById(R.id.product_details_name)
        productPrice = findViewById(R.id.product_details_price)
        productDescription = findViewById(R.id.product_details_description)
        productStock = findViewById(R.id.product_details_stock)
        fabEditProduct = findViewById(R.id.product_details_fab_edit_product)
    }

    private fun fillProductInfo(product: Product) {
        productName.text = product.name
        productPrice.text = CurrencyUtil().format(product.price)
        productDescription.text = product.description
        productStock.text = product.stock.toString()
    }
}