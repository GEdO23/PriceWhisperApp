package br.com.pricewhisper.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.utils.PRODUCT_ID_KEY
import br.com.pricewhisper.utils.PRODUCT_KEY
import br.com.pricewhisper.utils.RTDB_PRODUCTS_URL
import br.com.pricewhisper.utils.appLocale
import br.com.pricewhisper.utils.httpClient
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class EditProductActivity : AppCompatActivity() {

    private val gson = Gson()
    private lateinit var edtProductName: EditText
    private lateinit var edtProductPrice: EditText
    private lateinit var edtProductStock: EditText
    private lateinit var edtProductDescription: EditText
    private lateinit var productId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)
        setTitle("Editar Produto")

        initializeFields()

        val product = intent.getSerializableExtra(PRODUCT_KEY) as Product
        productId = intent.getSerializableExtra(PRODUCT_ID_KEY) as String

        fillForm(product)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId: Int = item.itemId
        if (itemId == R.id.save) {
            finishFormulary()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun finishFormulary() {
        val filledProduct = toEntity()
        editProductFromFirebase(filledProduct)
        finish()
    }

    private fun editProductFromFirebase(filledProduct: Product) {
        val productJson = gson.toJson(filledProduct)
        val mediaType = "application/json".toMediaTypeOrNull()
        val body = productJson.toRequestBody(mediaType)

        val request = Request.Builder()
            .url("$RTDB_PRODUCTS_URL/$productId.json")
            .put(body)
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("FirebaseEditProduct", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val localBody = response.body
                Log.d("FirebaseProducts", localBody!!.string())

                runOnUiThread {
                    Toast.makeText(
                        this@EditProductActivity,
                        "Produto editado",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }

        httpClient.newCall(request)
            .enqueue(response)
    }

    private fun toEntity(): Product {
        val name = edtProductName.text.toString()
        val price = edtProductPrice.text.toString()
        val stock = edtProductStock.text.toString()
        val description = edtProductDescription.text.toString()

        return Product(
            name = name,
            price = price.toBigDecimal(),
            stock = stock.toUInt(),
            description = description
        )
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