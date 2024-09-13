package br.com.pricewhisper.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.pricewhisper.R
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.ui.recycler.adapter.ProductsListAdapter
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
    private lateinit var fabRegisterProduct: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)
        setTitle("Lista de Produtos")

        val adapter = ProductsListAdapter(this, productsList) { goToProductDetails(it) }

        configureRecyclerView(adapter)

        loadFirebase(adapter)

        fabRegisterProduct = findViewById(R.id.products_list_fab_register_product)

        fabRegisterProduct.setOnClickListener {
            goToRegisterProduct()
        }

    }

    private fun loadFirebase(adapter: ProductsListAdapter) {
        val requestGetMethod = Request.Builder()
            .url(RTDB_PRODUCTS_URL)
            .get()
            .build()

        val responseGetMethod = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("LoadFirebase", e.message.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val localBody = response.body
                val productsJson = localBody?.string()

                val productsMapType = object : TypeToken<Map<String, Product>>() {}.type
                val productsMap: Map<String, Product> = gson.fromJson(productsJson, productsMapType)

                val products = productsMap.values.toList()

                runOnUiThread {
                    products.forEach {
                        productsList.add(it)
                        Log.d("Produto", it.toJson())
                    }
                    adapter.notifyDataSetChanged()
                }
            }

        }

        httpClient.newCall(requestGetMethod)
            .enqueue(responseGetMethod)
    }

    private fun goToProductDetails(position: Int) {
        val intent = Intent(this@ProductsListActivity, ProductDetailsActivity::class.java)
        intent.putExtra(PRODUCT_KEY, productsList[position])
        startActivity(intent)
    }

    private fun configureRecyclerView(adapter: ProductsListAdapter) {
        val productsListRecycler: RecyclerView = findViewById(R.id.products_list_recycler)
        productsListRecycler.layoutManager = LinearLayoutManager(this)
        productsListRecycler.adapter = adapter
    }

    private fun goToRegisterProduct() {
        val intent = Intent(this@ProductsListActivity, RegisterProductActivity::class.java)
        startActivity(intent)
    }
}