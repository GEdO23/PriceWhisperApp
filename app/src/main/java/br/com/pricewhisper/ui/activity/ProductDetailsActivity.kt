package br.com.pricewhisper.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.pricewhisper.R

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setTitle("Detalhes")
    }
}