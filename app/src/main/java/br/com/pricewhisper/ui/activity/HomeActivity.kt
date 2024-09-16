package br.com.pricewhisper.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.pricewhisper.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setTitle(getString(R.string.actionbar_home_title))

        val btnProductsList: TextView = findViewById(R.id.home_btn_products_list)
        btnProductsList.setOnClickListener {
            val intent = Intent(this@HomeActivity, ProductsListActivity::class.java)
            startActivity(intent)
        }
    }
}