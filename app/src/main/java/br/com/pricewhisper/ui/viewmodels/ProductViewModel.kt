package br.com.pricewhisper.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.repository.ProductRepository

class ProductViewModel {
    private val repo = ProductRepository()

    val productList = mutableStateListOf<Product>()

    fun save(product: Product) {
        repo.saveInFirebase(product)
    }

    fun getAll() {
        repo.loadFirebase(productList)
    }
}