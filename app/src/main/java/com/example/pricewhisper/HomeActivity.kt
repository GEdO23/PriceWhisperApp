package com.example.pricewhisper

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setTitle("Home")

        val btnProductsList: TextView = findViewById(R.id.home_btn_products_list)
        btnProductsList.setOnClickListener {
            val intent = Intent(this@HomeActivity, ProductsListActivity::class.java)
            startActivity(intent)
        }
    }
}