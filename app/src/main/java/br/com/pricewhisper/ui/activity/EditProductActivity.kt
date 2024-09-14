package br.com.pricewhisper.ui.activity

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.utils.PRODUCT_KEY
import br.com.pricewhisper.utils.appLocale

class EditProductActivity : AppCompatActivity() {

    private lateinit var edtProductName: EditText
    private lateinit var edtProductPrice: EditText
    private lateinit var edtProductStock: EditText
    private lateinit var edtProductDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)
        setTitle("Editar Produto")

        initializeFields()

        val product: Product = intent.getSerializableExtra(PRODUCT_KEY) as Product

        fillForm(product)
    }

    private fun fillForm(product: Product) {
        val formattedPrice = String.format(appLocale, product.price.toString())

        edtProductName.setText(product.name)
        edtProductPrice.setText(formattedPrice)
        edtProductStock.setText(product.stock.toString())
        edtProductDescription.setText(product.description)
    }

    private fun initializeFields() {
        edtProductName = findViewById(R.id.edit_product_edt_name)
        edtProductPrice = findViewById(R.id.edit_product_edt_price)
        edtProductStock = findViewById(R.id.edit_product_edt_stock)
        edtProductDescription = findViewById(R.id.edit_product_edt_description)
    }
}