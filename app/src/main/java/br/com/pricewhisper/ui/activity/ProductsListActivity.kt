package br.com.pricewhisper.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.ui.recycler.adapter.ProductsListAdapter
import br.com.pricewhisper.utils.PRODUCT_ID_KEY
import br.com.pricewhisper.utils.PRODUCT_KEY
import br.com.pricewhisper.utils.RTDB_PRODUCTS_URL
import br.com.pricewhisper.utils.httpClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class ProductsListActivity : AppCompatActivity() {

    private val gson = Gson()
    private val productsList: MutableList<Product> = mutableListOf()
    private lateinit var productsIds: List<String>
    private lateinit var adapter: ProductsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)
        setTitle("Lista de Produtos")

        configureRecyclerView()

        val fabRegisterProduct: FloatingActionButton =
            findViewById(R.id.products_list_fab_register_product)

        fabRegisterProduct.setOnClickListener {
            goToRegisterProduct()
        }
    }

    override fun onResume() {
        super.onResume()
        loadFirebase()
    }

    private fun loadFirebase() {
        val requestGetMethod = Request.Builder()
            .url("$RTDB_PRODUCTS_URL.json")
            .get()
            .build()

        val responseGetMethod = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("LoadFirebase", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val localBody = response.body
                val productsJson = localBody?.string()

                if (productsJson != null && productsJson != "null") {
                    val productsMapType = object : TypeToken<Map<String, Product?>?>() {}.type
                    val productsMap: Map<String, Product> =
                        gson.fromJson(productsJson, productsMapType)

                    val products = productsMap.values.toList()
                    productsIds = productsMap.keys.toList()

                    runOnUiThread {
                        products.forEach {
                            productsList.add(it)
                            Log.d("Produto", gson.toJson(it))
                        }
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this@ProductsListActivity,
                            "Nenhum produto encontrado\nCadastre um produto para começar",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        }

        httpClient.newCall(requestGetMethod)
            .enqueue(responseGetMethod)
    }

    private fun goToProductDetails(position: Int) {
        val intent = Intent(this@ProductsListActivity, ProductDetailsActivity::class.java)
        intent.putExtra(PRODUCT_KEY, productsList[position])
        intent.putExtra(PRODUCT_ID_KEY, productsIds[position])
        startActivity(intent)
    }

    private fun configureRecyclerView() {
        val productsListRecycler: RecyclerView = findViewById(R.id.products_list_recycler)
        productsListRecycler.layoutManager = LinearLayoutManager(this)
        adapter = ProductsListAdapter(this, productsList) { goToProductDetails(it) }
        productsListRecycler.adapter = adapter
    }

    private fun goToRegisterProduct() {
        val intent = Intent(this@ProductsListActivity, RegisterProductActivity::class.java)
        startActivity(intent)
    }
}