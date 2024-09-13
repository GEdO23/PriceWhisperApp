package br.com.pricewhisper.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.pricewhisper.R

class EditProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)
        setTitle("Editar Produto")
    }
}