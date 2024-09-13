package br.com.pricewhisper.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.pricewhisper.R

class RegisterProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_product)
        setTitle("Cadastro de Produto")
    }
}