package br.com.pricewhisper.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pricewhisper.models.Product
import br.com.pricewhisper.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repo = ProductRepository()
    val productList = mutableStateListOf<Product>()

    fun getAll() {
        viewModelScope.launch {
            repo.getProductListFromFirebase(
                onRequestFailure = { error ->
                    Log.e(
                        "PRICE_WHISPER",
                        "GET PRODUCT LIST FROM FIREBASE FAILED:\n${error.message}"
                    )
                },
                onRequestSuccess = { products ->
                    productList.clear()
                    products?.forEach { (id, product) ->
                        if (product != null) {
                            product.id = id
                            productList.add(product)
                            Log.d("PRICE_WHISPER", "PRODUCT '$id': $product")
                        }
                    }
                }
            )
        }
    }

    fun save(
        product: Product
    ) {
        viewModelScope.launch {
            repo.postProductToFirebase(
                product = product,
                onRequestFailure = { error ->
                    Log.e(
                        "PRICE_WHISPER",
                        "POST PRODUCT TO FIREBASE FAILED:\n${error.message}"
                    )
                },
                onRequestSuccess = { productSaved ->
                    Log.d("PRICE_WHISPER", "Product saved: $productSaved")
                }
            )
        }
    }
}