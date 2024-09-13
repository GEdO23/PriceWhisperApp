package com.example.pricewhisper.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pricewhisper.R

class ProductsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)
        setTitle("Lista de Produtos")
    }
}