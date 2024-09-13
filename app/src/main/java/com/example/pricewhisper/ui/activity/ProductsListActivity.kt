package com.example.pricewhisper.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pricewhisper.R
import com.example.pricewhisper.models.Product
import com.example.pricewhisper.ui.recycler.adapter.ProductsListAdapter
import java.math.BigDecimal

class ProductsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)
        setTitle("Lista de Produtos")

        val productsList: MutableList<Product> = arrayListOf(
            Product("Arroz 5kg", BigDecimal(47.95)),
            Product("Feijão 5kg", BigDecimal(42.00))
        )

        val productsListRecycler: RecyclerView = findViewById(R.id.products_list_recycler)
        productsListRecycler.layoutManager = LinearLayoutManager(this)
        productsListRecycler.adapter = ProductsListAdapter(this, productsList)
    }
}