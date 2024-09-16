package br.com.pricewhisper.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.utils.CurrencyUtil
import br.com.pricewhisper.utils.PRODUCT_ID_KEY
import br.com.pricewhisper.utils.PRODUCT_KEY
import br.com.pricewhisper.utils.RTDB_PRODUCTS_URL
import br.com.pricewhisper.utils.StockUtil
import br.com.pricewhisper.utils.httpClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ProductDetailsActivity : AppCompatActivity() {

    private val gson = Gson()
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productDescription: TextView
    private lateinit var productStock: TextView
    private lateinit var fabEditProduct: FloatingActionButton
    private lateinit var deleteProduct: TextView
    private lateinit var productId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        initializeFields()
        setTitle(getString(R.string.actionbar_productdetails_title))
    }

    override fun onResume() {
        super.onResume()

        getByIdInFirebase(productId)

        fabEditProduct.setOnClickListener {
            val product = getProductInfo()
            goToEditProduct(product, productId)
        }

        deleteProduct.setOnClickListener {
            deleteProductFromFirebase(productId)
            finish()
        }
    }

    private fun getByIdInFirebase(id: String) {
        val request = Request.Builder()
            .url("$RTDB_PRODUCTS_URL/$id.json")
            .get()
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("GetById", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val localBody = response.body
                val productsJson = localBody?.string()
                if (productsJson != null) {
                    val product = gson.fromJson(productsJson, Product::class.java)
                    runOnUiThread {
                        fillProductInfo(product)
                    }
                }
            }
        }

        httpClient.newCall(request)
            .enqueue(response)
    }

    private fun goToEditProduct(product: Product, productId: String) {
        val intent = Intent(this@ProductDetailsActivity, EditProductActivity::class.java)
        intent.putExtra(PRODUCT_KEY, product)
        intent.putExtra(PRODUCT_ID_KEY, productId)
        startActivity(intent)
    }

    private fun initializeFields() {
        productId = intent.getSerializableExtra(PRODUCT_ID_KEY) as String

        productName = findViewById(R.id.product_details_name)
        productPrice = findViewById(R.id.product_details_price)
        productDescription = findViewById(R.id.product_details_description)
        productStock = findViewById(R.id.product_details_stock)
        fabEditProduct = findViewById(R.id.product_details_fab_edit_product)
        deleteProduct = findViewById(R.id.product_details_delete_product)
        underlineText(deleteProduct)
    }

    private fun underlineText(textView: TextView) {
        val deleteProductText = textView.text.toString()
        val ss = SpannableString(deleteProductText)
        ss.setSpan(UnderlineSpan(), 0, ss.length, 0)
        textView.text = ss
    }

    private fun deleteProductFromFirebase(id: String) {
        val request = Request.Builder()
            .url("$RTDB_PRODUCTS_URL/$id.json")
            .delete()
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("DeleteProduct", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val localBody = response.body
                val json = localBody?.string()

                Log.d("DeleteProduct", "Json: $json")
            }

        }

        httpClient.newCall(request)
            .enqueue(response)
    }

    private fun fillProductInfo(product: Product) {
        productName.text = product.name
        productPrice.text = CurrencyUtil().format(product.price)
        productDescription.text = product.description
        productStock.text = StockUtil().format(product.stock)
    }

    private fun getProductInfo(): Product {
        return Product(
            name = productName.text.toString(),
            price = CurrencyUtil().format(productPrice.text.toString()),
            stock = StockUtil().format(productStock.text.toString()),
            description = productDescription.text.toString()
        )
    }
}