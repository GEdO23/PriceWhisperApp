package br.com.pricewhisper.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.utils.PRODUCT_KEY
import br.com.pricewhisper.utils.RTDB_PRODUCTS_URL
import br.com.pricewhisper.utils.httpClient
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class RegisterProductActivity : AppCompatActivity() {

    private val gson = Gson()
    private lateinit var edtProductName: EditText
    private lateinit var edtProductPrice: EditText
    private lateinit var edtProductStock: EditText
    private lateinit var edtProductDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_product)
        setTitle("Cadastro de Produto")
        initializeFields()
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

    private fun initializeFields() {
        edtProductName = findViewById(R.id.register_product_edt_name)
        edtProductPrice = findViewById(R.id.register_product_edt_price)
        edtProductStock = findViewById(R.id.register_product_edt_stock)
        edtProductDescription = findViewById(R.id.register_product_edt_description)
    }

    private fun finishFormulary() {
        runOnUiThread {
            validateFields()
            if (!edtProductName.text.isNullOrBlank() && !edtProductPrice.text.isNullOrBlank()) {
                val filledProduct = toEntity()
                saveProductInFirebase(filledProduct)
                goToProductDetails(filledProduct)
            }
        }
    }

    private fun validateFields() {
        if (edtProductName.text.isNullOrBlank()) {
            Toast.makeText(
                this@RegisterProductActivity,
                "O nome do produto deve ser informado", Toast.LENGTH_SHORT
            ).show()
        }
        if (edtProductPrice.text.isNullOrBlank()) {
            Toast.makeText(
                this@RegisterProductActivity,
                "O preço do produto deve ser informado", Toast.LENGTH_SHORT
            ).show()
        }
        if (edtProductStock.text.isNullOrBlank()) edtProductStock.setText("1")
        if (edtProductDescription.text.isNullOrBlank()) edtProductDescription.setText("")
    }

    private fun toEntity(): Product {
        val productName = edtProductName.text.toString()
        val productPrice = edtProductPrice.text.toString().toBigDecimal()
        val productStock = edtProductStock.text.toString().toUInt()
        val productDescription = edtProductDescription.text.toString()

        return Product(
            name = productName,
            price = productPrice,
            stock = productStock,
            description = productDescription
        )
    }

    private fun saveProductInFirebase(product: Product) {
        val productJson = gson.toJson(product)
        val mediaType = "application/json".toMediaTypeOrNull()
        val body = productJson.toRequestBody(mediaType)

        val request = Request.Builder()
            .url("$RTDB_PRODUCTS_URL.json")
            .post(body)
            .build()

        val response = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("FirebaseRegisterProduct", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val localBody = response.body
                val productsJson = localBody?.string()
                Log.d("FirebaseProducts", "" + productsJson)

                runOnUiThread {
                    Toast.makeText(
                        this@RegisterProductActivity,
                        "Produto cadastrado",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }

        httpClient.newCall(request)
            .enqueue(response)
    }

    private fun goToProductDetails(filledProduct: Product) {
        val intent = Intent(this@RegisterProductActivity, ProductDetailsActivity::class.java)
        intent.putExtra(PRODUCT_KEY, filledProduct)
        startActivity(intent)
    }
}